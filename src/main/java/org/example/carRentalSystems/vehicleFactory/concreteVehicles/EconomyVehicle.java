package org.example.carRentalSystems.vehicleFactory.concreteVehicles;

import org.example.carRentalSystems.enums.VehicleStatus;
import org.example.carRentalSystems.enums.VehicleType;
import org.example.carRentalSystems.vehicleFactory.Vehicle;

public class EconomyVehicle extends Vehicle {

    public static final double RATE_MULTIPLIER = 1.5;
    public EconomyVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }


    @Override
    public double calculateRentalFee(int days) {
        return days * RATE_MULTIPLIER * getBaseRentalPrice();
    }
}
