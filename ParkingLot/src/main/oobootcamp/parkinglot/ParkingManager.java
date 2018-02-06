package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static oobootcamp.parkinglot.exception.ParkingLotException.Message.PARKING_BOY_NOT_FOUND;

public class ParkingManager extends ParkingPerson {
    private List<ParkingPerson> parkingBoys;

    public ParkingManager() {
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

    public void employ(ParkingPerson parkingBoy, ParkingLot parkingLot) {
        parkingBoy.manage(parkingLot);
        parkingBoys.add(parkingBoy);
    }
}
