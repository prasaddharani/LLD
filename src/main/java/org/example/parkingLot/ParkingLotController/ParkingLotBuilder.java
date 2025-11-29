package org.example.parkingLot.ParkingLotController;

import org.example.parkingLot.parkingFloor.ParkingFloor;
import org.example.parkingLot.parkingSpot.concreteParkingSpot.BikeParkingSpot;
import org.example.parkingLot.parkingSpot.concreteParkingSpot.CarParkingSpot;

import java.util.List;

public class ParkingLotBuilder {
    private List<ParkingFloor> floors;

    public ParkingLotBuilder(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    private void addFloor(ParkingFloor floor) {
        this.floors.add(floor);
    }

    public ParkingLotBuilder createFloor(int floorNumber, int numOfCarSpots,
                                         int numOfBikeSpots, int... otherSpotCounts) {
        // Create a new parking floor
        ParkingFloor floor = new ParkingFloor(floorNumber);
        // Add car spots
        for (int i = 0; i < numOfCarSpots; i++) {
            floor.addParkingSpot(new CarParkingSpot(i + 1, "car"));
        }
        // Add bike spots
        for (int i = 0; i < numOfBikeSpots; i++) {
            floor.addParkingSpot(new BikeParkingSpot(numOfCarSpots + i + 1, "bike"));
        }
        // Add other types of spots if provided
        int spotOffset = numOfCarSpots + numOfBikeSpots;
        for (int i = 0; i < otherSpotCounts.length; i++) {
            for (int j = 0; j < otherSpotCounts[i]; j++) {
                // Dynamically add other vehicle type spots
                // Note: This uses OtherVehicle as a placeholder. In a real system,
                // you might want a more robust way to handle different vehicle types
//                floor.addParkingSpot(new OtherSpot(
//                        spotOffset + j + 1, new BasicHourlyRateStrategy()));
            }
            // Update the spot offset for the next type of vehicle
            spotOffset += otherSpotCounts[i];
        }
        // Add the configured floor to the list of floors
        floors.add(floor);
        return this;
    }

//    public ParkingLot build() {
//        //return new ParkingLot(floors);
//
//    }
}
