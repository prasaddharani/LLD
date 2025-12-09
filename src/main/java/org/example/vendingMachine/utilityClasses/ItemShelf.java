package org.example.vendingMachine.utilityClasses;

import java.util.ArrayList;
import java.util.List;

public class ItemShelf {
    private int code;
    private List<Item> items;
    private boolean isSoldOut;

    public ItemShelf(int code) {
        this.code = code;
        this.items = new ArrayList<>();
        this.isSoldOut = false;
    }

    public void setSoldOut(boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public void addItem(Item item) {
        items.add(item);
        if (isSoldOut) {
            setSoldOut(false);
        }
    }

    public void removeItem(Item item) {
        items.remove(item);
        if (items.isEmpty()) {
            setSoldOut(true);
        }
    }

    public int getCode() {
        return code;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }
}
