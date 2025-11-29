package org.example.parkingLot.ParkingLotController;

import org.example.parkingLot.parkingSpot.ParkingSpot;
import org.example.parkingLot.vehicleFactoryPattern.Vehicle;

import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> parkingSpots;

    public ParkingLot(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }


    private ParkingSpot findAvailableSpot(String vehicleType) {
        for (ParkingSpot parkingSpot: parkingSpots) {
            if (!parkingSpot.isOccupied() && parkingSpot.getSpotType().equalsIgnoreCase(vehicleType)) {
                return parkingSpot;
            }
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = findAvailableSpot(vehicle.getVehicleType());
        if (parkingSpot != null) {
            parkingSpot.parkVehicle(vehicle);
            System.out.println("Vehicle parked successfully with spot: " + parkingSpot.getSpotNumber());
            return parkingSpot;
        }
        System.out.println("No Parking spot available for vehicle type: " + vehicle.getVehicleType());
        return null;
    }


    public ParkingSpot vacate(ParkingSpot parkingSpot, Vehicle vehicle) {
        if (parkingSpot != null && parkingSpot.isOccupied() && parkingSpot.getVehicle().equals(vehicle)) {
            parkingSpot.vacate();
            System.out.println(vehicle.getVehicleType() + " vacated the spot " + parkingSpot.getSpotNumber());
            return parkingSpot;
        } else {
            System.out.println("Invalid operation! Either the spot is already vacant "
                    + "or the vehicle does not match.");
        }
        return null;
    }

}
