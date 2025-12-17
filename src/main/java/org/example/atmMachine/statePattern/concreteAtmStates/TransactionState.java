package org.example.atmMachine.statePattern.concreteAtmStates;

import org.example.atmMachine.statePattern.ATMState;
import org.example.atmMachine.statePattern.atmContext.ATMMachineContext;

public class TransactionState implements ATMState {
    @Override
    public String getStateName() {
        return "TransactionState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentCard() == null) {
            return context.getStateFactory().createIdleState();
        }
        return context.getStateFactory().createSelectOperationState();
    }
}
