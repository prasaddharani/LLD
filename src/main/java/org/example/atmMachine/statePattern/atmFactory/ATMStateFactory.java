package org.example.atmMachine.statePattern.atmFactory;

import org.example.atmMachine.statePattern.ATMState;
import org.example.atmMachine.statePattern.concreteAtmStates.HasCardState;
import org.example.atmMachine.statePattern.concreteAtmStates.IdleState;
import org.example.atmMachine.statePattern.concreteAtmStates.SelectOperationState;
import org.example.atmMachine.statePattern.concreteAtmStates.TransactionState;

public class ATMStateFactory {

    public static ATMStateFactory INSTANCE = null;

    private ATMStateFactory() {}

    public static ATMStateFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ATMStateFactory();
        }
        return INSTANCE;
    }

    public ATMState createIdleState() {
        return new IdleState();
    }

    public ATMState createHasCardState() {
        return new HasCardState();
    }

    public ATMState createSelectOperationState() {
        return new SelectOperationState();
    }

    public ATMState createTransactionState() {
        return new TransactionState();
    }
}
