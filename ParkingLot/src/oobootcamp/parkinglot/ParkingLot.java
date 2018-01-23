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

    public Receipt park(Car car) {
        if (parkedCars.size() >= capacity) {
            return null;
        }
        if (search(car) != null) {
            return null;
        }
        parkedCars.add(car);
        car.setParked(true);
        return new Receipt(name, car.getLicense());
    }

    private Car search(Car car) {
        for (Car parkedCar : parkedCars) {
            if (parkedCar.equals(car)) {
                return parkedCar;
            }
        }
        return null;
    }

    public Car pick(Car car) {
        Car parkedCar = search(car);
        if (parkedCar == null) {
            return null;
        }
        parkedCar.setParked(false);
        parkedCars.remove(parkedCar);
        return parkedCar;
    }

    public int getNumberOfAvailableSlots() {
        return capacity - parkedCars.size();
    }
}
