package Presentation;

import Application.UserServiceImpl;
import Domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/profile/uploadImage")
    public String uploadProfileImage(@RequestParam("image") MultipartFile image,
                                     HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");

        if (user != null && !image.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path uploadPath = Paths.get("src/main/resources/static/uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(filename);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Gem i DB
            user.setProfilePictureURL("/uploads/" + filename);
            userService.updateProfilePicture(user.getId(), "/uploads/" + filename);

            // Opdater i session
            session.setAttribute("user", user);
        }

        return "redirect:/login";
    }
}
