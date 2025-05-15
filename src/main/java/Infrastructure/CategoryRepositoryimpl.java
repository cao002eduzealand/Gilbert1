package Infrastructure;

import Domain.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryimpl implements CrudRepository<Category> {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Category save(Category category) {
       String sql = "insert into category (name) values (?)";

       jdbcTemplate.update(sql, category.getName());
       return category;
    }

    @Override
    public List<Category> findAll() {
        String sql = "select * from category";
      return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public void update(Category category) {
        String sql = "update category set name=? where id=?";
        jdbcTemplate.update(sql, category.getName(), category.getId());
    }

    @Override
    public void delete(int id) {
    String sql = "delete from category where id=?";
    jdbcTemplate.update(sql, id);
    }

    @Override
    public Category findById(int id) {
        String sql = "select * from category where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), id);
    }
}
