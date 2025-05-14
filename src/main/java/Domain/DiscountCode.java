package Domain;

import java.sql.Timestamp;

public class DiscountCode {

    private int id;
    private String code;
    private double discount;
    private int uses;
    private Timestamp expiryDate;
    private boolean active;


       public DiscountCode(){}
        public DiscountCode(int id, String code, double discount, int uses, Timestamp expiryDate, boolean active) {
            this.id = id;
            this.code = code;
            this.discount = discount;
            this.uses = uses;
            this.expiryDate = expiryDate;
            this.active = active;
        }

        public int getId() { return id; }
    public String getCode() { return code; }
    public double getDiscount() { return discount; }
    public int getUses() { return uses; }
    public Timestamp getExpiryDate() { return expiryDate; }
    public boolean isActive() { return active; }
    public void setId(int id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setDiscount(double discount) { this.discount = discount; }
    public void setUses(int uses) { this.uses = uses; }
    public void setExpiryDate(Timestamp expiryDate) { this.expiryDate = expiryDate; }
    public void setActive(boolean active) { this.active = active; }

}
