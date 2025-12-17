package org.example.atmMachine.statePattern.concreteAtmStates;

import org.example.atmMachine.statePattern.ATMState;
import org.example.atmMachine.statePattern.atmContext.ATMMachineContext;

public class HasCardState implements ATMState {
    @Override
    public String getStateName() {
        return "HasCardState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentState() == null) {
            return new IdleState();
        }

        if (context.getCurrentState() != null) {
            return context.getStateFactory().createHasCardState();
        }
        return null;
    }
}
