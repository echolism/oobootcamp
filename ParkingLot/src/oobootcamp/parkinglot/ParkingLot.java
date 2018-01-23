package oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final String name;
    private final int capacity;
    private List<Car> parkedCars;

    public ParkingLot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
    }

    public void park(Car car) {
        if (parkedCars.size() < capacity) {
            parkedCars.add(car);
            car.setParked(true);
        }
    }
}
