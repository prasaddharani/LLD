package org.example.inventoryManagement.productFactory.concreteProducts;

import org.example.inventoryManagement.enums.ProductCategory;
import org.example.inventoryManagement.productFactory.Product;

public class ElectronicsProduct extends Product {
    private String brand;
    private int warrantyPeriod;

    public ElectronicsProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory category) {
        super(sku, name, price, quantity, threshold, category);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
