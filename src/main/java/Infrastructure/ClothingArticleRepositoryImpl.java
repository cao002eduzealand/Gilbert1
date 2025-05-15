package Infrastructure;

import Domain.ClothingArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClothingArticleRepositoryImpl implements CrudRepository<ClothingArticle> {


    private final JdbcTemplate jdbcTemplate;

    public ClothingArticleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public ClothingArticle save(ClothingArticle clothingArtice){
        String sql = "INSERT INTO clothing_article (name, subcategory_id) VALUES (?,?)";

        jdbcTemplate.update(sql, clothingArtice.getName(), clothingArtice.getSubcategory().getId());
        return clothingArtice;
    }

    @Override
    public List<ClothingArticle> findAll() {
       String sql = "SELECT * FROM clothing_article";
       return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ClothingArticle.class));
    }

    @Override
    public void update(ClothingArticle clothingArticle) {
        String sql = "UPDATE clothing_article SET name=?, SET subcategory_id=? WHERE id=?";
        jdbcTemplate.update(sql, clothingArticle.getName(), clothingArticle.getSubcategory().getId(), clothingArticle.getId());

    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM clothing_article WHERE id=?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public ClothingArticle findById(int id) {
        String sql = "SELECT * FROM clothing_article WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ClothingArticle.class), id);
    }

}
