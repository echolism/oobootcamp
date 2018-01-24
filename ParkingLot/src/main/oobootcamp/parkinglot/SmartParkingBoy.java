package oobootcamp.parkinglot;

import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public Optional<ParkingLot> findTargetParkingLot() {
        List<ParkingLot> parkingLots = getParkingLots();
        if (parkingLots.size() == 1) {
            return Optional.of(parkingLots.get(0));
        }
        if (parkingLots.size() == 2) {
            return Optional.of(parkingLots.get(0).getNumberOfAvailableSlots() >= parkingLots.get(1).getNumberOfAvailableSlots() ? parkingLots.get(0) : parkingLots.get(1));
        }
        if (parkingLots.get(0).getNumberOfAvailableSlots() >= parkingLots.get(1).getNumberOfAvailableSlots() && parkingLots.get(0).getNumberOfAvailableSlots() >= parkingLots.get(2).getNumberOfAvailableSlots()) {
            return Optional.of(parkingLots.get(0));
        }
        if (parkingLots.get(1).getNumberOfAvailableSlots() >= parkingLots.get(0).getNumberOfAvailableSlots() && parkingLots.get(1).getNumberOfAvailableSlots() >= parkingLots.get(2).getNumberOfAvailableSlots()) {
            return Optional.of(parkingLots.get(1));
        }
        return Optional.of(parkingLots.get(2));
    }


}
