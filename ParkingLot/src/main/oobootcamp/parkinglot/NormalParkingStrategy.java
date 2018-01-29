package oobootcamp.parkinglot;

import java.util.List;
import java.util.Optional;

public class NormalParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingLot> findTargetParkingLot(List<ParkingLot> parkingLots) {
        return findFirstEmptyParkingLot(parkingLots);
    }

    private Optional<ParkingLot> findFirstEmptyParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull()).findFirst();
    }
}
