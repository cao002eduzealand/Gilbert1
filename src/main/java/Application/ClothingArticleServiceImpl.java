package Application;

import Domain.Category;
import Domain.ClothingArticle;
import Domain.SubCategory;
import Infrastructure.ClothingArticleRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingArticleServiceImpl implements ServiceInterface<ClothingArticle> {


    private final ClothingArticleRepositoryImpl repository;

    public ClothingArticleServiceImpl(ClothingArticleRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public ClothingArticle save(ClothingArticle clothingArticle) {
        return repository.save(clothingArticle);
    }

    @Override
    public List<ClothingArticle> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(ClothingArticle clothingArticle) {
        repository.update(clothingArticle);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);

    }

    @Override
    public ClothingArticle findById(int id) {
        return repository.findById(id);
    }

    public List<ClothingArticle> findBySubCategoryId(int id) {
        return repository.findBySubCategoryId(id);
    }

    //Category

    public List<Category> findAllCategories(){
        return repository.findAllCategories();
    }

    public Category findCategoryById(int id) {
        return repository.findCategoryById(id);
    }

    //Subcategory

    public List<SubCategory> findAllSubCategories(){
        return repository.findAllSubcategories();
    }

    public SubCategory findSubCategoryById(int id) {
        return repository.findSubcategoryById(id);
    }

    public List<SubCategory> findSubcategoryByCategoryId(int id){
        return repository.findSubcategoryByCategoryId(id);
    }

}
