package org.example.atmMachine.statePattern;

import org.example.atmMachine.statePattern.atmContext.ATMMachineContext;

public interface ATMState {
    String getStateName();
    ATMState next(ATMMachineContext context);
}
