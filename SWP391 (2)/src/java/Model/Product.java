/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Product {
    private int productID;
    private String name;
    private String code;
    private int material;
    private String size;   
    private int price;
    private float discount;
    private Category category;
    private int stock;
    private int isAvailable;
    private int quantitySold;
    private double rate_sum;
    private double rate_count;
    private String image;

    public Product() {
    }

    public Product(int productID, String name, String code, int material, String size, int price, float discount, Category category, int stock, int isAvailable, int quantitySold, double rate_sum, double rate_count, String image) {
        this.productID = productID;
        this.name = name;
        this.code = code;
        this.material = material;
        this.size = size;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.stock = stock;
        this.isAvailable = isAvailable;
        this.quantitySold = quantitySold;
        this.rate_sum = rate_sum;
        this.rate_count = rate_count;
        this.image = image;
    }

  

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getRate_sum() {
        return rate_sum;
    }

    public void setRate_sum(double rate_sum) {
        this.rate_sum = rate_sum;
    }

    public double getRate_count() {
        return rate_count;
    }

    public void setRate_count(double rate_count) {
        this.rate_count = rate_count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.productID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return this.productID == other.productID;
    }

//    @Override

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", name=" + name + ", code=" + code + ", material=" + material + ", size=" + size + ", price=" + price + ", discount=" + discount + ", category=" + category + ", stock=" + stock + ", isAvailable=" + isAvailable + ", quantitySold=" + quantitySold + ", rate_sum=" + rate_sum + ", rate_count=" + rate_count + ", image=" + image + '}';
    }

    public void setProductName(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setProductPrice(double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setProductLink(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   


    
    
}
