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
import java.util.UUID;

@Controller
public class ProductController {

    private final ProductServiceImpl productService;
    private final BrandServiceImpl brandService;
    private final ClothingArticleServiceImpl clothingArticleService;

    @Autowired
    public ProductController(ProductServiceImpl productService,
                             BrandServiceImpl brandService,
                             ClothingArticleServiceImpl clothingArticleService) {
        this.productService = productService;
        this.brandService = brandService;
        this.clothingArticleService = clothingArticleService;
    }

    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable("id") int productId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            Product product = productService.findById(productId);


            if (product.getSeller().getId() != user.getId()) {
                return "redirect:/profile";
            }

            // Load data for dropdowns
            populateModelForm(model, product);
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
            @RequestParam("modelName") String modelName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam(value = "productImage", required = false) MultipartFile productImage,
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

            updateProductDetails(product, brandId, clothingArticleId, conditionId, modelName, description, price);

            productService.update(product);

            if (hasProductImage(productImage)) {
                imageUpload(product, productImage);
            }
            return "redirect:/profile";
        } catch (ImageUploadException e) {
            model.addAttribute("errorMessage", "Something went wrong when dealing with Image");
            return "ProductEdit";
        }
    }



        @GetMapping("/product/{id}")
        public String viewProduct ( @PathVariable("id") int productId, Model model, HttpSession session){
            User user = (User) session.getAttribute("user");

            try {
                Product product = productService.findById(productId);


                if (product == null) {
                    return "redirect:/Gilbert";
                }


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

    private void populateModelForm(Model model, Product product) {
        model.addAttribute("product", product);
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("clothingArticles", clothingArticleService.findAll());
        model.addAttribute("conditions", productService.findAllProductCondition());
    }

    private void updateProductDetails(Product product, int brandId, int clothingArticleId, int conditionId, String modelName,
                                      String description, double price) {
        product.setBrand(brandService.findById(brandId));
        product.setClothingArticle(clothingArticleService.findById(clothingArticleId));
        product.setCondition(productService.findProductConditionById(conditionId));
        product.setModelName(modelName);
        product.setDescription(description);
        product.setPrice(price);
    }


    private void createNewImage(Product product, String imageUrl) {
        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setImageUrl(imageUrl);
        productImage.setUploadedAt(new Timestamp(System.currentTimeMillis()));
        productService.saveProductImage(productImage);
    }

    private boolean hasProductImage(MultipartFile image) {
        return image != null && !image.isEmpty();
    }

    private boolean hasExistingImages(Product product) {
        return product.getImages() != null && !product.getImages().isEmpty();
    }

    private void updateCurrentImage(Product product, String imageUrl) {
        ProductImage productImage = product.getImages().get(0);
        productImage.setImageUrl(imageUrl);
        productImage.setUploadedAt(new Timestamp(System.currentTimeMillis()));
        productService.updateProductImage(productImage); // Use update, not save
    }

    private void updateOrCreateImage(Product product, String imageUrl) {
        if (hasExistingImages(product)) {
            updateCurrentImage(product, imageUrl);
        } else {
            createNewImage(product, imageUrl);
        }
    }

    private String saveImage(MultipartFile image, Product product) throws IOException {
        Path uploadPath = Paths.get("src/main/resources/static/uploads/products");
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate unique filename
        String originalFilename = StringUtils.cleanPath(image.getOriginalFilename());
        String extension = originalFilename.contains(".") ?
                originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String newFilename = "product_" + product.getId() + "_" + UUID.randomUUID() + extension;

        // Save file
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/products/" + newFilename;
    }

    private void imageUpload(Product product, MultipartFile image) {
        try {
            String imageUrl = saveImage(image, product); // Fixed parameter order
            updateOrCreateImage(product, imageUrl);
        } catch (Exception e) {
            e.printStackTrace(); // Added proper exception handling
        }
    }
    }




