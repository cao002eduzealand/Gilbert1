package Presentation;

import Application.CategoryServiceImpl;
import Application.ClothingArticleServiceImpl;
import Application.SubCategoryServiceImpl;
import Application.UserServiceImpl;
import Domain.*;
import Infrastructure.ClothingArticleRepositoryImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {



    private final CategoryServiceImpl categoryService;
    private final SubCategoryServiceImpl subCategoryService;
    private final ClothingArticleServiceImpl clothingArticleService;

    public HomeController(CategoryServiceImpl categoryService, SubCategoryServiceImpl subCategoryService, ClothingArticleServiceImpl clothingArticleService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.clothingArticleService = clothingArticleService;
    }
    @RequestMapping("/Gilbert")

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Category> categories = categoryService.findAll();


        List<String> desiredOrder = List.of("Designer's", "Men's", "Women's", "Home", "Beauty");

        categories.sort(Comparator.comparingInt(cat ->
                desiredOrder.indexOf(cat.getName()))
        );
        Map<Category, Map<SubCategory, List<ClothingArticle>>> categoryTree = new LinkedHashMap<>();

        for (Category category : categories) {
            List<SubCategory> subCategories = subCategoryService.findByCategoryId(category.getId());
            Map<SubCategory, List<ClothingArticle>> subMap = new LinkedHashMap<>();

            for (SubCategory sub : subCategories) {
                List<ClothingArticle> articles = clothingArticleService.findBySubCategoryId(sub.getId());
                subMap.put(sub, articles);
            }

            categoryTree.put(category, subMap);
        }

        model.addAttribute("categoryTree", categoryTree);
        return "Home";
    }





}