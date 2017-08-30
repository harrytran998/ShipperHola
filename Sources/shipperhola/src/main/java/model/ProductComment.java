/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Quang Hiep
 */
public class ProductComment implements Serializable{
    private int id;
    private Date date;
    private String content;
    private Account account;
    private ProductComment repliedComment;
    private Product product;

    public ProductComment() {
    }

    public ProductComment(int id, Date date, String content, Account account, ProductComment repliedComment, Product product) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.account = account;
        this.repliedComment = repliedComment;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ProductComment getRepliedComment() {
        return repliedComment;
    }

    public void setRepliedComment(ProductComment repliedComment) {
        this.repliedComment = repliedComment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductComment{" + "id=" + id + ", date=" + date + ", content=" + content + ", account=" + account + ", repliedComment=" + repliedComment + ", product=" + product + '}';
    }
    
    
}
