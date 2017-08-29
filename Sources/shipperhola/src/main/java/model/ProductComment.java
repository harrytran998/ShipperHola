/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.sql.Date;

/**
 *
 * @author Quang Hiep
 */
public class ProductComment {
    private int Id;
    private Date date;
    private String content;
    private int accountId;
    private int repliedCommentId;
    private Product product;

    public ProductComment() {
    }

    public ProductComment(int Id, Date date, String content, int accountId, int repliedCommentId, Product product) {
        this.Id = Id;
        this.date = date;
        this.content = content;
        this.accountId = accountId;
        this.repliedCommentId = repliedCommentId;
        this.product = product;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getRepliedCommentId() {
        return repliedCommentId;
    }

    public void setRepliedCommentId(int repliedCommentId) {
        this.repliedCommentId = repliedCommentId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductComment{" + "Id=" + Id + ", date=" + date + ", content=" + content + ", accountId=" + accountId + ", repliedCommentId=" + repliedCommentId + ", product=" + product + '}';
    }
    
    
    
    
    
    
}
