package oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<ParkingLot> firstEmptyParkingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull()).findFirst();
        return firstEmptyParkingLot.isPresent() ? firstEmptyParkingLot.get().park(car) : null;
    }

    public Car pick(Receipt receipt) {
        for (ParkingLot parkingLot : parkingLots) {
            Car pickedCar = parkingLot.pick(receipt);
            if (pickedCar != null) {
                return pickedCar;
            }
        }
        return null;
    }
}
