/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.DateUtils;

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
    
    private List<Order> orders = new ArrayList<>();
    private List<ProductPicture> pictures = new ArrayList<>();
    private List<ProductComment> comments = new ArrayList<>();
    private List<ProductReview> reviews = new ArrayList<>();
    private List<ShippingAddress> shippingAddresses = new ArrayList<>();
    
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<ProductPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ProductPicture> pictures) {
        this.pictures = pictures;
    }

    public List<ProductComment> getComments() {
        return comments;
    }

    public void setComments(List<ProductComment> comments) {
        this.comments = comments;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public List<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public int getOrderQuantityToday() {
        Date now = new Date(System.currentTimeMillis());
        return orders.stream().filter(order -> DateUtils.isSameDay(order.getDate(), now)).collect(Collectors.summingInt(Order::getQuantity));
    }
    
    public double getAverageRating() {
        return reviews.stream().collect(Collectors.averagingDouble(ProductReview::getRating));
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", date=" + date + ", name=" + name + ", description=" + description + ", currentPrice=" + currentPrice + ", allowOrder=" + allowOrder + ", category=" + category + ", seller=" + seller + ", orders=" + orders + ", pictures=" + pictures + ", comments=" + comments + ", reviews=" + reviews + ", shippingAddresses=" + shippingAddresses + '}';
    }

}
