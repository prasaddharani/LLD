package org.example.carRentalSystems.paymentStrategyPattern;

public class PaymentProcessor {
    public boolean processPayment(double payment, PaymentStrategy strategy) {
        strategy.processPayment(payment);
        return true;
    }
 }
