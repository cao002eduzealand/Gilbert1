package Presentation;

import Application.UserServiceImpl;
import Domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class ProfileController {



    private final UserServiceImpl userService;

    public ProfileController(UserServiceImpl userService) {
        this.userService = userService;
    }



@GetMapping("/profile")
public String showProfile(HttpSession session, Model model) {
     User user = (User) session.getAttribute("user");
     if (user == null) {
         return "redirect:/login";
     }
     model.addAttribute("user", user);
        return "profile";
}


    @PostMapping("/profile/uploadImage")
    public String uploadProfileImage(@RequestParam("image") MultipartFile image, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null || image.isEmpty()) {
            return "redirect:/profile"; // or some error page
        }

        String newImageUrl = userService.saveProfileImage(user, image);
        user.setProfilePictureURL(newImageUrl);
        session.setAttribute("user", user);
        return "redirect:/profile";

    }
}
