package org.example.parkingLot.vehicleFactoryPattern.concreteVehicles;

import org.example.parkingLot.fareStrategyPattern.ParkingFeeStrategy;
import org.example.parkingLot.vehicleFactoryPattern.Vehicle;

public class BikeVehicle extends Vehicle {
    public BikeVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        super(vehicleType, licensePlate, parkingFeeStrategy);
    }
}
