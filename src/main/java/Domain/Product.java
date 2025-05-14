package Domain;

import java.sql.Timestamp;

public class Product {

    private int id;
    private Brand brand;
    private ClothingArticle clothingArticle;
    private User seller;
    private ProductStatus productStatus;
    private ProductCondition condition;
    private String modelName;
    private String description;
    private double price;
    private Timestamp dateUploaded;


    public Product() {}
    public Product(int id, Brand brand, ClothingArticle clothingArticle, User seller, ProductStatus productStatus,
                   ProductCondition condition, String modelName, String description, double price, Timestamp dateUploaded) {
        this.id = id;
        this.brand = brand;
        this.clothingArticle = clothingArticle;
        this.seller = seller;
        this.productStatus = productStatus;
        this.condition = condition;
        this.modelName = modelName;
        this.description = description;
        this.price = price;
        this.dateUploaded = dateUploaded;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }
    public ClothingArticle getClothingArticle() { return clothingArticle; }
    public void setClothingArticle(ClothingArticle clothingArticle) { this.clothingArticle = clothingArticle; }
    public User getSeller() { return seller; }
    public void setSeller(User seller) { this.seller = seller; }
    public ProductStatus getProductStatus() { return productStatus; }
    public void setProductStatus(ProductStatus productStatus) { this.productStatus = productStatus; }
    public ProductCondition getCondition() { return condition; }
    public void setCondition(ProductCondition condition) { this.condition = condition; }
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public Timestamp getDateUploaded() { return dateUploaded; }
    public void setDateUploaded(Timestamp dateUploaded) { this.dateUploaded = dateUploaded; }
}
