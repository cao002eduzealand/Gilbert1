package Infrastructure;

import Domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.Collections;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
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
                "description, price, date_uploaded) VALUES (?,?,?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, product.getBrand().getId());
            ps.setInt(2, product.getClothingArticle().getId());
            ps.setInt(3, product.getSeller().getId());
            ps.setInt(4,1);
            ps.setInt(5, product.getCondition().getId());
            ps.setString(6, product.getModelName());
            ps.setString(7, product.getDescription());
            ps.setDouble(8, product.getPrice());
            ps.setTimestamp(9, product.getDateUploaded());
            return ps;
        }, keyHolder);

        // Get the generated ID and set it to the product
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
        String sql = "SELECT * FROM product WHERE id = ?";

        Product product = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setModelName(rs.getString("model_name"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getDouble("price"));
            p.setDateUploaded(rs.getTimestamp("date_uploaded"));

            // Get the seller ID
            int sellerId = rs.getInt("seller_id");
            if (sellerId > 0) {
                User seller = new User();
                seller.setId(sellerId);
                p.setSeller(seller);
            }

            // Get other IDs
            int brandId = rs.getInt("brand_id");
            if (brandId > 0) {
                Brand brand = new Brand();
                brand.setId(brandId);
                p.setBrand(brand);
            }

            int articleId = rs.getInt("clothing_article_id");
            if (articleId > 0) {
                ClothingArticle article = new ClothingArticle();
                article.setId(articleId);
                p.setClothingArticle(article);
            }

            int conditionId = rs.getInt("condition_id");
            if (conditionId > 0) {
                ProductCondition condition = new ProductCondition();
                condition.setId(conditionId);
                p.setCondition(condition);
            }

            int statusId = rs.getInt("status_id");
            if (statusId > 0) {
                ProductStatus status = new ProductStatus();
                status.setId(statusId);
                p.setProductStatus(status);
            }

            return p;
        }, id);


        try {
            // Fetch seller
            if (product.getSeller() != null) {
                String sellerSql = "SELECT * FROM user WHERE id = ?";
                try {
                    User seller = jdbcTemplate.queryForObject(sellerSql, new BeanPropertyRowMapper<>(User.class), product.getSeller().getId());
                    product.setSeller(seller);
                } catch (Exception e) {
                    System.out.println("Error fetching seller: " + e.getMessage());
                }
            }

            // Fetch brand
            if (product.getBrand() != null) {
                String brandSql = "SELECT * FROM brand WHERE id = ?";
                try {
                    Brand brand = jdbcTemplate.queryForObject(brandSql, (rs, rowNum) -> {
                        Brand b = new Brand();
                        b.setId(rs.getInt("id"));
                        b.setBrandName(rs.getString("name"));
                        return b;
                    }, product.getBrand().getId());
                    product.setBrand(brand);
                } catch (Exception e) {
                    System.out.println("Error fetching brand: " + e.getMessage());
                }
            }

            // Fetch clothing article
            if (product.getClothingArticle() != null) {
                String articleSql = "SELECT * FROM clothing_article WHERE id = ?";
                try {
                    ClothingArticle article = jdbcTemplate.queryForObject(articleSql, (rs, rowNum) -> {
                        ClothingArticle a = new ClothingArticle();
                        a.setId(rs.getInt("id"));
                        a.setName(rs.getString("name"));
                        return a;
                    }, product.getClothingArticle().getId());
                    product.setClothingArticle(article);
                } catch (Exception e) {
                    System.out.println("Error fetching clothing article: " + e.getMessage());
                }
            }

            // Fetch condition
            if (product.getCondition() != null) {
                String conditionSql = "SELECT * FROM product_condition WHERE id = ?";
                try {
                    ProductCondition condition = jdbcTemplate.queryForObject(conditionSql, (rs, rowNum) -> {
                        ProductCondition c = new ProductCondition();
                        c.setId(rs.getInt("id"));
                        c.setCondition(rs.getString("name")); // Assuming column name is "name"
                        return c;
                    }, product.getCondition().getId());
                    product.setCondition(condition);
                } catch (Exception e) {
                    System.out.println("Error fetching condition: " + e.getMessage());
                }
            }

            // Fetch status
            if (product.getProductStatus() != null) {
                String statusSql = "SELECT * FROM product_status WHERE id = ?";
                try {
                    ProductStatus status = jdbcTemplate.queryForObject(statusSql, (rs, rowNum) -> {
                        ProductStatus s = new ProductStatus();
                        s.setId(rs.getInt("id"));
                        s.setStatus(rs.getString("name")); // Assuming column name is "name"
                        return s;
                    }, product.getProductStatus().getId());
                    product.setProductStatus(status);
                } catch (Exception e) {
                    System.out.println("Error fetching status: " + e.getMessage());
                }
            }

            // Fetch images
            String imageSql = "SELECT * FROM product_image WHERE product_id = ?";
            List<ProductImage> images = jdbcTemplate.query(imageSql, (rs, rowNum) -> {
                ProductImage image = new ProductImage();
                image.setId(rs.getInt("id"));
                image.setImageUrl(rs.getString("image_url"));
                image.setUploadedAt(rs.getTimestamp("uploaded_at"));
                return image;
            }, product.getId());

            product.setImages(images);

        } catch (Exception e) {
            System.out.println("Error fetching product details: " + e.getMessage());
            e.printStackTrace();
        }

        return product;
    }

    public List<Product> getProductsByUser(User user) {
        // Fetch basic product information
        String sql = "SELECT * FROM product WHERE seller_id = ?";
        List<Product> products = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setModelName(rs.getString("model_name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            product.setDateUploaded(rs.getTimestamp("date_uploaded"));

            // Log the IDs of related entities
            System.out.println("Product ID: " + rs.getInt("id"));
            System.out.println("  Brand ID: " + rs.getInt("brand_id"));
            System.out.println("  Article ID: " + rs.getInt("clothing_article_id"));
            System.out.println("  Condition ID: " + rs.getInt("condition_id"));

            // Create the brand object with just the ID
            Brand brand = new Brand();
            brand.setId(rs.getInt("brand_id"));
            product.setBrand(brand);

            // Create the clothing article object with just the ID
            ClothingArticle article = new ClothingArticle();
            article.setId(rs.getInt("clothing_article_id"));
            product.setClothingArticle(article);

            // Create the condition object with just the ID
            ProductCondition condition = new ProductCondition();
            condition.setId(rs.getInt("condition_id"));
            product.setCondition(condition);

            return product;
        }, user.getId());

        // Now populate each product with complete related entities
        for (Product product : products) {
            try {
                // Fetch brand
                if (product.getBrand() != null && product.getBrand().getId() > 0) {
                    String brandSql = "SELECT * FROM brand WHERE id = ?";
                    try {
                        // Use row mapper with specific column handling
                        Brand brand = jdbcTemplate.queryForObject(brandSql, (rs, rowNum) -> {
                            Brand b = new Brand();
                            b.setId(rs.getInt("id"));
                            b.setBrandName(rs.getString("name")); // Make sure this matches your column name
                            return b;
                        }, product.getBrand().getId());
                        product.setBrand(brand);
                        System.out.println("Fetched brand: " + brand.getId() + " - " + brand.getBrandName());
                    } catch (Exception e) {
                        System.out.println("Error fetching brand: " + e.getMessage());
                        product.setBrand(new Brand(0, "Unknown"));
                    }
                } else {
                    product.setBrand(new Brand(0, "Unknown"));
                }

                // Fetch clothing article
                if (product.getClothingArticle() != null && product.getClothingArticle().getId() > 0) {
                    String articleSql = "SELECT * FROM clothing_article WHERE id = ?";
                    try {
                        // Use row mapper with specific column handling
                        ClothingArticle article = jdbcTemplate.queryForObject(articleSql, (rs, rowNum) -> {
                            ClothingArticle a = new ClothingArticle();
                            a.setId(rs.getInt("id"));
                            a.setName(rs.getString("name")); // Make sure this matches your column name
                            return a;
                        }, product.getClothingArticle().getId());
                        product.setClothingArticle(article);
                        System.out.println("Fetched article: " + article.getId() + " - " + article.getName());
                    } catch (Exception e) {
                        System.out.println("Error fetching clothing article: " + e.getMessage());
                        product.setClothingArticle(new ClothingArticle(0, "Unknown", null));
                    }
                } else {
                    product.setClothingArticle(new ClothingArticle(0, "Unknown", null));
                }

                // Fetch condition
                if (product.getCondition() != null && product.getCondition().getId() > 0) {
                    String conditionSql = "SELECT * FROM product_condition WHERE id = ?";
                    try {
                        // Use row mapper with specific column handling
                        ProductCondition condition = jdbcTemplate.queryForObject(conditionSql, (rs, rowNum) -> {
                            ProductCondition c = new ProductCondition();
                            c.setId(rs.getInt("id"));
                            c.setCondition(rs.getString("name")); // Assuming column name is "name", not "condition"
                            return c;
                        }, product.getCondition().getId());
                        product.setCondition(condition);
                        System.out.println("Fetched condition: " + condition.getId() + " - " + condition.getCondition());
                    } catch (Exception e) {
                        System.out.println("Error fetching condition: " + e.getMessage());
                        product.setCondition(new ProductCondition(0, "Unknown"));
                    }
                } else {
                    product.setCondition(new ProductCondition(0, "Unknown"));
                }

                // Fetch images
                String imageSql = "SELECT * FROM product_image WHERE product_id = ?";
                List<ProductImage> images = jdbcTemplate.query(imageSql, (rs, rowNum) -> {
                    ProductImage image = new ProductImage();
                    image.setId(rs.getInt("id"));
                    image.setImageUrl(rs.getString("image_url"));
                    image.setUploadedAt(rs.getTimestamp("uploaded_at"));
                    return image;
                }, product.getId());

                System.out.println("Product " + product.getId() + " has " + images.size() + " images");
                for (ProductImage image : images) {
                    System.out.println("  Image URL: " + image.getImageUrl());
                }

                product.setImages(images);

            } catch (Exception e) {
                System.out.println("Error processing product " + product.getId() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }

        return products;
    }

    public List<Product> findAllByBrand(Brand brand) {
        String sql = "SELECT * FROM product p " +
                "JOIN product_status ps ON p.status_id = ps.id " +
                "WHERE p.brand_id = ? AND ps.name = 'listed'";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), brand.getId());

    }

    public List<ProductCondition> findAllProductConditions(){
        String sql = "SELECT * FROM product_condition";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductCondition.class));
    }

    // Searching for categorytree dropdowns



    public List<Product> findAllByCategoryId(int categoryId) {
        String sql = "SELECT p.* FROM product p " +
                "JOIN clothing_article ca ON p.clothing_article_id = ca.id " +
                "JOIN subcategory s ON ca.subcategory_id = s.id " +
                "JOIN product_status ps ON p.status_id = ps.id " +
                "WHERE s.category_id = ? AND ps.name = 'listed'";

        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), categoryId);
        return populateProductDetails(products);
    }

    public List<Product> findAllBySubCategoryId(int subCategoryId) {
        String sql = "SELECT p.* FROM product p " +
                "JOIN clothing_article ca ON p.clothing_article_id = ca.id " +
                "JOIN product_status ps ON p.status_id = ps.id " +
                "WHERE ca.subcategory_id = ? AND ps.name = 'listed'";

        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), subCategoryId);
        return populateProductDetails(products);
    }

    public List<Product> findAllByClothingArticleId(int clothingArticleId) {
        String sql = "SELECT p.* FROM product p " +
                "JOIN product_status ps ON p.status_id = ps.id " +
                "WHERE p.clothing_article_id = ? AND ps.name = 'listed'";

        List<Product> products = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), clothingArticleId);
        return populateProductDetails(products);
    }

    private List<Product> populateProductDetails(List<Product> products) {
        for (Product product : products) {
            try {
                populateSeller(product);
                populateBrand(product);
                populateClothingArticle(product);
                populateCondition(product);
                populateProductStatus(product);
                populateImages(product);
            } catch (Exception e) {
                System.out.println("Error processing product " + product.getId() + ": " + e.getMessage());
            }
        }
        return products;
    }

    private void populateSeller(Product product) {
        if (product.getSeller() != null && product.getSeller().getId() > 0) {
            try {
                String sql = "SELECT * FROM user WHERE id = ?";
                User seller = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), product.getSeller().getId());
                product.setSeller(seller);
            } catch (Exception e) {
                System.out.println("Error fetching seller: " + e.getMessage());
            }
        }
    }

    private void populateBrand(Product product) {
        if (product.getBrand() != null && product.getBrand().getId() > 0) {
            try {
                String sql = "SELECT * FROM brand WHERE id = ?";
                Brand brand = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    Brand b = new Brand();
                    b.setId(rs.getInt("id"));
                    b.setBrandName(rs.getString("name"));
                    return b;
                }, product.getBrand().getId());
                product.setBrand(brand);
            } catch (Exception e) {
                System.out.println("Error fetching brand: " + e.getMessage());
                product.setBrand(new Brand(0, "Unknown"));
            }
        } else {
            product.setBrand(new Brand(0, "Unknown"));
        }
    }

    private void populateClothingArticle(Product product) {
        if (product.getClothingArticle() != null && product.getClothingArticle().getId() > 0) {
            try {
                String sql = "SELECT * FROM clothing_article WHERE id = ?";
                ClothingArticle article = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    ClothingArticle a = new ClothingArticle();
                    a.setId(rs.getInt("id"));
                    a.setName(rs.getString("name"));
                    return a;
                }, product.getClothingArticle().getId());
                product.setClothingArticle(article);
            } catch (Exception e) {
                System.out.println("Error fetching clothing article: " + e.getMessage());
                product.setClothingArticle(new ClothingArticle(0, "Unknown", null));
            }
        } else {
            product.setClothingArticle(new ClothingArticle(0, "Unknown", null));
        }
    }

    private void populateCondition(Product product) {
        if (product.getCondition() != null && product.getCondition().getId() > 0) {
            try {
                String sql = "SELECT * FROM product_condition WHERE id = ?";
                ProductCondition condition = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    ProductCondition c = new ProductCondition();
                    c.setId(rs.getInt("id"));
                    c.setCondition(rs.getString("name"));
                    return c;
                }, product.getCondition().getId());
                product.setCondition(condition);
            } catch (Exception e) {
                System.out.println("Error fetching condition: " + e.getMessage());
                product.setCondition(new ProductCondition(0, "Unknown"));
            }
        } else {
            product.setCondition(new ProductCondition(0, "Unknown"));
        }
    }

    private void populateProductStatus(Product product) {
        if (product.getProductStatus() != null && product.getProductStatus().getId() > 0) {
            try {
                String sql = "SELECT * FROM product_status WHERE id = ?";
                ProductStatus status = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    ProductStatus s = new ProductStatus();
                    s.setId(rs.getInt("id"));
                    s.setStatus(rs.getString("name"));
                    return s;
                }, product.getProductStatus().getId());
                product.setProductStatus(status);
            } catch (Exception e) {
                System.out.println("Error fetching status: " + e.getMessage());
                product.setProductStatus(new ProductStatus(0, "Unknown"));
            }
        } else {
            product.setProductStatus(new ProductStatus(0, "Unknown"));
        }
    }

    private void populateImages(Product product) {
        try {
            String sql = "SELECT * FROM product_image WHERE product_id = ?";
            List<ProductImage> images = jdbcTemplate.query(sql, (rs, rowNum) -> {
                ProductImage image = new ProductImage();
                image.setId(rs.getInt("id"));
                image.setImageUrl(rs.getString("image_url"));
                image.setUploadedAt(rs.getTimestamp("uploaded_at"));
                return image;
            }, product.getId());

            product.setImages(images);
        } catch (Exception e) {
            System.out.println("Error fetching images: " + e.getMessage());
            product.setImages(Collections.emptyList());
        }
    }






}
