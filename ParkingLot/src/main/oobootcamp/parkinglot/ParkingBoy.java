package oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    private final List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public void manage(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Receipt park(Car car) {
        Optional<ParkingLot> availableEmptyParkingLot = findTargetParkingLot();
        return availableEmptyParkingLot.isPresent() ? availableEmptyParkingLot.get().park(car) : null;
    }

    public Optional<ParkingLot> findTargetParkingLot() {
        return findFirstEmptyParkingLot();
    }


    private Optional<ParkingLot> findFirstEmptyParkingLot() {
        return parkingLots.stream()
                    .filter(parkingLot -> !parkingLot.isFull()).findFirst();
    }

    private ParkingLot findParkingLotByName(String parkingLotName) {
        Optional<ParkingLot> targetParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLotName.equals(parkingLot.getName())).findFirst();
        return targetParkingLot.isPresent() ? targetParkingLot.get() : null;
    }

    public Car pick(Receipt receipt) {
        ParkingLot parkingLot = findParkingLotByName(receipt.getParkingLotName());
        if (parkingLot == null) {
            return null;
        }
        return parkingLot.pick(receipt);
    }
}
