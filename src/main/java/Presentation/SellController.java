package Presentation;


import Application.ProductServiceImpl;
import Domain.Product;
import Domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellController {

    private final ProductServiceImpl productService;

    public SellController(ProductServiceImpl productService){
        this.productService=productService;
    }

    @GetMapping("/Sell")
    public String showSellForm(HttpSession session, Model model){

        User user = (User) session.getAttribute("user");
        if (user==null){
            return "redirect:/login";
        }
        model.addAttribute("product", new Product());
        model.addAttribute("user", user);
        return "Sell";
    }
    @PostMapping("/ProductReview")
    public String showProductReview(HttpSession session, Model model, Product product){
        User user = (User) session.getAttribute("user");
        if (user==null){
            return "redirect:/login";
        }

        product.setSeller(user);
        productService.save(product);
        return "ProductReview";
    }


}
