package org.example.vendingMachine.vendingMachineStates.concreteStates;

import org.example.vendingMachine.vendingMachineStates.VendingMachineContext;
import org.example.vendingMachine.vendingMachineStates.VendingMachineState;

public class DispenseState implements VendingMachineState {
    @Override
    public String getStateName() {
        return "DispenseState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        return new IdleState();
    }
}
