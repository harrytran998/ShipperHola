/*
 * Copyright © 2017 XVideos Team
 */
package model;

/**
 *
 * @author Admin
 */
public class CartItem {
    private Account account;
    private Product product;
    private int quantity;
    private String note;

    public CartItem() {
    }

    public CartItem(Account account, Product product, int quantity, String note) {
        this.account = account;
        this.product = product;
        this.quantity = quantity;
        this.note = note;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CartItem{" + "account=" + account + ", product=" + product + ", quantity=" + quantity + ", note=" + note + '}';
    }
    
    
    
            
}
