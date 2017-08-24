/*
 * Copyright © 2017 XVideos Team
 */
package model;

/**
 *
 * @author Admin
 */
public class ProductPicture {
    private Integer id;
    private String fileName;
    private String extension;
    private Product product;

    public ProductPicture() {
    }

    public ProductPicture(Integer id, String fileName, String extension, Product product) {
        this.id = id;
        this.fileName = fileName;
        this.extension = extension;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductPicture{" + "id=" + id + ", fileName=" + fileName + ", extension=" + extension + ", product=" + product + '}';
    }
    
}
