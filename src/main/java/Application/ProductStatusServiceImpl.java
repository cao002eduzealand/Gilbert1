package Application;

import Domain.ProductStatus;
import Infrastructure.ProductStatusRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStatusServiceImpl implements ServiceInterface<ProductStatus> {

    private final ProductStatusRepositoryImpl repository;

    public ProductStatusServiceImpl(ProductStatusRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public ProductStatus save(ProductStatus status) {
        return repository.save(status);
    }

    @Override
    public List<ProductStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(ProductStatus status) {
        repository.update(status);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public ProductStatus findById(int id) {
        return repository.findById(id);
    }
}