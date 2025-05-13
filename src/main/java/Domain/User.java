package Domain;

import java.sql.Timestamp;

public class User {

    private int id;
    private String fName;
    private String lName;
    private String displayName;
    private String userName;
    private String email;
    private String password;
    private String profilePictureURL;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private String address;
    private String ZIP;
    private String city;
    private String country;
    private Role role;
    private Company company;

    public int getId() {return id;}
    public String getfName() {return fName;}
    public String getlName() {return lName;}
    public String getDisplayName() {return displayName;}
    public String getUserName() {return userName;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getProfilePictureURL() {return profilePictureURL;}
    public Timestamp getCreatedAt() {return createdAt;}
    public Timestamp getLastLogin() {return lastLogin;}
    public String getAddress() {return address;}
    public String getZIP() {return ZIP;}
    public String getCity() {return city;}
    public String getCountry() {return country;}
    public Role getRole() {return role;}
    public Company getCompany(){return company;}

    public void setCompany(Company company){this.company=company;}
    public void setId(int id) {this.id = id;}
    public void setfName(String fName) {this.fName = fName;}
    public void setlName(String lName) {this.lName = lName;}
    public void setDisplayName(String displayName) {this.displayName = displayName;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setProfilePictureURL(String profilePictureURL) {this.profilePictureURL = profilePictureURL;}
    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt;}
    public void setLastLogin(Timestamp lastLogin) {this.lastLogin = lastLogin;}
    public void setAddress(String address) {this.address = address;}
    public void setZIP(String ZIP) {this.ZIP = ZIP;}
    public void setCity(String city) {this.city = city;}
    public void setCountry(String country) {this.country = country;}
    public void setRole(Role role) {this.role = role;}
}
