/*
 * Copyright © 2017 XVideos Team
 */
package model;

/**
 *
 * @author Quang Hiep
 */
public class ProductReview {
    private int id;
    private int rate;
    private String content;
    private int accountId;
    private int productId;

    public ProductReview() {
    }

    public ProductReview(int id, int rate, String content, int accountId, int productId) {
        this.id = id;
        this.rate = rate;
        this.content = content;
        this.accountId = accountId;
        this.productId = productId;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final ProductReview other = (ProductReview) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductReview{" + "id=" + id + ", rate=" + rate + ", content=" + content + ", accountId=" + accountId + ", productId=" + productId + '}';
    }
    
    
}
