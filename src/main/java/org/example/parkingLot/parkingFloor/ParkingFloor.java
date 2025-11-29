package org.example.parkingLot.parkingFloor;

import org.example.parkingLot.parkingSpot.ParkingSpot;
import org.example.parkingLot.vehicleFactoryPattern.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
    }

    private ParkingSpot findAvailableSpot(String vehicleType) {
        for (ParkingSpot parkingSpot: parkingSpots) {
            if (!parkingSpot.isOccupied() && parkingSpot.getSpotType().equalsIgnoreCase(vehicleType)) {
                return parkingSpot;
            }
        }
        return null;
    }

    public void addParkingSpot(ParkingSpot spot) {
        this.parkingSpots.add(spot);
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
