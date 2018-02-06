package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static oobootcamp.parkinglot.exception.ParkingLotException.Message.*;

public abstract class ParkingPerson {
    private final List<ParkingLot> parkingLots;
    protected final String name;
    protected ParkingStrategy parkingStrategy;

    protected ParkingPerson(String name) {
        this.parkingLots = new ArrayList<>();
        this.name = name;
    }

    public void manage(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public void manage(List<ParkingLot> parkingLots) {
        this.parkingLots.addAll(parkingLots);
    }

    public Receipt park(Car car) throws ParkingLotException {
        if (parkingLots.isEmpty()) {
            throw new ParkingLotException(PARKING_BOY_HAS_NO_PARKING_LOT.toString());
        }
        Receipt receipt = parkingStrategy.findTargetParkingLot(parkingLots)
                .orElseThrow(() -> new ParkingLotException(NO_AVAILABLE_SPACE.toString()))
                .park(car);
        return receipt;
    }

    private Optional<ParkingLot> findParkingLotByName(String parkingLotName) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLotName.equals(parkingLot.getName()))
                .findFirst();
    }

    public Car pick(Receipt receipt) throws ParkingLotException {
        return findParkingLotByName(receipt.getParkingLotName())
                .orElseThrow(() -> new ParkingLotException(PARKING_LOT_NOT_FOUND.toString()))
                .pick(receipt);
    }

    public String getName() {
        return name;
    }

    public boolean canFindCarByReceipt(Receipt receipt) {
        Optional<ParkingLot> parkingLot = findParkingLotByName(receipt.getParkingLotName());
        if (!parkingLot.isPresent()) {
            return false;
        }
        return parkingLot.get().findByLicense(receipt.getCarLicense()).isPresent();
    }
}
