package org.example.vendingMachine.vendingMachineStates.concreteStates;

import org.example.vendingMachine.vendingMachineStates.VendingMachineContext;
import org.example.vendingMachine.vendingMachineStates.VendingMachineState;

public class IdleState implements VendingMachineState {
    @Override
    public String getStateName() {
        return "IdleState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        if (!context.getInventory().hasItems()) {
            return new OutOfStockState();
        }
        if (!context.getCoins().isEmpty()) {
            return new HasMoneyState();
        }
        return this;
    }
}
