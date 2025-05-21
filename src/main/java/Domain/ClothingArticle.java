package Domain;

public class ClothingArticle {

    private int id;
    private String name;
    private SubCategory subcategory;

    public ClothingArticle() {}
    public ClothingArticle(int id, String name){this.id=id; this.name=name;}
    public ClothingArticle(int id, String name, SubCategory subcategory) {
        this.id = id;
        this.name = name;
        this.subcategory = subcategory;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public SubCategory getSubcategory() {
        return subcategory;
    }
    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

}
