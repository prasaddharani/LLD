package org.example.carRentalSystems.utilityClasses;

import org.example.carRentalSystems.enums.VehicleStatus;
import org.example.carRentalSystems.vehicleFactory.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalStore {
    private int id;
    private String name;
    private Location location;
    private Map<String, Vehicle> vehicles;


    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = new HashMap<>();
    }

    public void addVehicle(String registrationNumber, Vehicle vehicle) {
        vehicles.put(registrationNumber, vehicle);
    }

    public void removeVehicle(String registrationNumber) {
        vehicles.remove(registrationNumber);
    }

    public boolean isVehicleAvailable(String registrationNumber) {
        Vehicle vehicle = vehicles.get(registrationNumber);
        return VehicleStatus.AVAILABLE == vehicle.getStatus();
    }

    public List<Vehicle> getAllAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Map.Entry<String, Vehicle> entry: vehicles.entrySet()) {
            if (VehicleStatus.AVAILABLE == entry.getValue().getStatus()) {
                availableVehicles.add(entry.getValue());
            }
        }
        return availableVehicles;
    }

    public Vehicle getVehicle(String registrationNumber) {
        return vehicles.get(registrationNumber);
    }

    public int getId() {
        return id;
    }
}
