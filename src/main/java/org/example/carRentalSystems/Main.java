package org.example.carRentalSystems;

import org.example.carRentalSystems.enums.VehicleType;
import org.example.carRentalSystems.mainRentalSystem.RentalSystem;
import org.example.carRentalSystems.paymentStrategyPattern.PaymentStrategy;
import org.example.carRentalSystems.paymentStrategyPattern.concretePaymentStrategy.CashPayment;
import org.example.carRentalSystems.paymentStrategyPattern.concretePaymentStrategy.CreditCardPayment;
import org.example.carRentalSystems.paymentStrategyPattern.concretePaymentStrategy.PaypalPayment;
import org.example.carRentalSystems.utilityClasses.Location;
import org.example.carRentalSystems.utilityClasses.RentalStore;
import org.example.carRentalSystems.utilityClasses.Reservation;
import org.example.carRentalSystems.utilityClasses.User;
import org.example.carRentalSystems.vehicleFactory.Vehicle;
import org.example.carRentalSystems.vehicleFactory.VehicleFactory;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = RentalSystem.getInstance();

        RentalStore store1 = new RentalStore(1, "MR Palli Store",
                new Location("1", "TPT", "AP", "517501"));
        RentalStore store2 = new RentalStore(2, "Annamiah Store",
                new Location("1", "TPT", "AP", "517501"));

        rentalSystem.addStore(store1);
        rentalSystem.addStore(store2);

        Vehicle economyCar = VehicleFactory.createVehicle("EC01", "Toyota", VehicleType.ECONOMY, 50.0);
        Vehicle suvCar = VehicleFactory.createVehicle("SUV01", "Honda", VehicleType.SUV, 75.0);

        store1.addVehicle(economyCar);
        store2.addVehicle(suvCar);

        // List Vehicles for store 1 :
        Map<String, Vehicle> vehicles = store1.getAllVehicles();
        for(Map.Entry<String , Vehicle> entry : vehicles.entrySet()){
            System.out.println("Vehicle Id : " + entry.getKey() + "Vehicle Type : "
                    + entry.getValue().getType() + "Vehicle Number: " + entry.getValue().getRegistrationNumber());
        }

        // Register user
        User user1 = new User(123, "ABC" , "abc@gmail.com");
        User user2 = new User(345 , "BCD" , "bcd@yahoo.com");

        rentalSystem.registerUser(user1);
        rentalSystem.registerUser(user2);

        // Create reservations
        Reservation reservation1 = rentalSystem.createReservation(user1.getId(), economyCar.getRegistrationNumber(),
                store1.getId(), store1.getId(), new Date(2025 - 1900, 4, 1),
                new Date(2025 - 1900, 5, 15));


        // Process payment using different strategies (Strategy Pattern)
        Scanner scanner = new Scanner(System.in);
        System.out.println("nProcessing payment for reservation #" + reservation1.getId());
        System.out.println("Total amount: $" + reservation1.getTotalAmount());
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.println("3. PayPal");

        int choice = 1; // Default to credit card for this example
        // In a real application, you would get user input:
        // int choice = scanner.nextInt();
        PaymentStrategy paymentStrategy;
        switch (choice) {
            case 1:
                paymentStrategy = new CreditCardPayment();
                break;
            case 2:
                paymentStrategy = new CashPayment();
                break;
            case 3:
                paymentStrategy = new PaypalPayment();
                break;
            default:
                System.out.println("Invalid choice! Defaulting to credit card payment.");
                paymentStrategy = new CreditCardPayment();
                break;
        }
        boolean paymentSuccess = rentalSystem.processPayment(reservation1.getId(), paymentStrategy);
        if (paymentSuccess) {
            System.out.println("Payment successful!");

            // Start the rental
            rentalSystem.startRental(reservation1.getId());

            // Simulate rental period
            System.out.println("nSimulating rental period...");

            // Complete the rental
            rentalSystem.completeRental(reservation1.getId());
        } else {
            System.out.println("Payment failed!");
        }
    }


}
