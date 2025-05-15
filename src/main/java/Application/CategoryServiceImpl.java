package Application;

import Domain.Category;
import Infrastructure.CategoryRepositoryimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryServiceImpl implements ServiceInterface<Category> {

    private final CategoryRepositoryimpl repository;

    public CategoryServiceImpl(CategoryRepositoryimpl repository) {
        this.repository = repository;
    }


    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Category category) {
        repository.update(category);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Category findById(int id) {
        return repository.findById(id);
    }
}
