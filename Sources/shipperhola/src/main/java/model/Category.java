/*
 * Copyright © 2017 XVideos Team
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Category implements Serializable {
    private Integer id;
    private String name;

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }
    
    
    
    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + '}';
    }
}
