package Infrastructure;

import Domain.Brand;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepositoryImpl implements CrudRepository<Brand> {

    private final JdbcTemplate jdbcTemplate;

    public BrandRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Brand save(Brand brand) {
        String sql = "INSERT INTO brand(name) VALUES (?)";
        jdbcTemplate.update(sql, brand.getBrandName());
        return brand;
    }

    @Override
    public List<Brand> findAll() {
        String sql = "SELECT * FROM brand";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Brand brand = new Brand();
            brand.setId(rs.getInt("id"));
            brand.setBrandName(rs.getString("name"));
            return brand;
        });
    }

    @Override
    public void update(Brand brand) {
        String sql = "UPDATE brand SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, brand.getBrandName(), brand.getId());
    }

    @Override
    public void delete(int id) {
    String sql = "DELETE FROM brand WHERE id = ?";
    jdbcTemplate.update(sql, id);
    }


    @Override
    public Brand findById(int id) {
        String sql = "SELECT * FROM brand WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Brand brand = new Brand();
            brand.setId(rs.getInt("id"));
            brand.setBrandName(rs.getString("name"));
            return brand;
        }, id);
    }
}
