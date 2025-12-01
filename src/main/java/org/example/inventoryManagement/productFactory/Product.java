package org.example.inventoryManagement.productFactory;

import org.example.inventoryManagement.enums.ProductCategory;

public abstract class Product {
    private String sku;
    private String name;
    private double price;
    private int quantity;
    private int threshold;
    private ProductCategory category;

    public Product(String sku, String name, double price, int quantity, int threshold, ProductCategory category) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.threshold = threshold;
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
