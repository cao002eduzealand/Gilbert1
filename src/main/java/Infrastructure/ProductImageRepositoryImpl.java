package Infrastructure;

import Domain.ProductImage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductImageRepositoryImpl implements CrudRepository<ProductImage> {

    private final JdbcTemplate jdbcTemplate;
    
    public ProductImageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        String sql = "INSERT INTO product_image (product_id, image_url, uploaded_at) VALUES (?, ?, ?)";

        System.out.println("Saving product image with product ID: " + productImage.getProduct().getId());

        jdbcTemplate.update(sql,
                productImage.getProduct().getId(),
                productImage.getImageUrl(),
                productImage.getUploadedAt());

        return productImage;
    }
    
    @Override
    public List<ProductImage> findAll() {
        String sql = "SELECT * FROM product_image";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductImage productImage = new ProductImage();
            productImage.setId(rs.getInt("id"));
            productImage.setImageUrl(rs.getString("image_url"));
            productImage.setUploadedAt(rs.getTimestamp("uploaded_at"));
            return productImage;
        });
    }

    public void update(ProductImage productImage) {
        String sql = "UPDATE product_image SET image_url = ?, uploaded_at = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                productImage.getImageUrl(),
                productImage.getUploadedAt(),
                productImage.getId());
    }

    @Override
    public void delete(int id) {
        String deleteImagesSql = "DELETE FROM product_image WHERE product_id = ?";
        jdbcTemplate.update(deleteImagesSql, id);

        String deleteProductSql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(deleteProductSql, id);
    }
    
    @Override
    public ProductImage findById(int id) {
        String sql = "SELECT * FROM product_image WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ProductImage productImage = new ProductImage();
            productImage.setId(rs.getInt("id"));
            productImage.setImageUrl(rs.getString("image_url"));
            productImage.setUploadedAt(rs.getTimestamp("uploaded_at"));
            return productImage;
        }, id);
    }
    
    public List<ProductImage> findByProductId(int productId) {
        String sql = "SELECT * FROM product_image WHERE product_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductImage productImage = new ProductImage();
            productImage.setId(rs.getInt("id"));
            productImage.setImageUrl(rs.getString("image_url"));
            productImage.setUploadedAt(rs.getTimestamp("uploaded_at"));
            return productImage;
        }, productId);
    }
}