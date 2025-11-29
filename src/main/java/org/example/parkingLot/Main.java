package org.example.parkingLot;

import org.example.parkingLot.ParkingLotController.ParkingLot;
import org.example.parkingLot.enums.DurationType;
import org.example.parkingLot.fareStrategyPattern.ParkingFeeStrategy;
import org.example.parkingLot.fareStrategyPattern.concreteStrategies.BasicHourlyRateStrategy;
import org.example.parkingLot.fareStrategyPattern.concreteStrategies.PremiumRateStrategy;
import org.example.parkingLot.parkingSpot.ParkingSpot;
import org.example.parkingLot.parkingSpot.concreteParkingSpot.BikeParkingSpot;
import org.example.parkingLot.parkingSpot.concreteParkingSpot.CarParkingSpot;
import org.example.parkingLot.paymentStrategies.PaymentStrategy;
import org.example.parkingLot.paymentStrategies.concreteStrategies.CashPayment;
import org.example.parkingLot.paymentStrategies.concreteStrategies.CreditCardPayment;
import org.example.parkingLot.vehicleFactoryPattern.Vehicle;
import org.example.parkingLot.vehicleFactoryPattern.VehicleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ParkingSpot car1 = new CarParkingSpot(1, "car");
        ParkingSpot car2 = new CarParkingSpot(2, "car");
        ParkingSpot bike1 = new BikeParkingSpot(3, "bike");
        ParkingSpot bike2 = new BikeParkingSpot(4, "bike");

        List<ParkingSpot> parkingSpots = new ArrayList<>(List.of(car1, car2, bike1, bike2));
        ParkingLot parkingLot = new ParkingLot(parkingSpots);

        ParkingFeeStrategy basicHourlyRateStrategy = new BasicHourlyRateStrategy();
        ParkingFeeStrategy premiumRateStrategy = new PremiumRateStrategy();

        Vehicle carVehicle1 = VehicleFactory.createVehicle("car", "1234", basicHourlyRateStrategy);
        Vehicle carVehicle2 = VehicleFactory.createVehicle("car", "1111", premiumRateStrategy);

        Vehicle bikeVehicle1 = VehicleFactory.createVehicle("bike", "1221", basicHourlyRateStrategy);
        Vehicle bikeVehicle2 = VehicleFactory.createVehicle("bike", "1222", basicHourlyRateStrategy);

        ParkingSpot parkingSpot1 = parkingLot.parkVehicle(carVehicle1);
        ParkingSpot parkingSpot2 = parkingLot.parkVehicle(carVehicle2);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select payment method for your vehicle:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");

        int paymentMethod = scanner.nextInt();
        if (parkingSpot1 != null) {
            double amount = parkingSpot1.getVehicle().calculateFee(2, DurationType.HOURS);
            PaymentStrategy paymentStrategy = getPaymentStrategy(paymentMethod);
            paymentStrategy.processPayment(amount);
            parkingLot.vacate(parkingSpot1, carVehicle1);
        }
        scanner.close();
    }

    private static PaymentStrategy getPaymentStrategy(int paymentMethod) {
        return switch (paymentMethod) {
            case 1 -> new CreditCardPayment();
            default -> new CashPayment();
        };
    }
}
