package org.example.inventoryManagement.ReplenishmentStrategies.concreteStrategies;

import org.example.inventoryManagement.ReplenishmentStrategies.ReplenishmentStrategy;
import org.example.inventoryManagement.productFactory.Product;

public class JustInTimeStrategy implements ReplenishmentStrategy {
    @Override
    public void replenishment(Product product) {
        System.out.println("Applying Just-In-Time Replenishment for product: " + product.getName());
    }
}
