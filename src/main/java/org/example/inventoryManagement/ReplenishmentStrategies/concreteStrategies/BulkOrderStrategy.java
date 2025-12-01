package org.example.inventoryManagement.ReplenishmentStrategies.concreteStrategies;

import org.example.inventoryManagement.ReplenishmentStrategies.ReplenishmentStrategy;
import org.example.inventoryManagement.productFactory.Product;

public class BulkOrderStrategy implements ReplenishmentStrategy {
    @Override
    public void replenishment(Product product) {
        System.out.println("Applying BulkOrder strategy for product: " + product.getName());
    }
}
