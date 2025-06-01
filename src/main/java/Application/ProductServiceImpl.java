package Application;


import Domain.*;
import Infrastructure.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

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

    public List<Product> findAllByBrand(Brand brand){return repository.findAllByBrand(brand);}
    public int getStatusIdByName(String statusName){return repository.getStatusIdByName(statusName);}


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

    public List<Product> searchProducts(String query){return repository.searchProducts(query);}

    public ProductCondition findProductConditionById(int id){return repository.findProductConditionById(id);}

    public List<ProductCondition> findAllProductCondition(){return repository.findAllProductCondition();}

    //ProductImage
    public ProductImage saveProductImage(ProductImage productImage){return repository.saveProductImage(productImage);}

    public void updateProductImage(ProductImage productImage){repository.updateProductImage(productImage);}
    public void deleteProductImage(int id){repository.deleteProductImage(id);}
    public ProductImage findProductImageById(int id){return repository.findProductImageById(id);}

    //ProductStatus

    public ProductStatus findProductStatusById(int id){return repository.findProductStatusById(id);}
    public List<ProductStatus> findAllProductStatus(){return repository.findAllProductStatus();}








}



