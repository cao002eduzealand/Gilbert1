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

        populateSellForm(model, user);
        return "Sell";
    }


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
        if (user == null) {
            return "redirect:/login";
        }

        try {
            // Verify that clothing article belongs to selected subcategory
            ClothingArticle clothingArticle = clothingArticleService.findById(clothingArticleId);
            if (clothingArticle.getSubcategory().getId() != subcategoryId) {
                return handleError(model, "Invalid clothing article selection");
            }

            // Create and save product
            Product product = createProduct(user, brandId, clothingArticleId, conditionId, modelName, description, price);
            Product savedProduct = productService.save(product);

            // Handle image upload if provided
            if (productImage != null && !productImage.isEmpty()) {
                handleImageUpload(savedProduct, productImage);
            }

            session.setAttribute("successMessage", "Your product has been successfully submitted for review! An employee has to approve the listing before it goes on the store");
            session.setAttribute("refreshProducts", true);
            return "redirect:/profile";

        } catch (ProductOperationException e) {
            return handleError(model, e.getMessage());
        } catch (ImageUploadException e) {
            return handleError(model, "Failed to upload image: " + e.getMessage());
        } catch (Exception e) {
            return handleError(model, "An unexpected error occurred while creating your listing");
        }
    }
    private Product createProduct(User user, int brandId, int clothingArticleId, int conditionId,
                                  String modelName, String description, double price) {
        try {
            Product product = new Product();
            product.setBrand(brandService.findById(brandId));
            product.setClothingArticle(clothingArticleService.findById(clothingArticleId));
            product.setCondition(productService.findProductConditionById(conditionId));
            product.setModelName(modelName);
            product.setDescription(description);
            product.setPrice(price);
            product.setSeller(user);
            product.setDateUploaded(new Timestamp(System.currentTimeMillis()));
            return product;
        } catch (Exception e) {
            throw new ProductOperationException("Failed to create product", e);
        }
    }

    private void handleImageUpload(Product savedProduct, MultipartFile productImage) {
        try {
            // Create directory if it doesn't exist
            Path uploadPath = Paths.get("src/main/resources/static/uploads/products");
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate unique filename
            String originalFilename = StringUtils.cleanPath(productImage.getOriginalFilename());
            String extension = originalFilename.contains(".") ?
                    originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
            String newFilename = "product_" + savedProduct.getId() + "_" + UUID.randomUUID() + extension;

            // Save file
            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(productImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Create and save product image
            ProductImage productImageObj = new ProductImage();
            productImageObj.setProduct(savedProduct);
            productImageObj.setImageUrl("/uploads/products/" + newFilename);
            productImageObj.setUploadedAt(new Timestamp(System.currentTimeMillis()));
            productService.saveProductImage(productImageObj);

        } catch (Exception e) {
            throw new ImageUploadException("Failed to upload and save product image", e);
        }
    }

    private String handleError(Model model, String errorMessage) {
        model.addAttribute("error", errorMessage);
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("categories", clothingArticleService.findAllCategories());
        model.addAttribute("conditions", productService.findAllProductCondition());
        return "Sell";
    }

    // Helper mthods

    private void populateSellForm(Model model, User user){
        List<Brand> brands = brandService.findAll();
        List<Category> categories = clothingArticleService.findAllCategories();
        List<ProductCondition> conditions = productService.findAllProductCondition();

        List<Category> filteredCategories = categories.stream()
                .filter(category -> !category.getName().equalsIgnoreCase("Designer's"))
                .collect(Collectors.toList());
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("conditions", conditions);

    }

}