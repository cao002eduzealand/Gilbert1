package Presentation;


import Application.BrandServiceImpl;
import Application.CompanyServiceImpl;
import Application.UserServiceImpl;
import Domain.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserServiceImpl userService;
    private final CompanyServiceImpl companyService;
    public LoginController(UserServiceImpl userService, CompanyServiceImpl companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }


    @GetMapping("/login")
    public String showLoginForm() {
       return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

        try {
            User user = userService.authenticeUser(username, password);
           Company company = companyService.findCompanyByUser(user.getId());
           user.setCompany(company);

            session.setAttribute("user", user);
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

