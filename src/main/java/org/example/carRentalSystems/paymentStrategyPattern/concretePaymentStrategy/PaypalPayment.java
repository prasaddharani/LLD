package org.example.carRentalSystems.paymentStrategyPattern.concretePaymentStrategy;

import org.example.carRentalSystems.paymentStrategyPattern.PaymentStrategy;

public class PaypalPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}
