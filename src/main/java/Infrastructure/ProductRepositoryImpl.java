package Infrastructure;

import Domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements CrudRepository<Product> {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void fillProductInsertParameters(Product product, PreparedStatement ps) throws SQLException {
        ps.setInt(1, product.getBrand().getId());
        ps.setInt(2, product.getClothingArticle().getId());
        ps.setInt(3, product.getSeller().getId());
        ps.setInt(4,1);
        ps.setInt(5, product.getCondition().getId());
        ps.setString(6, product.getModelName());
        ps.setString(7, product.getDescription());
        ps.setDouble(8, product.getPrice());
        ps.setTimestamp(9, product.getDateUploaded());

    }


    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (brand_id, clothing_article_id, seller_id, status_id, condition_id, model_name," +
                "description, price, date_uploaded) VALUES (?,?,?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            fillProductInsertParameters(product, ps);
            return ps;
        }, keyHolder);


        product.setId(keyHolder.getKey().intValue());

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
        Product product = fetchBasicProductInfo(id);
        populateProductDetails(product);
        return product;
    }
    private void setProductInfo(Product product, ResultSet rs) throws SQLException {
        product.setId(rs.getInt("id"));
        product.setModelName(rs.getString("model_name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setDateUploaded(rs.getTimestamp("date_uploaded"));

        // Set placeholder objects with IDs
        setSeller(product, rs.getInt("seller_id"));
        setBrand(product, rs.getInt("brand_id"));
        setClothingArticle(product, rs.getInt("clothing_article_id"));
        setCondition(product, rs.getInt("condition_id"));
        setStatus(product, rs.getInt("status_id"));
    }

    private Product fetchBasicProductInfo(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Product p = new Product();
            setProductInfo(p, rs);

            return p;
        }, id);
    }

    private void setSeller(Product product, int sellerId) {
        if (sellerId > 0) {
            User seller = new User();
            seller.setId(sellerId);
            product.setSeller(seller);
        }
    }

    private void setBrand(Product product, int brandId) {
        if (brandId > 0) {
            Brand brand = new Brand();
            brand.setId(brandId);
            product.setBrand(brand);
        }
    }

    private void setClothingArticle(Product product, int articleId) {
        if (articleId > 0) {
            ClothingArticle article = new ClothingArticle();
            article.setId(articleId);
            product.setClothingArticle(article);
        }
    }

    private void setCondition(Product product, int conditionId) {
        if (conditionId > 0) {
            ProductCondition condition = new ProductCondition();
            condition.setId(conditionId);
            product.setCondition(condition);
        }
    }

    private void setStatus(Product product, int statusId) {
        if (statusId > 0) {
            ProductStatus status = new ProductStatus();
            status.setId(statusId);
            product.setProductStatus(status);
        }
    }

    private void populateProductDetails(Product product) {
        populateSellerDetails(product);
        populateBrandDetails(product);
        populateClothingArticleDetails(product);
        populateConditionDetails(product);
        populateStatusDetails(product);
        populateProductImages(product);
    }

    private void populateSellerDetails(Product product) {
        if (product.getSeller() != null) {
            String sql = "SELECT * FROM user WHERE id = ?";
            try {
                User seller = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), product.getSeller().getId());
                product.setSeller(seller);
            } catch (Exception e) {
                // Handle error silently
            }
        }
    }

    private void populateBrandDetails(Product product) {
        if (product.getBrand() != null) {
            String sql = "SELECT * FROM brand WHERE id = ?";
            try {
                Brand brand = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    Brand b = new Brand();
                    b.setId(rs.getInt("id"));
                    b.setBrandName(rs.getString("name"));
                    return b;
                }, product.getBrand().getId());
                product.setBrand(brand);
            } catch (Exception e) {

            }
        }
    }

    private void populateClothingArticleDetails(Product product) {
        if (product.getClothingArticle() != null) {
            String sql = "SELECT * FROM clothing_article WHERE id = ?";
            try {
                ClothingArticle article = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    ClothingArticle a = new ClothingArticle();
                    a.setId(rs.getInt("id"));
                    a.setName(rs.getString("name"));
                    return a;
                }, product.getClothingArticle().getId());
                product.setClothingArticle(article);
            } catch (Exception e) {

            }
        }
    }

    private void populateConditionDetails(Product product) {
        if (product.getCondition() != null) {
            String sql = "SELECT * FROM product_condition WHERE id = ?";
            try {
                ProductCondition condition = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    ProductCondition c = new ProductCondition();
                    c.setId(rs.getInt("id"));
                    c.setCondition(rs.getString("name"));
                    return c;
                }, product.getCondition().getId());
                product.setCondition(condition);
            } catch (Exception e) {
                // Handle error silently
            }
        }
    }

    private void populateStatusDetails(Product product) {
        if (product.getProductStatus() != null) {
            String sql = "SELECT * FROM product_status WHERE id = ?";
            try {
                ProductStatus status = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    ProductStatus s = new ProductStatus();
                    s.setId(rs.getInt("id"));
                    s.setStatus(rs.getString("name"));
                    return s;
                }, product.getProductStatus().getId());
                product.setProductStatus(status);
            } catch (Exception e) {
                // Handle error silently
            }
        }
    }

    private void populateProductImages(Product product) {
        String sql = "SELECT * FROM product_image WHERE product_id = ?";
        List<ProductImage> images = jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductImage image = new ProductImage();
            image.setId(rs.getInt("id"));
            image.setImageUrl(rs.getString("image_url"));
            image.setUploadedAt(rs.getTimestamp("uploaded_at"));
            return image;
        }, product.getId());

        product.setImages(images);
    }

    public List<Product> getProductsByUser(User user) {
        // Fetch basic product information with all related data in one query
        String sql = "SELECT FROM product WHERE seller_id = ?";

        List<Product> products = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product product = new Product();
            setProductInfo(product, rs);

            return product;
        }, user.getId());

        for (Product product : products) {
            populateBrandDetails(product);
            populateClothingArticleDetails(product);
            populateConditionDetails(product);
            populateProductImages(product);

        }
        return products;
    }

    public List<Product> findAllByBrand(Brand brand) {
        String sql = "SELECT * FROM product p " +
                "JOIN product_status ps ON p.status_id = ps.id " +
                "WHERE p.brand_id = ? AND ps.name = 'listed'";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), brand.getId());

    }

    // Searching for categorytree dropdowns
    public int getStatusIdByName(String statusName) {
        String sql = "SELECT id FROM product_status WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{statusName}, Integer.class);
        } catch (Exception e) {
            System.out.println("Status not found for name: " + statusName);
            return -1;
        }
    }
    private Integer listedStatusId = null;

    private int getListedStatusId() {
        if (listedStatusId == null) {
            listedStatusId = getStatusIdByName("listed");
        }
        return listedStatusId;
    }



    public List<Product> findAllByCategoryId(int categoryId) {
        int statusId = getListedStatusId();
        if (statusId == -1) return Collections.emptyList();

        String sql = "SELECT " +
                "p.id AS product_id, p.model_name, p.description, p.price, " +
                "b.id AS brand_id, b.name AS brand_name, " +
                "c.id AS condition_id, c.name AS condition_name, " +
                "a.id AS article_id, a.name AS article_name " +
                "FROM product p " +
                "LEFT JOIN brand b ON p.brand_id = b.id " +
                "LEFT JOIN product_condition c ON p.condition_id = c.id " +
                "LEFT JOIN clothing_article a ON p.clothing_article_id = a.id " +
                "LEFT JOIN subcategory s ON a.subcategory_id = s.id " +
                "LEFT JOIN category cat ON s.category_id = cat.id " +
                "WHERE cat.id = ? AND p.status_id = ?";

        return jdbcTemplate.query(sql, new Object[]{categoryId, statusId}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("product_id"));
            product.setModelName(rs.getString("model_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));

            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            brand.setBrandName(rs.getString("brand_name"));
            product.setBrand(brand);

            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("condition_id"));
            condition.setCondition(rs.getString("condition_name"));
            product.setCondition(condition);

            ClothingArticle article = new ClothingArticle();
            article.setId(rs.getInt("article_id"));
            article.setName(rs.getString("article_name"));
            product.setClothingArticle(article);

            return product;
        });
    }


    public List<Product> findAllBySubCategoryId(int subcategoryId) {
        int statusId = getListedStatusId();
        if (statusId == -1) return Collections.emptyList();

        String sql = """
        SELECT
            p.id AS product_id,
            p.model_name,
            p.description,
            p.price,

            b.id AS brand_id,
            b.name AS brand_name,

            c.id AS condition_id,
            c.name AS condition_name,

            a.id AS article_id,
            a.name AS article_name

        FROM product p
        LEFT JOIN brand b ON p.brand_id = b.id
        LEFT JOIN product_condition c ON p.condition_id = c.id
        LEFT JOIN clothing_article a ON p.clothing_article_id = a.id
        WHERE a.subcategory_id = ? AND p.status_id = ?
    """;

        return jdbcTemplate.query(sql, new Object[]{subcategoryId, statusId}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("product_id"));
            product.setModelName(rs.getString("model_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));

            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            brand.setBrandName(rs.getString("brand_name"));
            product.setBrand(brand);

            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("condition_id"));
            condition.setCondition(rs.getString("condition_name"));
            product.setCondition(condition);

            ClothingArticle article = new ClothingArticle();
            article.setId(rs.getInt("article_id"));
            article.setName(rs.getString("article_name"));
            product.setClothingArticle(article);

            return product;
        });
    }




    public List<Product> findAllByClothingArticleId(int clothingArticleId) {
        int statusId = getListedStatusId();
        if (statusId == -1) return Collections.emptyList();

        String sql = """
        SELECT
            p.id AS product_id,
            p.model_name,
            p.description,
            p.price,

            b.id AS brand_id,
            b.name AS brand_name,

            c.id AS condition_id,
            c.name AS condition_name,

            a.id AS article_id,
            a.name AS article_name

        FROM product p
        LEFT JOIN brand b ON p.brand_id = b.id
        LEFT JOIN product_condition c ON p.condition_id = c.id
        LEFT JOIN clothing_article a ON p.clothing_article_id = a.id
        WHERE p.clothing_article_id = ? AND p.status_id = ?
    """;

        return jdbcTemplate.query(sql, new Object[]{clothingArticleId, statusId}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("product_id"));
            product.setModelName(rs.getString("model_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));

            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            brand.setBrandName(rs.getString("brand_name"));
            product.setBrand(brand);

            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("condition_id"));
            condition.setCondition(rs.getString("condition_name"));
            product.setCondition(condition);

            ClothingArticle article = new ClothingArticle();
            article.setId(rs.getInt("article_id"));
            article.setName(rs.getString("article_name"));
            product.setClothingArticle(article);

            return product;
        });
    }


    public void populateImagesForProducts(List<Product> products) {
        for (Product product : products) {
            try {
                String sql = "SELECT * FROM product_image WHERE product_id = ?";
                List<ProductImage> images = jdbcTemplate.query(sql, (rs, rowNum) -> {
                    ProductImage img = new ProductImage();
                    img.setId(rs.getInt("id"));
                    img.setImageUrl(rs.getString("image_url"));
                    img.setUploadedAt(rs.getTimestamp("uploaded_at"));
                    return img;
                }, product.getId());

                product.setImages(images);
            } catch (Exception e) {
                product.setImages(Collections.emptyList());
            }
        }
    }

    public List<Product> findAllListed() {
        int statusId = getListedStatusId();
        if (statusId == -1) return Collections.emptyList();

        String sql = """
        SELECT
            p.id AS product_id,
            p.model_name,
            p.description,
            p.price,

            b.id AS brand_id,
            b.name AS brand_name,

            c.id AS condition_id,
            c.name AS condition_name,

            a.id AS article_id,
            a.name AS article_name

        FROM product p
        LEFT JOIN brand b ON p.brand_id = b.id
        LEFT JOIN product_condition c ON p.condition_id = c.id
        LEFT JOIN clothing_article a ON p.clothing_article_id = a.id
        WHERE p.status_id = ?
    """;

        return jdbcTemplate.query(sql, new Object[]{statusId}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("product_id"));
            product.setModelName(rs.getString("model_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));

            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            brand.setBrandName(rs.getString("brand_name"));
            product.setBrand(brand);

            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("condition_id"));
            condition.setCondition(rs.getString("condition_name"));
            product.setCondition(condition);

            ClothingArticle article = new ClothingArticle();
            article.setId(rs.getInt("article_id"));
            article.setName(rs.getString("article_name"));
            product.setClothingArticle(article);

            return product;
        });
    }


    public List<Product> searchProducts(String query) {
        int statusId = getListedStatusId();
        if (statusId == -1) {
            return Collections.emptyList();
        }

        if (query == null || query.trim().isEmpty()) {
            // Fallback to all listed products
            return findAllListed();
        }

        String likeQuery = "%" + query.trim().toLowerCase() + "%";

        String sql = """
        SELECT
            p.id AS product_id,
            p.model_name,
            p.description,
            p.price,

            b.id AS brand_id,
            b.name AS brand_name,

            c.id AS condition_id,
            c.name AS condition_name,

            a.id AS article_id,
            a.name AS article_name

        FROM product p
        LEFT JOIN brand b ON p.brand_id = b.id
        LEFT JOIN product_condition c ON p.condition_id = c.id
        LEFT JOIN clothing_article a ON p.clothing_article_id = a.id
        LEFT JOIN subcategory s ON a.subcategory_id = s.id
        LEFT JOIN category cat ON s.category_id = cat.id
        WHERE p.status_id = ?
          AND (
              LOWER(p.model_name) LIKE ?
              OR LOWER(p.description) LIKE ?
              OR LOWER(b.name) LIKE ?
              OR LOWER(a.name) LIKE ?
              OR LOWER(s.name) LIKE ?
              OR LOWER(cat.name) LIKE ?
          )
        ORDER BY
            CASE 
                WHEN LOWER(p.model_name) LIKE ? THEN 1
                WHEN LOWER(p.description) LIKE ? THEN 2
                WHEN LOWER(b.name) LIKE ? THEN 3
                WHEN LOWER(a.name) LIKE ? THEN 4
                WHEN LOWER(s.name) LIKE ? THEN 5
                WHEN LOWER(cat.name) LIKE ? THEN 6
                ELSE 7
            END,
            p.date_uploaded DESC
    """;

        Object[] params = new Object[] {
                statusId,                      // Filter listed only
                likeQuery, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery,
                likeQuery, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery
        };

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("product_id"));
            product.setModelName(rs.getString("model_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));

            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            brand.setBrandName(rs.getString("brand_name"));
            product.setBrand(brand);

            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("condition_id"));
            condition.setCondition(rs.getString("condition_name"));
            product.setCondition(condition);

            ClothingArticle article = new ClothingArticle();
            article.setId(rs.getInt("article_id"));
            article.setName(rs.getString("article_name"));
            product.setClothingArticle(article);

            return product;
        });
    }


    //ProductCondition

    public ProductCondition findProductConditionById(int id) {
        String sql = "SELECT * FROM product_condition WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("id"));
            condition.setCondition(rs.getString("name"));
            return condition;
        }, id);
    }
    public List<ProductCondition> findAllProductCondition() {
        String sql = "SELECT * FROM product_condition";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("id"));
            condition.setCondition(rs.getString("name"));
            return condition;
        });
    }


    //ProductImage
    public ProductImage saveProductImage(ProductImage productImage) {
        String sql = "INSERT INTO product_image (product_id, image_url, uploaded_at) VALUES (?, ?, ?)";

        System.out.println("Saving product image with product ID: " + productImage.getProduct().getId());

        jdbcTemplate.update(sql,
                productImage.getProduct().getId(),
                productImage.getImageUrl(),
                productImage.getUploadedAt());

        return productImage;
    }


    public void updateProductImage(ProductImage productImage) {
        String sql = "UPDATE product_image SET image_url = ?, uploaded_at = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                productImage.getImageUrl(),
                productImage.getUploadedAt(),
                productImage.getId());
    }


    public void deleteProductImage(int id) {
        String deleteImagesSql = "DELETE FROM product_image WHERE product_id = ?";
        jdbcTemplate.update(deleteImagesSql, id);

        String deleteProductSql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(deleteProductSql, id);
    }


    public ProductImage findProductImageById(int id) {
        String sql = "SELECT * FROM product_image WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ProductImage productImage = new ProductImage();
            productImage.setId(rs.getInt("id"));
            productImage.setImageUrl(rs.getString("image_url"));
            productImage.setUploadedAt(rs.getTimestamp("uploaded_at"));
            return productImage;
        }, id);
    }

    //ProductStatus

    public ProductStatus findProductStatusById(int id) {
        String sql = "SELECT * FROM product_status WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ProductStatus status = new ProductStatus();
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("name"));
            return status;
        }, id);
    }
    public List<ProductStatus> findAllProductStatus() {
        String sql = "SELECT * FROM product_status";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductStatus status = new ProductStatus();
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("name"));
            return status;
        });
    }



}
