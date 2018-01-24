package oobootcamp.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public Optional<ParkingLot> findTargetParkingLot() {
        return getParkingLots().stream()
                .max(Comparator.comparingInt(ParkingLot::getNumberOfAvailableSlots));
    }


}
