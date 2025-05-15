package Application;

import Domain.SubCategory;
import Infrastructure.SubCategoryRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements ServiceInterface<SubCategory> {

    private final SubCategoryRepositoryImpl repository;

    public SubCategoryServiceImpl(SubCategoryRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public SubCategory save(SubCategory subCategory) {
        return repository.save(subCategory);
    }

    @Override
    public List<SubCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(SubCategory subCategory) {
    repository.update(subCategory);
    }

    @Override
    public void delete(int id) {
       repository.delete(id);
    }

    @Override
    public SubCategory findById(int id) {
        return repository.findById(id);
    }
}
