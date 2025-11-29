package org.example.parkingLot.paymentStrategies.concreteStrategies;

import org.example.parkingLot.paymentStrategies.PaymentStrategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of: " + amount);
    }
}
