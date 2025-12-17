package org.example.atmMachine.utilityClasses;

import org.example.atmMachine.enums.CashType;

import java.util.HashMap;
import java.util.Map;

public class ATMInventory {
    private Map<CashType, Integer> cashInventory;

    public ATMInventory() {
        cashInventory = new HashMap<>();
        initializeCashInventory();
    }

    private void initializeCashInventory() {
        cashInventory.put(CashType.BILL_100, 10);
        cashInventory.put(CashType.BILL_50, 10);
        cashInventory.put(CashType.BILL_20, 20);
        cashInventory.put(CashType.BILL_10, 30);
        cashInventory.put(CashType.BILL_5, 20);
        cashInventory.put(CashType.BILL_1, 50);
    }

    public int getTotalCash() {
        int total = 0;
        for (Map.Entry<CashType, Integer> entry: cashInventory.entrySet()) {
            total += (entry.getKey().value * entry.getValue());
        }
        return total;
    }

    public boolean hasSufficientCash(int amount) {
        return getTotalCash() >= amount;
    }

    public Map<CashType, Integer> dispenseCash(int amount) {
        if (!hasSufficientCash(amount)) {
            return null;
        }
        Map<CashType, Integer> dispenseCash = new HashMap<>();
        int remainingAmount = amount;

        for (CashType cashType: CashType.values()) {
            int count = Math.min(remainingAmount / cashType.value, cashInventory.get(cashType));
            if (count > 0) {
                dispenseCash.put(cashType, count);
                remainingAmount -= cashType.value * amount;
                cashInventory.put(cashType, cashInventory.get(cashType) - count);
            }
        }

        // we couldn't make this exact change
        if (remainingAmount > 0) {
            // Rollback transaction
            for (Map.Entry<CashType, Integer> entry: dispenseCash.entrySet()) {
                cashInventory.put(entry.getKey(), cashInventory.get(entry.getKey()) + entry.getValue());
            }
            return null;
        }
        return dispenseCash;
    }

    public void addCash(CashType cashType, int count) {
        cashInventory.put(cashType, cashInventory.get(cashType) + count);
    }
}
