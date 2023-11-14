/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Request {
    private int id;
    private int userID;
    private String name;
    private String size;
    private Material material;
    private float price;
    private int staffID;
    private Date createDate;
    private int status;
    private int quantity;
    private Category category;
    
    public Request() {
    }

    public Request(int id, int userID, String name, String size, Material material, float price, int staffID, Date createDate, int status, int quantity, Category category) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.size = size;
        this.material = material;
        this.price = price;
        this.staffID = staffID;
        this.createDate = createDate;
        this.status = status;
        this.quantity = quantity;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    

    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", userID=" + userID + ", name=" + name + ", size=" + size + ", material=" + material + ", price=" + price + ", staffID=" + staffID + ", createDate=" + createDate + ", status=" + status + ", quantity=" + quantity + '}';
    }

  
    
}
