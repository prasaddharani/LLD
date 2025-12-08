package org.example.carRentalSystems.utilityClasses;

import org.example.carRentalSystems.enums.ReservationStatus;
import org.example.carRentalSystems.enums.VehicleStatus;
import org.example.carRentalSystems.vehicleFactory.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
    private int id;
    private User user;
    private Vehicle vehicle;
    private RentalStore pickupStore;
    private RentalStore rentalStore;
    private Date startDate;
    private Date endDate;
    private ReservationStatus status;
    private double totalAmount;


    public Reservation(int id, User user, Vehicle vehicle, RentalStore pickupStore, RentalStore rentalStore, Date startDate, Date endDate) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.pickupStore = pickupStore;
        this.rentalStore = rentalStore;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;

        long timeInMilli = endDate.getTime() - startDate.getTime();
        int days = (int) (timeInMilli / (1000 * 60 * 60 * 24) + 1);
        this.totalAmount = vehicle.calculateRentalFee(days);
    }

    public void confirmReservation() {
        if (ReservationStatus.PENDING == status) {
            this.status = ReservationStatus.CONFIRMED;
            this.vehicle.setStatus(VehicleStatus.RESERVED);
        }
    }

    public void startRental() {
        if (ReservationStatus.CONFIRMED == status) {
            this.status = ReservationStatus.IN_PROGRESS;
            this.vehicle.setStatus(VehicleStatus.RENTED);
        }
    }

    public void completeRental() {
        if (ReservationStatus.IN_PROGRESS == status) {
            this.status = ReservationStatus.COMPLETED;
            this.vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public void cancelReservation() {
        if (ReservationStatus.PENDING == status
                || ReservationStatus.CONFIRMED == status) {
            this.status = ReservationStatus.CANCELLED;
            this.vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public int getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
