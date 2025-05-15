package Application;

import Domain.Brand;
import Infrastructure.BrandRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements ServiceInterface<Brand> {

    private final BrandRepositoryImpl repository;

    public BrandServiceImpl(BrandRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Brand save(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Brand brand) {
        repository.update(brand);
    }

    @Override
    public void delete(int id) {
    repository.delete(id);
    }

    @Override
    public Brand findById(int id) {
        return repository.findById(id);
    }
}
