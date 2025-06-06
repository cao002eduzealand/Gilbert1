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
import java.util.stream.Collectors;

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
        Map<Category, Map<SubCategory, List<ClothingArticle>>> categorytree = getCategoryTree();
        addCategoryTreeToModel(model, categorytree);
        return "Home";
    }

    @GetMapping("/category/{categoryId}")
    public String getProductsByCategory(@PathVariable int categoryId, Model model) {
        try {
            List<Product> products = productService.findAllByCategoryId(categoryId);
            productService.populateImagesForProducts(products);
            Category category = clothingArticleService.findCategoryById(categoryId);
            populateCategorySearchModel(model, products, category);
        } catch (ProductOperationException| ProductNotfoundException e) {e.getMessage();
        model.addAttribute("errorMessage", e.getMessage());
        }

        return "search";
    }

    @GetMapping("/subcategory/{subCategoryId}")
    public String getProductsBysubcategory(@PathVariable int subCategoryId, Model model) {

        try {
            List<Product> products = productService.findAllBySubCategoryId(subCategoryId);
            SubCategory subCategory = clothingArticleService.findSubCategoryById(subCategoryId);
            Category category = clothingArticleService.findCategoryById(subCategory.getCategory().getId());
            productService.populateImagesForProducts(products);

            populateSubcategorySearchModel(model, products, category, subCategory);
        } catch (ProductOperationException| ProductNotfoundException e) {e.getMessage();
            model.addAttribute("errorMessage", e.getMessage());}

        return "search";
    }

    @GetMapping("/article/{clothingArticleId}")
    public String getProductsByClothingArticle(@PathVariable int clothingArticleId, Model model) {
        try {
            List<Product> products = productService.findAllByClothingArticleId(clothingArticleId);
            populateClothingArticleSearchModel(model, products);
            productService.populateImagesForProducts(products);
        } catch (ProductOperationException| ProductNotfoundException e) {e.getMessage();
        model.addAttribute("errorMessage", e.getMessage());}
        return "search";

    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam(required = false) String query, Model model) {
        try{
            List<Product> products = productService.searchProducts(query);
            productService.populateImagesForProducts(products);

            populateQuerySearchModel(model, products, query);
        } catch (ProductOperationException| ProductNotfoundException e) {e.getMessage();
        model.addAttribute("errorMessage", e.getMessage());}
        return "search";
    }

    // Helper methods
    private List<Category> getAllCategories() {
        return clothingArticleService.findAllCategories();
    }
    private Map<Category, Map<SubCategory, List<ClothingArticle>>> getCategoryTree() {
        List<Category> categories = getAllCategories();
        List<Category> sortedCategories = sortCategoriesByOrder(categories);
        return categoryTree(sortedCategories);
    }
    private List<Category> sortCategoriesByOrder(List<Category> categories) {
        List<String> order = getDesiredCategoryOrder();
        categories.sort(Comparator.comparingInt(cat -> order.indexOf(cat.getName())));
        return categories;
    }

    private List<String> getDesiredCategoryOrder() {
    return List.of("Designer's", "Men's", "Women's", "Home", "Beauty");
    }

    private Map<Category, Map<SubCategory, List<ClothingArticle>>> categoryTree(List<Category> categories) {
        Map<Category, Map<SubCategory, List<ClothingArticle>>> categoryTree = new LinkedHashMap<>();

        for (Category category : categories) {
            Map<SubCategory, List<ClothingArticle>> subcategoryMap = createSubcategoryMap(category);
            categoryTree.put(category, subcategoryMap);
        }
        return categoryTree;
    }

    private Map<SubCategory, List<ClothingArticle>> createSubcategoryMap(Category category) {
        List<SubCategory> subCategories = clothingArticleService.findSubcategoryByCategoryId(category.getId());
        Map<SubCategory, List<ClothingArticle>> subMap = new LinkedHashMap<>();

        for (SubCategory subCategory : subCategories) {
            List<ClothingArticle> articles = clothingArticleService.findBySubCategoryId(subCategory.getId());

            subMap.put(subCategory, articles);
        }

        return subMap;
    }

    private void addCategoryTreeToModel(Model model, Map<Category, Map<SubCategory, List<ClothingArticle>>> categoryTree) {
        model.addAttribute("categoryTree", categoryTree);
    }

    private void populateCategorySearchModel(Model model, List<Product> products, Category category) {
        model.addAttribute("products", products);
        model.addAttribute("searchTitle", buildCategorySearchTitle(category));
        model.addAttribute("searchType", "category");
        model.addAttribute("searchResult", category.getName());
    }

    private void populateSubcategorySearchModel(Model model, List<Product> products, Category category, SubCategory subCategory) {
        model.addAttribute("products", products);
        model.addAttribute("searchTitle", buildSubcategorySearchTitle(category, subCategory));
        model.addAttribute("searchType", "subcategory");
        model.addAttribute("searchResult", subCategory.getName());
    }

    private void populateClothingArticleSearchModel(Model model, List<Product> products) {
        model.addAttribute("products", products);
        model.addAttribute("searchTitle", "Product Search");
        model.addAttribute("searchType", "article");
        model.addAttribute("searchResult", "Clothing Article");
    }

    private void populateQuerySearchModel(Model model, List<Product> products, String query) {
        model.addAttribute("products", products);
        model.addAttribute("searchTitle", "Search Results");
        model.addAttribute("searchType", "query");
        model.addAttribute("searchResult", getQuerySearchResult(query));
    }
    private String buildCategorySearchTitle(Category category) {
        return category.getName() + " products";
    }

    private String buildSubcategorySearchTitle(Category category, SubCategory subCategory) {
        return category.getName() + " • " + subCategory.getName();
    }

    private String getQuerySearchResult(String query) {
        return query != null ? query : "All Products";
    }



}