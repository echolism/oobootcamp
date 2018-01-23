package oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private final String name;
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(String name) {
        this.name = name;
        this.parkingLots = new ArrayList<>();
    }

    public void manage(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public Receipt park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        return null;
    }

    public Car pick(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            Car pickedCar = parkingLot.pick(car);
            if (pickedCar != null) {
                return pickedCar;
            }
        }
        return null;
    }
}
