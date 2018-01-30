package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ParkingPerson {

    private final List<ParkingLot> parkingLots;
    protected ParkingStrategy parkingStrategy;

    public ParkingPerson() {
        this.parkingLots = new ArrayList<>();
    }

    public void manage(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public Receipt park(Car car) throws ParkingLotException {
        return parkingStrategy.findTargetParkingLot(parkingLots)
                .orElseThrow(() -> new ParkingLotException(ParkingLotException.Message.NO_AVAILABLE_SPACE.toString()))
                .park(car);
    }

    private Optional<ParkingLot> findParkingLotByName(String parkingLotName) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLotName.equals(parkingLot.getName()))
                .findFirst();
    }

    public Car pick(Receipt receipt) throws ParkingLotException {
        return findParkingLotByName(receipt.getParkingLotName())
                .orElseThrow(() -> new ParkingLotException(ParkingLotException.Message.PARKING_LOT_NOT_FOUND.toString()))
                .pick(receipt);
    }
}
