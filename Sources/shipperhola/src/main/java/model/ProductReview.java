/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Quang Hiep
 */
public class ProductReview implements Serializable {

    private int id;
    private int rate;
    private String content;
    private int accountId;
    private Product product;

    public ProductReview() {
    }

    public ProductReview(int id, int rate, String content, int accountId, Product product) {
        this.id = id;
        this.rate = rate;
        this.content = content;
        this.accountId = accountId;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductReview{" + "id=" + id + ", rate=" + rate + ", content=" + content + ", accountId=" + accountId + ", product=" + product + '}';
    }

}
