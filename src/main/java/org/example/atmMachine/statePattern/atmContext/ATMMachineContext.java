package org.example.atmMachine.statePattern.atmContext;

import org.example.atmMachine.enums.CashType;
import org.example.atmMachine.enums.TransactionType;
import org.example.atmMachine.statePattern.ATMState;
import org.example.atmMachine.statePattern.atmFactory.ATMStateFactory;
import org.example.atmMachine.statePattern.concreteAtmStates.HasCardState;
import org.example.atmMachine.statePattern.concreteAtmStates.IdleState;
import org.example.atmMachine.statePattern.concreteAtmStates.SelectOperationState;
import org.example.atmMachine.statePattern.concreteAtmStates.TransactionState;
import org.example.atmMachine.utilityClasses.ATMInventory;
import org.example.atmMachine.utilityClasses.Account;
import org.example.atmMachine.utilityClasses.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ATMMachineContext {
    private ATMState currentState;
    private Card currentCard;
    private Account currentAccount;
    private ATMInventory atmInventory;
    private Map<String, Account> accounts;
    private ATMStateFactory stateFactory;
    private TransactionType transactionType;

    public ATMMachineContext() {
        this.stateFactory = ATMStateFactory.getInstance();
        this.currentState = stateFactory.createIdleState();
        this.atmInventory = new ATMInventory();
        this.accounts = new HashMap<>();
        System.out.println("ATM initialized in: " + currentState.getStateName());
    }

    public void advanceState() {
        ATMState nextState = currentState.next(this);
        currentState = nextState;
        System.out.println("Current State: " + currentState.getStateName());
    }

    public void insertCard(Card card) {
        if (currentState instanceof IdleState) {
            System.out.println("Card Inserted");
            this.currentCard = card;
            advanceState();
        } else {
            System.out.println("Cannot insert card in: " + currentState.getStateName());
        }
    }

    public void enterPin(int pin) {
        if (currentState instanceof HasCardState) {
            if (currentCard.isValidPin(pin)) {
                System.out.println("PIN authenticated successfully");
                currentAccount = accounts.get(currentCard.getAccountNumber());
                advanceState();
            } else {
                System.out.println("Invalid PIN. Please try again");
            }
        } else {
            System.out.println("Cannot enter PIN in: "+ currentState.getStateName());
        }
    }

    public void selectOperation(TransactionType type) {
        if (currentState instanceof TransactionState) {
            System.out.println("Selected operation: " + type.name());
            this.transactionType = type;
            advanceState();
        } else {
            System.out.println("Cannot select operation in: "+ currentState.getStateName());
        }
    }

    public void performTransaction(int amount) {
        if (currentState instanceof TransactionState) {
            try {
                if (transactionType == TransactionType.WITHDRAW_CASH) {
                    performWithdrawal(amount);
                } else if (transactionType == TransactionType.CHECK_BALANCE) {
                    checkBalance();
                }
                advanceState();
            } catch (Exception ex) {
                System.out.println("Transaction Failed: " + ex.getMessage());
                currentState = stateFactory.createSelectOperationState();
            }
        } else {
            System.out.println("Cannot perform transaction in: "+ currentState.getStateName());
        }
    }

    private void checkBalance() {
    }

    private void performWithdrawal(int amount) throws Exception {
        if (!currentAccount.withdraw(amount)) {
            throw new Exception("Insufficient funds in the account");
        }

        if (!atmInventory.hasSufficientCash(amount)) {
            throw new Exception("Insufficient cash in ATM");
        }

        Map<CashType, Integer> dispenseCash = atmInventory.dispenseCash(amount);
        if (dispenseCash == null) {
            // Rollback the account withdrawal
            currentAccount.deposit(amount);
            throw new Exception("Unable to dispense exact amount");
        }
        System.out.println("Transaction successful. Please collect your cash:");
        for (Map.Entry<CashType, Integer> entry : dispenseCash.entrySet()) {
            System.out.println(entry.getValue() + " x $" + entry.getKey().value);
        }
    }

    // Reset ATM state
    private void resetATM() {
        this.currentCard = null;
        this.currentAccount = null;
        this.transactionType = null;
        this.currentState = stateFactory.createIdleState();
    }

    public ATMState getCurrentState() {
        return currentState;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public ATMInventory getAtmInventory() {
        return atmInventory;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public ATMStateFactory getStateFactory() {
        return stateFactory;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void returnCard() {
        if (currentState instanceof HasCardState
                || currentState instanceof SelectOperationState
                || currentState instanceof TransactionState) {
            System.out.println("Card returned to customer");
            resetATM();
        } else {
            System.out.println("No card to return in " + currentState.getStateName());
        }
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}
