package org.example.parkingLot.parkingSpot.concreteParkingSpot;

import org.example.parkingLot.parkingSpot.ParkingSpot;
import org.example.parkingLot.vehicleFactoryPattern.Vehicle;

public class BikeParkingSpot extends ParkingSpot {
    public BikeParkingSpot(int spotNumber, String spotType) {
        super(spotNumber, spotType);
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle) {
        return "bike".equalsIgnoreCase(vehicle.getVehicleType());
    }
}
