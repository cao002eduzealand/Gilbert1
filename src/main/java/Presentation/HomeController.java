package Presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping
    public String home() {
        return "Home";
    }
    @GetMapping("/sell")
    public String profile() {
        return "Profile";
    }
}
