package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

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

    public Receipt park(Car car) throws ParkingLotException {
        return findTargetParkingLot().orElseThrow(() -> new ParkingLotException(ParkingLotException.Message.NO_AVAILABLE_SPACE.toString())).park(car);
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

    public Car pick(Receipt receipt) throws ParkingLotException {
        ParkingLot parkingLot = findParkingLotByName(receipt.getParkingLotName());
        if (parkingLot == null) {
            throw new ParkingLotException(ParkingLotException.Message.PARKING_LOT_NOT_FOUND.toString());
        }
        return parkingLot.pick(receipt);
    }
}
