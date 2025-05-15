package Application;

import Domain.ClothingArticle;
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
}
