
package model;

import java.sql.Date;


public class Order {
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    private Integer id;
    private Date date;
    private int quantity;
    private double price;
    private String byerAddress;
    private String byerPhoneNumber;
    private String paymentMethod;
    private String status;

    public Order() {
    }

    public Order(int id, Date date, int quantity, double price, String byerAddress, String byerPhoneNumber, String paymentMethod, String status) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.byerAddress = byerAddress;
        this.byerPhoneNumber = byerPhoneNumber;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getByerAddress() {
        return byerAddress;
    }

    public void setByerAddress(String byerAddress) {
        this.byerAddress = byerAddress;
    }

    public String getByerPhoneNumber() {
        return byerPhoneNumber;
    }

    public void setByerPhoneNumber(String byerPhoneNumber) {
        this.byerPhoneNumber = byerPhoneNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
