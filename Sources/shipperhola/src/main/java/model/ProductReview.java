/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Quang Hiep
 */
public class ProductReview {

    private Integer id;
    private Integer rating;
    private String content;
    private Account account;
    private Product product;

    public ProductReview() {
    }

    public ProductReview(Integer id, Integer rating, String content, Account account, Product product) {
        this.id = id;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
        return "ProductReview{" + "id=" + id + ", rating=" + rating + ", content=" + content + ", account=" + account + ", product=" + product + '}';
    }

}
