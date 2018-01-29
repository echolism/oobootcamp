package oobootcamp.parkinglot;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingLot> findTargetParkingLot(List<ParkingLot> parkingLots);
}
