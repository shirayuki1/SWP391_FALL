/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Comment {
    private int id;
    private Account account;
    private Product product;
    private String content;
    private Date commentDate;
    private int rate;
    private boolean status;

    public Comment(int id, Account account, Product product, String content, Date commentDate, int rate, boolean status) {
        this.id = id;
        this.account = account;
        this.product = product;
        this.content = content;
        this.commentDate = commentDate;
        this.rate = rate;
        this.status = status;
    }

    public Comment() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    
}
