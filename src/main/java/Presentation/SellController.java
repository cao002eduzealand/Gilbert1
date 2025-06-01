package Presentation;


import Application.*;
import Domain.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class SellController {

    private final ProductServiceImpl productService;
    private final BrandServiceImpl brandService;
    private final ClothingArticleServiceImpl clothingArticleService;


    @Autowired
    public SellController(ProductServiceImpl productService,
                          BrandServiceImpl brandService,
                          ClothingArticleServiceImpl clothingArticleService) {
        this.productService = productService;
        this.brandService = brandService;
        this.clothingArticleService = clothingArticleService;
    }

    @GetMapping("/Sell")
    public String showSellForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Load data for dropdowns
        model.addAttribute("brands", brandService.findAll());

        // Get all categories and filter out "Designer's" category
        List<Category> allCategories = clothingArticleService.findAllCategories();
        List<Category> filteredCategories = allCategories.stream()
                .filter(category -> !category.getName().equalsIgnoreCase("Designer's"))
                .collect(Collectors.toList());

        model.addAttribute("categories", filteredCategories);
        model.addAttribute("conditions", productService.findAllProductCondition());

        model.addAttribute("user", user);
        return "Sell";
    }

    // Add REST endpoints to get subcategories and clothing articles
    @GetMapping("/api/subcategories/{categoryId}")
    @ResponseBody
    public List<SubCategory> getSubcategories(@PathVariable int categoryId) {
        return clothingArticleService.findSubcategoryByCategoryId(categoryId);
    }

    @GetMapping("/api/clothingArticles/{subcategoryId}")
    @ResponseBody
    public List<ClothingArticle> getClothingArticles(@PathVariable int subcategoryId) {
        return clothingArticleService.findBySubCategoryId(subcategoryId);
    }
    @PostMapping("/ProductReview")
    public String showProductReview(HttpSession session, Model model,
                                    @RequestParam("brandId") int brandId,
                                    @RequestParam("categoryId") int categoryId,
                                    @RequestParam("subcategoryId") int subcategoryId,
                                    @RequestParam("clothingArticleId") int clothingArticleId,
                                    @RequestParam("conditionId") int conditionId,
                                    @RequestParam("modelName") String modelName,
                                    @RequestParam("description") String description,
                                    @RequestParam("price") double price,
                                    @RequestParam(value = "productImage", required = false) MultipartFile productImage) {

        User user = (User) session.getAttribute("user");
        if (user == null){
            return "redirect:/login";
        }

        try {
            // Verify that clothing article belongs to selected subcategory
            ClothingArticle clothingArticle = clothingArticleService.findById(clothingArticleId);
            if (clothingArticle.getSubcategory().getId() != subcategoryId) {
                model.addAttribute("error", "Invalid clothing article selection");

                // Load data for dropdowns
                model.addAttribute("brands", brandService.findAll());
                model.addAttribute("categories", clothingArticleService.findAllCategories());
                model.addAttribute("conditions", productService.findAllProductCondition());

                return "Sell";
            }

            // Create a new product
            Product product = new Product();
            product.setBrand(brandService.findById(brandId));
            product.setClothingArticle(clothingArticle);
            product.setCondition(productService.findProductConditionById(conditionId));
            product.setModelName(modelName);
            product.setDescription(description);
            product.setPrice(price);
            product.setSeller(user);
            product.setDateUploaded(new Timestamp(System.currentTimeMillis()));

            // Save the product
            Product savedProduct = productService.save(product);
            System.out.println("Saved product with ID: " + savedProduct.getId());

            // Handle image upload if provided
            if (productImage != null && !productImage.isEmpty()) {
                try {
                    System.out.println("Processing image: " + productImage.getOriginalFilename() + ", size: " + productImage.getSize());

                    // Create directory if it doesn't exist
                    Path uploadPath = Paths.get("src/main/resources/static/uploads/products");
                    if (Files.notExists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    System.out.println("Upload path: " + uploadPath.toAbsolutePath());

                    // Generate unique filename
                    String originalFilename = StringUtils.cleanPath(productImage.getOriginalFilename());
                    String extension = "";
                    if (originalFilename.contains(".")) {
                        extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    }
                    String newFilename = "product_" + savedProduct.getId() + "_" + UUID.randomUUID() + extension;

                    // Save file
                    Path filePath = uploadPath.resolve(newFilename);
                    System.out.println("Saving file to: " + filePath.toAbsolutePath());
                    Files.copy(productImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File saved successfully!");

                    // Generate relative URL
                    String imageUrl = "/uploads/products/" + newFilename;
                    System.out.println("Image URL: " + imageUrl);

                    // Create and save product image
                    ProductImage productImageObj = new ProductImage();
                    productImageObj.setProduct(savedProduct);
                    productImageObj.setImageUrl(imageUrl);
                    productImageObj.setUploadedAt(new Timestamp(System.currentTimeMillis()));

                    // Save the product image
                    ProductImage savedImage = productService.saveProductImage(productImageObj);
                    System.out.println("Saved product image with ID: " + savedImage.getId() + ", URL: " + savedImage.getImageUrl());

                } catch (Exception e) {
                    System.out.println("Error processing image: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("No image provided for product " + savedProduct.getId());
            }

            // Add a success message
            session.setAttribute("successMessage", "Your product has been successfully submitted for review! An employee has to approve" +
                    "the listing before it goes on the store");

            // Add flag to force refresh product images
            session.setAttribute("refreshProducts", true);

            return "redirect:/profile";

        } catch (Exception e) {
            System.out.println("Error creating product: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "There was an error creating your listing: " + e.getMessage());

            // Return to the sell form
            model.addAttribute("brands", brandService.findAll());
            model.addAttribute("clothingArticles", clothingArticleService.findAll());
            model.addAttribute("conditions", productService.findAllProductCondition());

            return "Sell";
        }
    }
}