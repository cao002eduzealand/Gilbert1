package Presentation;

import Application.CompanyServiceImpl;
import Application.ProductServiceImpl;
import Application.UserServiceImpl;
import Domain.Company;
import Domain.Product;
import Domain.ProductImage;
import Domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfileController {

    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;
    private final CompanyServiceImpl companyService;
    public ProfileController(UserServiceImpl userService, ProductServiceImpl productService, CompanyServiceImpl companyService) {
        this.userService = userService;
        this.productService = productService;
        this.companyService = companyService;
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }


        // Check for any success messages
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        // Check if we need to force refresh products
        boolean refreshProducts = session.getAttribute("refreshProducts") != null;
        if (refreshProducts) {
            session.removeAttribute("refreshProducts");
        }

        try {
            // Fetch user's listings
            List<Product> userProducts = productService.getProductsByUser(user);

            // Debug - log product details including images
            System.out.println("User " + user.getUserName() + " has " + userProducts.size() + " products");
            for (Product product : userProducts) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("  Model: " + product.getModelName());

                // Check images
                if (product.getImages() != null) {
                    System.out.println("  Number of images: " + product.getImages().size());
                    for (ProductImage image : product.getImages()) {
                        System.out.println("    Image URL: " + image.getImageUrl());
                    }
                } else {
                    System.out.println("  Images: NULL");
                }
            }

            model.addAttribute("products", userProducts);
        } catch (Exception e) {
            System.out.println("Error loading products: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error loading your products: " + e.getMessage());
            model.addAttribute("products", new ArrayList<Product>());
        }

        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile/uploadImage")
    public String uploadProfileImage(@RequestParam("image") MultipartFile image, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || image.isEmpty()) {
            return "redirect:/profile";
        }

        String imageUrl = userService.saveProfileImage(user, image);
        user.setProfilePictureURL(imageUrl);
        session.setAttribute("user", user);

        return "redirect:/profile";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/profile/createBusiness")
    public String showCreateBusinessForm(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user==null){
            return "redirect:/login";
        }
        Company company = user.getCompany();
        if (company==null){
            company = new Company();
        }
        model.addAttribute("company", company);
        return "CompanyForm";
    }

    @PostMapping("/profile/createBusiness")
    public String createCompany(@ModelAttribute Company company, HttpSession session){

    User user = (User) session.getAttribute("user");
    if (user==null){
        return "redirect:/login";
    }
    Company savedCompany = companyService.save(company);

    userService.updateUserCompany(user.getId(), savedCompany.getId());
    user.setCompany(savedCompany);
    session.setAttribute("user", user);

        return "redirect:/profile";

    }

}