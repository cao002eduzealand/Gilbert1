package Presentation;


import Application.UserServiceImpl;
import Domain.EmailAlreadyTakenException;
import Domain.InvalidCredentialsException;
import Domain.User;
import Domain.UsernameAlreadyTakenException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserServiceImpl userService;

    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String profile(HttpSession session) {
        session.getAttribute("user");
        if (session.getAttribute("user") == null) {
            return "Login";
        }
        return "Profile";
    }

    @PostMapping("/login")
        public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

        try {
            User user = userService.authenticeUser(username, password);
            session.setAttribute("user", user);
        } catch (InvalidCredentialsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "Login";
        }
        return "redirect:/login";
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
        } catch(EmailAlreadyTakenException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "Register";
        } catch(UsernameAlreadyTakenException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "Register";
        }

        return "redirect:/Gilbert";
    }
}


