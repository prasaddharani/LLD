package org.example.atmMachine.statePattern.concreteAtmStates;

import org.example.atmMachine.statePattern.ATMState;
import org.example.atmMachine.statePattern.atmContext.ATMMachineContext;

public class IdleState implements ATMState {
    public IdleState() {
        System.out.println("ATM is in IdleState: Please insert youe card");
    }

    @Override
    public String getStateName() {
        return "IdleState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentState() != null) {
            return context.getCurrentState();
        }
        return this;
    }
}
