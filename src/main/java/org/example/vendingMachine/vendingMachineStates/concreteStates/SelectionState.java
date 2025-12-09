package org.example.vendingMachine.vendingMachineStates.concreteStates;

import org.example.vendingMachine.vendingMachineStates.VendingMachineContext;
import org.example.vendingMachine.vendingMachineStates.VendingMachineState;

public class SelectionState implements VendingMachineState {
    @Override
    public String getStateName() {
        return "SelectionState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        if (!context.getInventory().hasItems()) {
            return new OutOfStockState();
        }
        // If no money left, go back to idle
        if (context.getCoins().isEmpty()) {
            return new IdleState();
        }

        if (context.getCurrentState() instanceof SelectionState) {
            return new DispenseState();
        }
        return this;
    }
}
