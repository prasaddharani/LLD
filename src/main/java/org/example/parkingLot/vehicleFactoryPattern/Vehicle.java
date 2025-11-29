package org.example.parkingLot.vehicleFactoryPattern;

import org.example.parkingLot.enums.DurationType;
import org.example.parkingLot.fareStrategyPattern.ParkingFeeStrategy;

public abstract class Vehicle {
    private String vehicleType;
    private String licensePlate;
    private ParkingFeeStrategy parkingFeeStrategy;

    public Vehicle(String vehicleType, String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.parkingFeeStrategy = parkingFeeStrategy;
    }

    public String getVehicleType () {
        return this.vehicleType;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public double calculateFee(int duration, DurationType durationType) {
        return parkingFeeStrategy.calculateFee(vehicleType, duration, durationType);
    }
}
