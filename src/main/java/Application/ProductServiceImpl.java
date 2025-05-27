package Application;


import Domain.User;
import Infrastructure.ProductRepositoryImpl;
import org.springframework.stereotype.Service;
import Domain.Product;
import java.util.List;

@Service
public class ProductServiceImpl implements ServiceInterface<Product> {

    private final ProductRepositoryImpl repository;

    public ProductServiceImpl(ProductRepositoryImpl repository){this.repository = repository;}

    @Override
    public Product save(Product product){return repository.save(product);}

    @Override
    public List<Product> findAll(){return repository.findAll();}

    @Override
    public void update(Product product){ repository.update(product);}

    @Override
    public void delete(int id){repository.delete(id);}



    @Override
    public Product findById(int id) {return repository.findById(id);}


    public List<Product> getProductsByUser(User user){return repository.getProductsByUser(user);}


    public List<Product> findAllByCategoryId(int categoryId){
        return repository.findAllByCategoryId(categoryId);
    }

    public List<Product> findAllBySubCategoryId(int subCategoryId){
        return repository.findAllBySubCategoryId(subCategoryId);
    }

    public List<Product> findAllByClothingArticleId(int clothingArticleId){return repository.findAllByClothingArticleId(clothingArticleId);}

    public void populateImagesForProducts(List<Product> products){
       repository.populateImagesForProducts(products);
    }
}



