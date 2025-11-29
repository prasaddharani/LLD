package org.example.parkingLot.vehicleFactoryPattern;

import org.example.parkingLot.fareStrategyPattern.ParkingFeeStrategy;
import org.example.parkingLot.vehicleFactoryPattern.concreteVehicles.BikeVehicle;
import org.example.parkingLot.vehicleFactoryPattern.concreteVehicles.CarVehicle;
import org.example.parkingLot.vehicleFactoryPattern.concreteVehicles.OtherVehicle;

public class VehicleFactory {

    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        if ("car".equalsIgnoreCase(vehicleType)) {
            return new CarVehicle(vehicleType, licensePlate, parkingFeeStrategy);
        } else if ("bike".equalsIgnoreCase(vehicleType)) {
            return new BikeVehicle(vehicleType, licensePlate, parkingFeeStrategy);
        }
        return new OtherVehicle(vehicleType, licensePlate, parkingFeeStrategy);
    }
}
