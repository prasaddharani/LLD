package org.example.parkingLot.parkingSpot;

import org.example.parkingLot.vehicleFactoryPattern.Vehicle;

public abstract class ParkingSpot {
    private final int spotNumber;
    private boolean isOccupied;
    private final String spotType;
    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {
        if (isOccupied) {
            throw new IllegalArgumentException("Parking is already occupied");
        }
        if (!canParkVehicle(vehicle)) {
            throw new IllegalArgumentException("Vehicle cannot fit into this parking spot");
        }

        this.isOccupied = true;
        this.vehicle = vehicle;
    }

    public void vacate() {
        if (!isOccupied) {
            throw new IllegalArgumentException("No vehicle present in this parking spot to vacate");
        }

        this.isOccupied = false;
        this.vehicle = null;
    }


    public int getSpotNumber() {
        return this.spotNumber;
    }

    public String getSpotType() {
        return this.spotType;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }
}
