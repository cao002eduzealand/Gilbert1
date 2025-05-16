package Presentation;

import Application.UserServiceImpl;
import Domain.EmailAlreadyTakenException;
import Domain.User;
import Domain.UsernameAlreadyTakenException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("/Gilbert")
    public String home(HttpSession session, Model model) {
        return "Home";
    }

}