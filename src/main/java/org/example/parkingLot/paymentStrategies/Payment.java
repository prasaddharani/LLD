package org.example.parkingLot.paymentStrategies;

public class Payment {
    private PaymentStrategy paymentStrategy;
    private final double amount;

    public Payment(PaymentStrategy paymentStrategy, double amount) {
        this.paymentStrategy = paymentStrategy;
        this.amount = amount;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        paymentStrategy.processPayment(amount);
    }
}
