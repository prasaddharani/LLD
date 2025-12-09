package org.example.vendingMachine.vendingMachineStates.concreteStates;

import org.example.vendingMachine.vendingMachineStates.VendingMachineContext;
import org.example.vendingMachine.vendingMachineStates.VendingMachineState;

public class HasMoneyState implements VendingMachineState {
    @Override
    public String getStateName() {
        return "HasMoneyState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        if (!context.getInventory().hasItems()) {
            return new OutOfStockState();
        }
        if (context.getCoins().isEmpty()) {
            return new IdleState();
        }
        if (context.getCurrentState() instanceof HasMoneyState) {
            return new SelectionState();
        }
        return this;
    }
}
