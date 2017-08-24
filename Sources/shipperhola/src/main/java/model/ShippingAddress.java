/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ShippingAddress implements Serializable {
    private Integer id;
    private String address;

    public ShippingAddress() {
    }

    public ShippingAddress(Integer id, String address) {
        this.id = id;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
