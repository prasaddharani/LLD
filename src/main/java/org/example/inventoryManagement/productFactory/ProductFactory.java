package org.example.inventoryManagement.productFactory;

import org.example.inventoryManagement.enums.ProductCategory;
import org.example.inventoryManagement.productFactory.concreteProducts.ClothingProduct;
import org.example.inventoryManagement.productFactory.concreteProducts.ElectronicsProduct;
import org.example.inventoryManagement.productFactory.concreteProducts.GroceryProduct;

public class ProductFactory {

    public static Product createProduct(String sku, String name, double price, int quantity, int threshold, ProductCategory category) {
        return switch (category) {
            case GROCERY -> new GroceryProduct(sku, name, price, quantity, threshold, category);
            case ELECTRONICS -> new ElectronicsProduct(sku, name, price, quantity, threshold, category);
            case CLOTHING -> new ClothingProduct(sku, name, price, quantity, threshold, category);
            default -> throw new IllegalArgumentException("Invalid product chosen");
        };
    }
}