package org.example.inventoryManagement.ReplenishmentStrategies;

import org.example.inventoryManagement.productFactory.Product;

public interface ReplenishmentStrategy {
    void replenishment(Product product);
}
