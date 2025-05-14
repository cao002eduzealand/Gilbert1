package Domain;

public class Company {
   private int id;
    private String companyName;
    private String companyCVRNumber;

    public Company() {}
    public Company(int id, String companyName, String companyCVRNumber) {
        this.id = id;
        this.companyName = companyName;
        this.companyCVRNumber = companyCVRNumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyCVRNumber() {
        return companyCVRNumber;
    }
    public void setCompanyCVRNumber(String companyCVRNumber) {
        this.companyCVRNumber = companyCVRNumber;
    }

}
