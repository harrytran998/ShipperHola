/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Quang Hiep
 */
public class ProductReview implements Serializable {

    private Integer id;
    private Date date = new Date(System.currentTimeMillis());
    private double rating;
    private String content;
    private Account account;
    private Product product;

    public ProductReview() {
    }

    public ProductReview(Integer id, double rating, String content, Account account, Product product) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.account = account;
        this.product = product;
    }

    public ProductReview(Integer id, Date date, double rating, String content, Account account, Product product) {
        this.id = id;
        this.date = date;
        this.rating = rating;
        this.content = content;
        this.account = account;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "ProductReview{" + "id=" + id + ", date=" + date + ", rating=" + rating + ", content=" + content + ", account=" + account + ", product=" + product + '}';
    }
    
}
