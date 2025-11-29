package org.example.parkingLot.fareStrategyPattern;

import org.example.parkingLot.enums.DurationType;

public interface ParkingFeeStrategy {
    double calculateFee(String vehicleType, int duration, DurationType durationType);
}
