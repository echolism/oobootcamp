package oobootcamp.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingStrategy implements ParkingStrategy{

    @Override
    public Optional<ParkingLot> findTargetParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(Comparator.comparingInt(ParkingLot::getNumberOfAvailableSlots));
    }
}
