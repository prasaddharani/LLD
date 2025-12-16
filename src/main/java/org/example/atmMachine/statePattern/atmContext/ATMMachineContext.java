package org.example.atmMachine.statePattern.atmContext;

import org.example.atmMachine.enums.TransactionType;
import org.example.atmMachine.statePattern.ATMState;
import org.example.atmMachine.statePattern.atmFactory.ATMStateFactory;

public class ATMMachineContext {
    private ATMState currentState;
    private ATMStateFactory stateFactory;
    private TransactionType transactionType;
}
