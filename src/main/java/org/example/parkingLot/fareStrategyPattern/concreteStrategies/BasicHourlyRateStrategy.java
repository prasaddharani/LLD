package org.example.parkingLot.fareStrategyPattern.concreteStrategies;

import org.example.parkingLot.enums.DurationType;
import org.example.parkingLot.fareStrategyPattern.ParkingFeeStrategy;

public class BasicHourlyRateStrategy implements ParkingFeeStrategy {
    @Override
    public double calculateFee(String vehicleType, int duration, DurationType durationType) {
        return switch (vehicleType) {
            case "car" -> DurationType.HOURS == durationType? duration * 10: duration * 24 * 10;
            case "bike" -> DurationType.HOURS == durationType? duration * 5: duration * 24 * 5;
            default -> DurationType.HOURS == durationType? duration * 15: duration * 24 * 15;
        };
    }
}
