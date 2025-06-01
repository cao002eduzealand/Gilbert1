package Presentation;

import Application.*;
import Domain.*;

import Infrastructure.ProductRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {


    private final ClothingArticleServiceImpl clothingArticleService;
    private final ProductServiceImpl productService;


    public HomeController(ClothingArticleServiceImpl clothingArticleService, ProductServiceImpl productService) {
        this.clothingArticleService = clothingArticleService;
        this.productService = productService;
    }

    @RequestMapping("/Gilbert")

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Category> categories = clothingArticleService.findAllCategories();


        List<String> desiredOrder = List.of("Designer's", "Men's", "Women's", "Home", "Beauty");

        categories.sort(Comparator.comparingInt(cat ->
                desiredOrder.indexOf(cat.getName()))
        );
        Map<Category, Map<SubCategory, List<ClothingArticle>>> categoryTree = new LinkedHashMap<>();

        for (Category category : categories) {
            List<SubCategory> subCategories = clothingArticleService.findSubcategoryByCategoryId(category.getId());
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

    @GetMapping("/category/{categoryId}")
    public String getProductsByCategory(@PathVariable int categoryId, Model model) {
        List<Product> products = productService.findAllByCategoryId(categoryId);
        productService.populateImagesForProducts(products);
        Category category = clothingArticleService.findCategoryById(categoryId);

        model.addAttribute("products", products);
        model.addAttribute("searchTitle", category.getName() + "products");
        model.addAttribute("searchType", "category");
        model.addAttribute("searchResult", category.getName());

        return "search";
    }

    @GetMapping("/subcategory/{subCategoryId}")
    public String getProductsBysubcategory(@PathVariable int subCategoryId, Model model) {
        List<Product> products = productService.findAllBySubCategoryId(subCategoryId);
        SubCategory subCategory = clothingArticleService.findSubCategoryById(subCategoryId);
        Category category = clothingArticleService.findCategoryById(subCategory.getCategory().getId());
        productService.populateImagesForProducts(products);

        model.addAttribute("products", products);
        model.addAttribute("searchTitle", category.getName() + " • " + subCategory.getName());
        model.addAttribute("searchType", "subcategory");
        model.addAttribute("searchResult", subCategory.getName());

        return "search";
    }

    @GetMapping("/article/{clothingArticleId}")
    public String getProductsByClothingArticle(@PathVariable int clothingArticleId, Model model) {
        List<Product> products = productService.findAllByClothingArticleId(clothingArticleId);
        productService.populateImagesForProducts(products);
        model.addAttribute("products", products);
        model.addAttribute("searchTitle", "Product Search");
        model.addAttribute("searchType", "article");
        model.addAttribute("searchResult", "Clothing Article");
        return "search";

    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam(required = false) String query, Model model) {
        List<Product> products = productService.searchProducts(query);
        productService.populateImagesForProducts(products);

        model.addAttribute("products", products);
        model.addAttribute("searchTitle", "Search Results");
        model.addAttribute("searchType", "query");
        model.addAttribute("searchResult", query != null ? query : "All Products");

        return "search";
    }

}