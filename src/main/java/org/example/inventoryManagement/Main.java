package org.example.inventoryManagement;

import org.example.inventoryManagement.ReplenishmentStrategies.ReplenishmentStrategy;
import org.example.inventoryManagement.ReplenishmentStrategies.concreteStrategies.BulkOrderStrategy;
import org.example.inventoryManagement.ReplenishmentStrategies.concreteStrategies.JustInTimeStrategy;
import org.example.inventoryManagement.enums.ProductCategory;
import org.example.inventoryManagement.productFactory.Product;
import org.example.inventoryManagement.productFactory.ProductFactory;
import org.example.inventoryManagement.stockManager.InventoryManager;
import org.example.inventoryManagement.utility.WareHouse;

public class Main {
    public static void main(String[] args) {
        ReplenishmentStrategy justInTimeStrategy = new JustInTimeStrategy();
        InventoryManager inventoryManager = InventoryManager.getInstance(justInTimeStrategy);


        WareHouse wareHouse1 = new WareHouse(1, "warehouse1", "MR Palli");
        WareHouse wareHouse2 = new WareHouse(2, "warehouse2", "Durga Nagar");
        inventoryManager.addWareHouse(wareHouse1);
        inventoryManager.addWareHouse(wareHouse2);

        Product laptop = ProductFactory.createProduct("sku123", "laptop", 20000, 10, 5, ProductCategory.ELECTRONICS);
        Product rice = ProductFactory.createProduct("sku234", "rice", 1000, 50, 25, ProductCategory.GROCERY);

        wareHouse1.addProduct(laptop);
        wareHouse2.addProduct(rice);

        ReplenishmentStrategy bulkOrderStrategy = new BulkOrderStrategy();

        inventoryManager.setReplenishmentStrategy(bulkOrderStrategy);

        inventoryManager.performInventoryCheck();

        inventoryManager.checkAndReplenish("sku123");
    }
}
