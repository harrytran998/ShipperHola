/*
 * Copyright © 2017 XVideos Team
 */
package model;

/**
 *
 * @author Admin
 */
public class ShippingAddress {
    private int id;
    private String address;

    public ShippingAddress() {
    }

    public ShippingAddress(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" + "id=" + id + ", address=" + address + '}';
    }
    
}
