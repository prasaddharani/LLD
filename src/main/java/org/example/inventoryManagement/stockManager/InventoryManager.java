package org.example.inventoryManagement.stockManager;

import org.example.inventoryManagement.ReplenishmentStrategies.ReplenishmentStrategy;
import org.example.inventoryManagement.productFactory.Product;
import org.example.inventoryManagement.productFactory.ProductFactory;
import org.example.inventoryManagement.utility.WareHouse;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private static volatile InventoryManager INSTANCE = null;
    private List<WareHouse> wareHouses;
    private ProductFactory productFactory;
    private ReplenishmentStrategy replenishmentStrategy;

    private InventoryManager(ReplenishmentStrategy replenishmentStrategy) {
        this.wareHouses = new ArrayList<>();
        this.productFactory = new ProductFactory();
        this.replenishmentStrategy = replenishmentStrategy;
    }

    public InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy) {
        if (INSTANCE == null) {
            synchronized (InventoryManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new InventoryManager(replenishmentStrategy);
                }
            }
        }
        return INSTANCE;
    }

    public void setReplenishmentStrategy(ReplenishmentStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
    }

    public void addWareHouse(WareHouse wareHouse) {
        this.wareHouses.add(wareHouse);
    }

    public void removeWareHouse(WareHouse wareHouse) {
        this.wareHouses.remove(wareHouse);
    }

    public Product getProductBySku(String sku) {
        for (WareHouse wareHouse: wareHouses) {
            Product product = wareHouse.getProductBySku(sku);
            if (product != null) {
                return product;
            }
        }
        return null;
    }

    public void checkAndReplenish(String sku) {
        Product product = getProductBySku(sku);
        if (product != null) {
            if (product.getQuantity() < product.getThreshold()) {
                replenishmentStrategy.replenishment(product);
            }
        }
    }

    public void performInventoryCheck() {
        for (WareHouse wareHouse: wareHouses) {
            for (Product product: wareHouse.getProducts().values()) {
                if (product.getQuantity() < product.getThreshold()) {
                    replenishmentStrategy.replenishment(product);
                }
            }
        }
    }

}
