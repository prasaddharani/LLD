package org.example.inventoryManagement.utility;

import org.example.inventoryManagement.productFactory.Product;

import java.util.HashMap;
import java.util.Map;

public class WareHouse {
    private int id;
    private String name;
    private String location;
    private Map<String, Product> products; // SKU -> Product

    public WareHouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.products = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        String sku = product.getSku();
        if (products.containsKey(sku)) {
            products.get(sku).setQuantity(product.getQuantity());
        } else {
            products.put(sku, product);
        }
        System.out.println(product.getQuantity() + " units of " + product.getName()
                + " (SKU: " + sku + ") added to " + name
                + ". New quantity: " + getAvailableQuantity(sku));
    }

    public boolean removeProduct(String sku, int quantity) {
        if (products.containsKey(sku)) {
            int availableQuantity = getAvailableQuantity(sku);
            Product product = products.get(sku);
            if (availableQuantity >= quantity) {
                int leftQuantity = availableQuantity - quantity;
                if (leftQuantity == 0) {
                    products.remove(sku);
                    System.out.println("Product " + product.getName()
                            + " removed from inventory as quantity is now zero.");
                } else {
                    products.get(sku).setQuantity(leftQuantity);
                    System.out.println(quantity + " units of " + product.getName()
                            + " (SKU: " + sku + ") removed from " + name
                            + ". Remaining quantity: " + product.getQuantity());
                }
                return true;
            }
        }
        System.out.println(
                "Error: Product with SKU " + sku + " not found in " + name);
        return false;
    }

    private int getAvailableQuantity(String sku) {
        if (products.containsKey(sku)) {
            return products.get(sku).getQuantity();
        }
        return 0;
    }

    public Product getProductBySku(String sku) {
        return products.get(sku);
    }
}
