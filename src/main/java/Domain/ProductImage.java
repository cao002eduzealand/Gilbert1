package Domain;

import java.sql.Timestamp;

public class ProductImage {
    private int id;
    private Product product;
    private String imageUrl;
    private Timestamp uploadedAt;

    public ProductImage() {}

    public ProductImage(int id, Product product, String imageUrl, Timestamp uploadedAt) {
        this.id = id;
        this.product = product;
        this.imageUrl = imageUrl;
        this.uploadedAt = uploadedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}