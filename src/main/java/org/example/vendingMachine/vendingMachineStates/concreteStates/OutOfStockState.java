package org.example.vendingMachine.vendingMachineStates.concreteStates;

import org.example.vendingMachine.vendingMachineStates.VendingMachineContext;
import org.example.vendingMachine.vendingMachineStates.VendingMachineState;

public class OutOfStockState implements VendingMachineState {
    @Override
    public String getStateName() {
        return "OutOfStockState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        return this;
    }
}
