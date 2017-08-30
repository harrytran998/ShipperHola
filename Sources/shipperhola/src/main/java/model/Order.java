package model;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

    private Integer id;
    private Date date;
    private int quantity;
    private double price;
    private String buyerAddress;
    private String buyerPhoneNumber;
    private String paymentMethod;
    private String status;
    private Account buyer;
    private Product product;

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(Integer id, Date date, int quantity, double price, String buyerAddress, String buyerPhoneNumber, String paymentMethod, String status, Account buyer, Product product) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.buyerAddress = buyerAddress;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.buyer = buyer;
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

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
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

    public Account getBuyer() {
        return buyer;
    }

    public void setBuyer(Account buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + date + ", quantity=" + quantity + ", price=" + price + ", buyerAddress=" + buyerAddress + ", buyerPhoneNumber=" + buyerPhoneNumber + ", paymentMethod=" + paymentMethod + ", status=" + status + ", buyer=" + buyer + ", product=" + product + '}';
    }

}
