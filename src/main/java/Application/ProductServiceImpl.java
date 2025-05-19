package Application;

import Domain.Brand;
import Domain.ProductCondition;
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

    public List<Product> findAllByBrand(Brand brand){return repository.findAllByBrand(brand);}

    public List<Product> getProductsByUser(User user){return repository.getProductsByUser(user);}

    public List<ProductCondition> findAllProductConditions(){
      return  repository.findAllProductConditions();
    }
}
