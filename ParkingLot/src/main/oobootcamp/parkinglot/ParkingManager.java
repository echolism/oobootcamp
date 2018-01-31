package oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingPerson {
    private List<ParkingPerson> parkingBoys;

    public ParkingManager() {
        this.parkingStrategy = new NormalParkingStrategy();
        parkingBoys = new ArrayList<>();
    }

    public Report parkByParkingBoy(ParkingPerson parkingBoy, Car car) {
        parkingBoy.manage(getParkingLots());
        Receipt receipt = parkingBoy.park(car);
        return new Report(parkingBoy.getName(), receipt);
    }

    public Report parkByParkingBoy(String parkingBoyName, Car car) {
        return parkByParkingBoy(parkingBoys.get(0), car);
    }

    public void manageParkingBoy(ParkingPerson parkingBoy) {
        parkingBoys.add(parkingBoy);
    }
}
