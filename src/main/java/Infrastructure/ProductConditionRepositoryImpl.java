package Infrastructure;

import Domain.ProductCondition;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductConditionRepositoryImpl implements CrudRepository<ProductCondition> {

    private final JdbcTemplate jdbcTemplate;

    public ProductConditionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductCondition save(ProductCondition condition) {
        String sql = "INSERT INTO product_condition (name) VALUES (?)";
        jdbcTemplate.update(sql, condition.getCondition());
        return condition;
    }

    @Override
    public List<ProductCondition> findAll() {
        String sql = "SELECT * FROM product_condition";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("id"));
            condition.setCondition(rs.getString("name"));
            return condition;
        });
    }

    @Override
    public void update(ProductCondition condition) {
        String sql = "UPDATE product_condition SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, condition.getCondition(), condition.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product_condition WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public ProductCondition findById(int id) {
        String sql = "SELECT * FROM product_condition WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("id"));
            condition.setCondition(rs.getString("name"));
            return condition;
        }, id);
    }
}