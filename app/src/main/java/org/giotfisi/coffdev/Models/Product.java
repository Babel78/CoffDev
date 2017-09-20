package org.giotfisi.coffdev.Models;

/**
 * Created by Axel on 16/09/2017.
 */

public class Product {
    private String Title;
    private String description;
    private int imgProduct;

    public Product(String title, String description, int imgProduct) {
        Title = title;
        this.description = description;
        this.imgProduct = imgProduct;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }
}
