package org.example.parkingLot.parkingSpot.concreteParkingSpot;

import org.example.parkingLot.parkingSpot.ParkingSpot;
import org.example.parkingLot.vehicleFactoryPattern.Vehicle;

public class CarParkingSpot extends ParkingSpot {
    public CarParkingSpot(int spotNumber, String spotType) {
        super(spotNumber, spotType);
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle) {
        return "car".equalsIgnoreCase(vehicle.getVehicleType());
    }
}
