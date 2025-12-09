package org.example.vendingMachine.utilityClasses;

import org.example.vendingMachine.enums.ItemType;

public class Item {
    private ItemType type;
    private int price;


    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
