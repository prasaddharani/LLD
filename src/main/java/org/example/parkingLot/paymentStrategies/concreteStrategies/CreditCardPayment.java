package org.example.parkingLot.paymentStrategies.concreteStrategies;

import org.example.parkingLot.paymentStrategies.PaymentStrategy;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: " + amount);
    }
}
