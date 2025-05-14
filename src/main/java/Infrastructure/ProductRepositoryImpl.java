package Infrastructure;

import Domain.Brand;
import Domain.Product;
import Domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements CrudRepository<Product> {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
public Product save(Product product) {

        String sql = "INSERT INTO product (brand_id, clothing_article_id, seller_id, status_id, condition_id, model_name," +
                "description, price, date_uploaded) VALUES (?,?,?,?,?,?,?,?,? )";

               jdbcTemplate.update(sql, product.getBrand().getId(), product.getClothingArticle().getId(), product.getSeller().getId(), product.getProductStatus().getId()
               , product.getCondition().getId(), product.getModelName(), product.getDescription(), product.getPrice(), product.getDateUploaded());
                    return product;
    }

    @Override
    public List<Product> findAll(){
    String sql = "SELECT * FROM product";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));

    }

    @Override
    public void update(Product product) {

        String sql = "UPDATE product SET brand_id=?, clothing_article_id=?, seller_id=?, status_id=?, condition_id=?, model_name=?, description=?, price=?, date_uploaded=?" +
                "WHERE id=?";
        jdbcTemplate.update(sql, product.getBrand().getId(), product.getClothingArticle().getId(), product.getSeller().getId(), product.getProductStatus().getId()
                , product.getCondition().getId(), product.getModelName(), product.getDescription(), product.getPrice(), product.getDateUploaded(), product.getId());

    }

    @Override
    public void delete(int id) {

    String sql = "DELETE FROM product WHERE id=?";
    jdbcTemplate.update(sql, id);

    }


    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }

    public List<Product> findAllByBrand(Brand brand) {
        String sql = "SELECT * FROM product p " +
                "JOIN product_status ps ON p.status_id = ps.id " +
                "WHERE p.brand_id = ? AND ps.name = 'listed'";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), brand.getId());

    }

    public List<Product> getProductsByUser(User user){

        String sql = "SELECT * FROM product where user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), user.getId());

}


}
