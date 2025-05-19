package Application;

import Domain.ProductCondition;
import Infrastructure.ProductConditionRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductConditionServiceImpl implements ServiceInterface<ProductCondition> {

    private final ProductConditionRepositoryImpl repository;

    public ProductConditionServiceImpl(ProductConditionRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public ProductCondition save(ProductCondition condition) {
        return repository.save(condition);
    }

    @Override
    public List<ProductCondition> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(ProductCondition condition) {
        repository.update(condition);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public ProductCondition findById(int id) {
        return repository.findById(id);
    }
}