package Presentation;

import Application.ProductServiceImpl;
import Application.UserServiceImpl;
import Domain.Company;
import Domain.Product;
import Domain.ProductImage;
import Domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProfileController {

    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;
    public ProfileController(UserServiceImpl userService, ProductServiceImpl productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Handle success message
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        // Remove refresh flag if present
        session.removeAttribute("refreshProducts");

        try {
            // Fetch user's listings
            List<Product> userProducts = productService.getProductsByUser(user);
            model.addAttribute("products", userProducts);
        } catch (Exception e) {
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
    Company savedCompany = userService.saveCompany(company);

    userService.updateUserCompany(user.getId(), savedCompany.getId());
    user.setCompany(savedCompany);
    session.setAttribute("user", user);

        return "redirect:/profile";

    }

}