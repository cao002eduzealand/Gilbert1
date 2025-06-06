package Infrastructure;

import Domain.Category;
import Domain.ClothingArticle;
import Domain.SubCategory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "SELECT ca.*, sc.name as subcategory_name, sc.category_id " +
                "FROM clothing_article ca " +
                "JOIN subcategory sc ON ca.subcategory_id = sc.id";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ClothingArticle article = new ClothingArticle();
            fillClothingArticle(article, rs);

            return article;
        });
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
        String sql = "SELECT ca.*, sc.name as subcategory_name, sc.category_id " +
                "FROM clothing_article ca " +
                "JOIN subcategory sc ON ca.subcategory_id = sc.id " +
                "WHERE ca.id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ClothingArticle article = new ClothingArticle();
            fillClothingArticle(article, rs);

            return article;
        }, id);
    }

    public List<ClothingArticle> findBySubCategoryId(int id) {
        String sql = "SELECT ca.*, sc.name as subcategory_name, sc.category_id " +
                "FROM clothing_article ca " +
                "JOIN subcategory sc ON ca.subcategory_id = sc.id " +
                "WHERE ca.subcategory_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ClothingArticle article = new ClothingArticle();
            fillClothingArticle(article, rs);

            return article;
        }, id);
    }
    //Category

    public List<Category> findAllCategories() {
        String sql = "select * from category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
    public Category findCategoryById(int id) {
        String sql = "select * from category where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), id);
    }

    //Subcategory

    public List<SubCategory> findAllSubcategories() {
        String sql = "SELECT * FROM subcategory";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SubCategory subCategory = new SubCategory();
            fillSubcategory(subCategory, rs);

            return subCategory;
        });
    }
    public SubCategory findSubcategoryById(int id) {
        String sql = "SELECT * FROM subcategory WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            SubCategory subCategory = new SubCategory();
            fillSubcategory(subCategory, rs);

            return subCategory;
        }, id);
    }


    public List<SubCategory> findSubcategoryByCategoryId(int id) {
        String sql = "SELECT * FROM subcategory WHERE category_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SubCategory subCategory = new SubCategory();
            fillSubcategory(subCategory, rs);

            return subCategory;
        }, id);
    }

    // Helper methods

    private void fillSubcategory(SubCategory subCategory, ResultSet rs) throws SQLException {
        subCategory.setId(rs.getInt("id"));
        subCategory.setName(rs.getString("name"));

        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        subCategory.setCategory(category);
    }

    private void fillClothingArticle(ClothingArticle article, ResultSet rs) throws SQLException {
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));


        SubCategory subcategory = new SubCategory();
        subcategory.setId(rs.getInt("subcategory_id"));
        subcategory.setName(rs.getString("subcategory_name"));


        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        subcategory.setCategory(category);


        article.setSubcategory(subcategory);
    }

}
