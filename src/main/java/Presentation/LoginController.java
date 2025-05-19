package Presentation;


import Application.BrandServiceImpl;
import Application.UserServiceImpl;
import Domain.EmailAlreadyTakenException;
import Domain.InvalidCredentialsException;
import Domain.User;
import Domain.UsernameAlreadyTakenException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserServiceImpl userService;
    private final BrandServiceImpl brandService;
    public LoginController(UserServiceImpl userService, BrandServiceImpl brandService) {
        this.userService = userService;
        this.brandService=brandService;
    }


    @GetMapping("/login")
    public String showLoginForm() {
       return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

        try {
            User user = userService.authenticeUser(username, password);
            session.setAttribute("user", user);
            brandService.saveBrands();
            return "redirect:/Gilbert";

        } catch (InvalidCredentialsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "Login";
        }

    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }


    @PostMapping("/register")
    public String registerSave(@ModelAttribute User user, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "Register";
        }
        try {
            User savedUser = userService.save(user);
            session.setAttribute("user", savedUser);
            return "redirect:/Gilbert";

        } catch (UsernameAlreadyTakenException | EmailAlreadyTakenException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "Register";
        }
    }



}

