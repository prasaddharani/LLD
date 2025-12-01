package org.example.inventoryManagement.productFactory.concreteProducts;

import org.example.inventoryManagement.enums.ProductCategory;
import org.example.inventoryManagement.productFactory.Product;

import java.util.Date;

public class GroceryProduct extends Product {
    private Date expiryDate;
    private boolean refrigerated;

    public GroceryProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory category) {
        super(sku, name, price, quantity, threshold, category);
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }
}
