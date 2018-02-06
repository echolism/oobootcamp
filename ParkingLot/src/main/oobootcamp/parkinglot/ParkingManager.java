package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static oobootcamp.parkinglot.exception.ParkingLotException.Message.CAR_NOT_FOUND;
import static oobootcamp.parkinglot.exception.ParkingLotException.Message.PARKING_BOY_NOT_FOUND;

public class ParkingManager extends ParkingPerson {
    private List<ParkingPerson> parkingBoys;

    public ParkingManager() {
        this("");
    }

    public ParkingManager(String name) {
        super(name);
        this.parkingStrategy = new NormalParkingStrategy();
        this.parkingBoys = new ArrayList<>();
    }

    public Report parkByParkingBoy(String parkingPersonName, Car car) {
        Optional<ParkingPerson> targetParkingBoy = findParkingPersonByName(parkingPersonName);
        Receipt receipt = targetParkingBoy.orElseThrow(() -> new ParkingLotException(PARKING_BOY_NOT_FOUND.toString()))
                .park(car);
        return new Report(targetParkingBoy.get().getName(), receipt);
    }

    private Optional<ParkingPerson> findParkingPersonByName(String parkingPersonName) {
        return parkingBoys.stream()
                .filter(parkingPerson -> parkingPersonName.equals(parkingPerson.getName()))
                .findFirst();
    }

    public void employ(ParkingPerson parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public void delegate(String parkingPersonName, ParkingLot parkingLot) {
        delegate(parkingPersonName, Arrays.asList(parkingLot));
    }

    public void delegate(String parkingPersonName, List<ParkingLot> parkingLots) {
        delegate(findParkingPersonByName(parkingPersonName), parkingLots);
    }

    private void delegate(Optional<ParkingPerson> parkingBoy, List<ParkingLot> parkingLots) {
        parkingBoy.orElseThrow(() -> new ParkingLotException(PARKING_BOY_NOT_FOUND.toString()))
                .manage(parkingLots);
    }

    @Override
    public Car pick(Receipt receipt) {
        if (this.canFindCarByReceipt(receipt)) {
            return super.pick(receipt);
        }
        return pickByParkingBoy(receipt);
    }

    private Car pickByParkingBoy(Receipt receipt) {
        for (ParkingPerson parkingPerson : parkingBoys) {
            if (parkingPerson.canFindCarByReceipt(receipt)) {
                return parkingPerson.pick(receipt);
            }
        }
        throw new ParkingLotException(CAR_NOT_FOUND.toString());
    }
}
