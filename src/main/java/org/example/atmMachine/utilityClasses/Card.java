package org.example.atmMachine.utilityClasses;

public class Card {
    private String cardNumber;
    private int pin;
    private String accountNumber;

    public Card(String cardNumber, int pin, String accountNumber) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {return cardNumber;}

    public String getAccountNumber() {return accountNumber;}

    public boolean isValidPin(int enteredPin) {
        return this.pin == enteredPin;
    }

}
