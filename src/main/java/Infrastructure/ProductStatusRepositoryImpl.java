package Infrastructure;

import Domain.ProductStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductStatusRepositoryImpl implements CrudRepository<ProductStatus> {

    private final JdbcTemplate jdbcTemplate;

    public ProductStatusRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductStatus save(ProductStatus status) {
        String sql = "INSERT INTO product_status (name) VALUES (?)";
        jdbcTemplate.update(sql, status.getStatus());
        return status;
    }

    @Override
    public List<ProductStatus> findAll() {
        String sql = "SELECT * FROM product_status";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductStatus status = new ProductStatus();
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("name"));
            return status;
        });
    }

    @Override
    public void update(ProductStatus status) {
        String sql = "UPDATE product_status SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, status.getStatus(), status.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product_status WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public ProductStatus findById(int id) {
        String sql = "SELECT * FROM product_status WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ProductStatus status = new ProductStatus();
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("name"));
            return status;
        }, id);
    }
}