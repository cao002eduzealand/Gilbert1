package Infrastructure;

import Domain.Category;
import Domain.SubCategory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubCategoryRepositoryImpl implements CrudRepository<SubCategory> {


    private final JdbcTemplate jdbcTemplate;

    public SubCategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SubCategory save(SubCategory subCategory) {
        String sql = "INSERT INTO subcategory (name, category_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, subCategory.getName(), subCategory.getCategory().getId());
        return subCategory;
    }

    @Override
    public List<SubCategory> findAll() {
        String sql = "SELECT * FROM subcategory";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(rs.getInt("id"));
            subCategory.setName(rs.getString("name"));

            Category category = new Category();
            category.setId(rs.getInt("category_id"));
            subCategory.setCategory(category);

            return subCategory;
        });
    }

    @Override
    public void update(SubCategory subCategory) {
        String sql = "UPDATE subcategory SET name = ?, category_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, subCategory.getName(), subCategory.getCategory().getId(), subCategory.getId());

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM subcategory WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public SubCategory findById(int id) {
        String sql = "SELECT * FROM subcategory WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(rs.getInt("id"));
            subCategory.setName(rs.getString("name"));

            Category category = new Category();
            category.setId(rs.getInt("category_id")); // Kun ID sættes – resten kan hentes senere hvis nødvendigt
            subCategory.setCategory(category);

            return subCategory;
        }, id);
    }


    public List<SubCategory> findByCategoryId(int id) {
        String sql = "SELECT * FROM subcategory WHERE category_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(rs.getInt("id"));
            subCategory.setName(rs.getString("name"));

            Category category = new Category();
            category.setId(rs.getInt("category_id"));
            subCategory.setCategory(category);

            return subCategory;
        }, id);
    }
}
