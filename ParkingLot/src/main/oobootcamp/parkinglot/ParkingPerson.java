package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ParkingPerson {
    // TODO: Factory method
    private final List<ParkingLot> parkingLots;
    private final String name;
    protected ParkingStrategy parkingStrategy;

    public ParkingPerson() {
        this("");
    }

    public ParkingPerson(String name) {
        this.parkingLots = new ArrayList<>();
        this.name = name;
    }

    protected List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void manage(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public void manage(List<ParkingLot> parkingLots) {
        parkingLots.forEach(this::manage);
    }

    public Receipt park(Car car) throws ParkingLotException {
        Receipt receipt = parkingStrategy.findTargetParkingLot(parkingLots)
                .orElseThrow(() -> new ParkingLotException(ParkingLotException.Message.NO_AVAILABLE_SPACE.toString()))
                .park(car);
        receipt.setParkingPersonName(name);
        return receipt;
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
