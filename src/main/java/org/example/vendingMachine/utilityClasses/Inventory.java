package org.example.vendingMachine.utilityClasses;

public class Inventory {
    private ItemShelf[] inventory;

    public Inventory(int itemCount) {
        inventory = new ItemShelf[itemCount];
        initializeEmptyInventory();
    }

    private void initializeEmptyInventory() {
        int startCode = 101;
        for (int i = 0; i < inventory.length; i++) {
            ItemShelf space = new ItemShelf(startCode);
            inventory[i] = space;
            startCode++;
        }
    }

    public ItemShelf[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemShelf[] inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item, int codeNumber) {
        for(ItemShelf shelf: inventory) {
            if (codeNumber == shelf.getCode()) {
                shelf.addItem(item);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }

    public Item getItem(int codeNumber) {
        for (ItemShelf shelf: inventory) {
            if (codeNumber == shelf.getCode()) {
                return shelf.getItems().getFirst();
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }

    public void removeItem(int codeNumber) {
        for (ItemShelf shelf: inventory) {
            if (codeNumber == shelf.getCode()) {
                shelf.removeItem(getItem(codeNumber));
                return;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }

    public void updateSoldOutItem(int codeNumber) {
        for (ItemShelf shelf: inventory) {
            if (codeNumber == shelf.getCode()) {
                if (shelf.getItems().isEmpty()) {
                    shelf.setSoldOut(true);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }

    public boolean hasItems() {
        for (ItemShelf shelf: inventory) {
            if (!shelf.isSoldOut()) {
                return true;
            }
        }
        return false;
    }
}
