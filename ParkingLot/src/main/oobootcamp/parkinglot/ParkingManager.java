package oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager extends ParkingPerson {
    private List<ParkingPerson> parkingBoys;

    public ParkingManager() {
        this.parkingStrategy = new NormalParkingStrategy();
        parkingBoys = new ArrayList<>();
    }

    public Receipt parkByParkingBoy(ParkingPerson parkingBoy, Car car) {
        parkingBoy.manage(getParkingLots());
        return parkingBoy.park(car);
    }

    public Receipt parkByParkingBoy(String parkingBoyName, Car car) {
        return parkByParkingBoy(parkingBoys.get(0), car);
    }

    public void manageParkingBoy(ParkingPerson parkingBoy) {
        parkingBoys.add(parkingBoy);
    }
}
