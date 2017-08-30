/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Product implements Serializable {

    private Integer id;
    private Date date = new Date(System.currentTimeMillis());
    private String name;
    private String description;
    private double currentPrice;
    private boolean allowOrder;
    private Category category;
    private Account seller;

    public Product() {
    }

    public Product(Integer id, Date date, String name, String description, double currentPrice, boolean allowOrder, Category category, Account seller) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
        this.allowOrder = allowOrder;
        this.category = category;
        this.seller = seller;
    }

    public Product(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isAllowOrder() {
        return allowOrder;
    }

    public void setAllowOrder(boolean allowOrder) {
        this.allowOrder = allowOrder;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getSeller() {
        return seller;
    }

    public void setSeller(Account seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", date=" + date + ", name=" + name + ", description=" + description + ", currentPrice=" + currentPrice + ", allowOrder=" + allowOrder + ", category=" + category + ", seller=" + seller + '}';
    }

    

}
