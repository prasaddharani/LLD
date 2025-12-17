package org.example.atmMachine.statePattern.concreteAtmStates;

import org.example.atmMachine.statePattern.ATMState;
import org.example.atmMachine.statePattern.atmContext.ATMMachineContext;

public class SelectOperationState implements ATMState {
    @Override
    public String getStateName() {
        return "SelectOperationState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentState() == null) {
            return new IdleState();
        }

        if (context.getTransactionType() != null) {
          return context.getStateFactory().createTransactionState();
        }
        return null;
    }
}
