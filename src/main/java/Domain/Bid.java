package Domain;

import java.sql.Timestamp;

public class Bid {

    private int id;
    private Product product;
    private User bidder;
    private double bidAmount;
    private String message;
    private bidStatus status;
    private Timestamp createdAt;
    public enum bidStatus{
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Bid(){}

    public Bid(int id, Product product, User bidder, double bidAmount, String message, bidStatus status, Timestamp createdAt) {
        this.id = id;
        this.product = product;
        this.bidder = bidder;
        this.bidAmount = bidAmount;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;

    }
    public int getId() { return id; }
    public Product getProduct() { return product; }
    public User getBidder() { return bidder; }
    public double getBidAmount() { return bidAmount; }
    public String getMessage() { return message; }
    public bidStatus getStatus() { return status; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setId(int id) { this.id = id; }
    public void setProduct(Product product) { this.product = product; }
    public void setBidder(User bidder) { this.bidder = bidder; }
    public void setBidAmount(double bidAmount) { this.bidAmount = bidAmount; }
    public void setMessage(String message) { this.message = message; }
    public void setStatus(bidStatus status) { this.status = status; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


}
