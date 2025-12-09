package org.example.vendingMachine.vendingMachineStates;

import org.example.vendingMachine.enums.Coin;
import org.example.vendingMachine.utilityClasses.Inventory;
import org.example.vendingMachine.utilityClasses.Item;
import org.example.vendingMachine.vendingMachineStates.concreteStates.DispenseState;
import org.example.vendingMachine.vendingMachineStates.concreteStates.HasMoneyState;
import org.example.vendingMachine.vendingMachineStates.concreteStates.IdleState;
import org.example.vendingMachine.vendingMachineStates.concreteStates.SelectionState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineContext {
    private VendingMachineState currentState;
    private Inventory inventory;
    private List<Coin> coins;
    private int selectedItemCode;

    public VendingMachineContext() {
        this.currentState = new IdleState();
        this.inventory = new Inventory(10);
        this.coins = new ArrayList<>();
        System.out.println("Initialized: " + currentState.getStateName());
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public int getSelectedItemCode() {
        return selectedItemCode;
    }

    public void setSelectedItemCode(int selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }

    public void advanceState() {
        currentState = currentState.next(this);
        System.out.println("Current state: " + currentState.getStateName());
    }

    public void clickOnInsertCoinButton(Coin coin) {
        if (currentState instanceof IdleState || currentState instanceof HasMoneyState) {
            System.out.println("Inserted " + coin.name() + " worth " + coin.value);
            coins.add(coin);
            advanceState();
        } else {
            System.out.println("Cannot insert coin in " + currentState.getStateName());
        }
    }

    public void clickOnSelectOptionButton(int codeNumber) {
        if (currentState instanceof HasMoneyState) {
            advanceState();
            selectProduct(codeNumber);
        } else {
            System.out.println("Product selection button can only be clicked in HasMoney state");
        }
    }

    public void selectProduct(int codeNumber) {
        if (currentState instanceof SelectionState) {
            int balance = getBalance();
            Item item = inventory.getItem(codeNumber);
            if (balance < item.getPrice()) {
                System.out.println(
                        "Insufficient amount. Product price: " + item.getPrice() + ", paid: " + balance);
                return;
            }
            setSelectedItemCode(codeNumber);
            advanceState();
            dispenseItem(codeNumber);
            if (balance > item.getPrice()) {
                int change = balance - item.getPrice();
                System.out.println("Returning change: " + change);
            }
        } else {
            System.out.println("Products can only be selected in Selection state");
        }
    }

    private void dispenseItem(int codeNumber) {
        if (currentState instanceof DispenseState) {
            Item item = inventory.getItem(codeNumber);
            System.out.println("Dispensing: " + item.getType());
            inventory.removeItem(codeNumber);
            inventory.updateSoldOutItem(codeNumber);
            resetBalance();
            resetSelection();
            advanceState();
        } else {
            System.out.println("System cannot dispense in : " + currentState);
        }
    }

    private void resetSelection() {
        setSelectedItemCode(0);
    }

    private void resetBalance() {
        coins.clear();
    }

    private int getBalance() {
        int balance = 0;
        for (Coin coin: coins) {
            balance += coin.value;
        }
        return balance;
    }

    // Updates the inventory by adding a new item
    public void updateInventory(Item item, int codeNumber) {
        if (currentState instanceof IdleState) { // Only update inventory in Idle state
            try {
                inventory.addItem(item, codeNumber); // Add the item to inventory
                System.out.println("Added " + item.getType() + " to slot " + codeNumber);
            } catch (Exception e) {
                System.out.println("Error updating inventory: " + e.getMessage());
            }
        } else {
            System.out.println("Inventory can only be updated in Idle state");
        }
    }
}
