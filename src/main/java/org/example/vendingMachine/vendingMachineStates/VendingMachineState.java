package org.example.vendingMachine.vendingMachineStates;

public interface VendingMachineState {
    String getStateName();
    VendingMachineState next(VendingMachineContext context);
}
