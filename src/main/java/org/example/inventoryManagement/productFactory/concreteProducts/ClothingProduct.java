package org.example.inventoryManagement.productFactory.concreteProducts;

import org.example.inventoryManagement.enums.ProductCategory;
import org.example.inventoryManagement.productFactory.Product;

public class ClothingProduct extends Product {
    private int size;
    private String color;


    public ClothingProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory category) {
        super(sku, name, price, quantity, threshold, category);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
