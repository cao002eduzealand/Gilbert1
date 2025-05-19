package Application;

import Domain.ProductImage;
import Infrastructure.ProductImageRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ServiceInterface<ProductImage> {

    private final ProductImageRepositoryImpl repository;
    
    public ProductImageServiceImpl(ProductImageRepositoryImpl repository) {
        this.repository = repository;
    }
    
    @Override
    public ProductImage save(ProductImage productImage) {
        return repository.save(productImage);
    }
    
    @Override
    public List<ProductImage> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(ProductImage productImage) {
        repository.update(productImage);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public ProductImage findById(int id) {
        return repository.findById(id);
    }
    
    public List<ProductImage> findByProductId(int productId) {
        return repository.findByProductId(productId);
    }
}