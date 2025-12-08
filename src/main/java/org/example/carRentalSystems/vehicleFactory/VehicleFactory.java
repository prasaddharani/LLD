package org.example.carRentalSystems.vehicleFactory;

import org.example.carRentalSystems.enums.VehicleType;
import org.example.carRentalSystems.vehicleFactory.concreteVehicles.EconomyVehicle;
import org.example.carRentalSystems.vehicleFactory.concreteVehicles.LuxuryVehicle;
import org.example.carRentalSystems.vehicleFactory.concreteVehicles.SUVVehicle;

public class VehicleFactory {

    public static Vehicle createVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        return switch (type) {
            case SUV -> new SUVVehicle(registrationNumber, model, type, baseRentalPrice);
            case ECONOMY -> new EconomyVehicle(registrationNumber, model, type, baseRentalPrice);
            case LUXURY -> new LuxuryVehicle(registrationNumber, model, type, baseRentalPrice);
            default -> throw new IllegalArgumentException("Provided Vehicle type is not supported");
        };
    }
}
