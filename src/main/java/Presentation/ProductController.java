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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {

    private final ProductServiceImpl productService;
    private final BrandServiceImpl brandService;
    private final ClothingArticleServiceImpl clothingArticleService;
    private final ProductConditionServiceImpl conditionService;
    private final ProductStatusServiceImpl statusService;
    private final ProductImageServiceImpl productImageService;

    @Autowired
    public ProductController(ProductServiceImpl productService,
                          BrandServiceImpl brandService,
                          ClothingArticleServiceImpl clothingArticleService,
                          ProductConditionServiceImpl conditionService,
                          ProductStatusServiceImpl statusService, 
                          ProductImageServiceImpl productImageService) {
        this.productService = productService;
        this.brandService = brandService;
        this.clothingArticleService = clothingArticleService;
        this.conditionService = conditionService;
        this.statusService = statusService;
        this.productImageService = productImageService;
    }

    @GetMapping("/product/edit/{id}")  // This should match your profile.html link
    public String showEditForm(@PathVariable("id") int productId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        try {
            Product product = productService.findById(productId);
            
            // Check if the product belongs to the logged-in user
            if (product.getSeller().getId() != user.getId()) {
                return "redirect:/profile";
            }
            
            // Load data for dropdowns
            model.addAttribute("brands", brandService.findAll());
            model.addAttribute("clothingArticles", clothingArticleService.findAll());
            model.addAttribute("conditions", conditionService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            
            model.addAttribute("product", product);
            return "ProductEdit";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/profile";
        }
    }
    
    @PostMapping("/product/update")
    public String updateProduct(
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId,
            @RequestParam("clothingArticleId") int clothingArticleId,
            @RequestParam("conditionId") int conditionId,
            @RequestParam("statusId") int statusId,
            @RequestParam("modelName") String modelName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam(value = "productImage", required = false) MultipartFile productImage,
            @RequestParam("action") String action,
            HttpSession session,
            Model model) {
        
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        try {
            Product product = productService.findById(productId);
            
            // Check if the product belongs to the logged-in user
            if (product.getSeller().getId() != user.getId()) {
                return "redirect:/profile";
            }
            
            // Handle different actions
            if ("delete".equals(action)) {
                // Delete the product
                productService.delete(productId);
                session.setAttribute("successMessage", "Product has been successfully deleted.");
                return "redirect:/profile";
            } else if ("markSold".equals(action)) {
                // Find the "Sold" status and update the product
                List<ProductStatus> statuses = statusService.findAll();
                ProductStatus soldStatus = null;
                for (ProductStatus status : statuses) {
                    if (status.getStatus().toLowerCase().contains("sold")) {
                        soldStatus = status;
                        break;
                    }
                }
                
                if (soldStatus != null) {
                    product.setProductStatus(soldStatus);
                    productService.update(product);
                    session.setAttribute("successMessage", "Product has been marked as sold.");
                    return "redirect:/profile";
                } else {
                    model.addAttribute("errorMessage", "Could not find 'Sold' status in the database.");
                }
            } else if ("update".equals(action)) {
                // Update product information
                product.setBrand(brandService.findById(brandId));
                product.setClothingArticle(clothingArticleService.findById(clothingArticleId));
                product.setCondition(conditionService.findById(conditionId));
                product.setProductStatus(statusService.findById(statusId));
                product.setModelName(modelName);
                product.setDescription(description);
                product.setPrice(price);
                
                // Update the product in database
                productService.update(product);
                
                // Handle image upload if provided
                if (productImage != null && !productImage.isEmpty()) {
                    try {
                        // Create directory if it doesn't exist
                        Path uploadPath = Paths.get("src/main/resources/static/uploads/products");
                        if (Files.notExists(uploadPath)) {
                            Files.createDirectories(uploadPath);
                        }
                        
                        // Generate unique filename
                        String originalFilename = StringUtils.cleanPath(productImage.getOriginalFilename());
                        String extension = "";
                        if (originalFilename.contains(".")) {
                            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                        }
                        String newFilename = "product_" + product.getId() + "_" + UUID.randomUUID() + extension;
                        
                        // Save file
                        Path filePath = uploadPath.resolve(newFilename);
                        Files.copy(productImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                        
                        // Generate relative URL
                        String imageUrl = "/uploads/products/" + newFilename;
                        
                        // Handle existing images
                        if (product.getImages() != null && !product.getImages().isEmpty()) {
                            // Update existing image
                            ProductImage existingImage = product.getImages().get(0);
                            existingImage.setImageUrl(imageUrl);
                            existingImage.setUploadedAt(new Timestamp(System.currentTimeMillis()));
                            productImageService.update(existingImage);
                        } else {
                            // Create and save new product image
                            ProductImage productImageObj = new ProductImage();
                            productImageObj.setProduct(product);
                            productImageObj.setImageUrl(imageUrl);
                            productImageObj.setUploadedAt(new Timestamp(System.currentTimeMillis()));
                            productImageService.save(productImageObj);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
                session.setAttribute("successMessage", "Product has been successfully updated.");
                return "redirect:/profile";
            }
            
            // If we get here, reload the edit form with the product data
            model.addAttribute("brands", brandService.findAll());
            model.addAttribute("clothingArticles", clothingArticleService.findAll());
            model.addAttribute("conditions", conditionService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            model.addAttribute("product", product);
            return "ProductEdit";
            
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "redirect:/profile";
        }
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable("id") int productId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        try {
            Product product = productService.findById(productId);

            // Check if the product or seller is null
            if (product == null) {
                return "redirect:/Gilbert";
            }

            // Set isOwner flag based on whether the logged-in user is the seller
            boolean isOwner = false;
            if (user != null && product.getSeller() != null) {
                isOwner = user.getId() == product.getSeller().getId();
            }

            model.addAttribute("product", product);
            model.addAttribute("isOwner", isOwner);
            return "ProductView";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Gilbert";
        }
    }




}
