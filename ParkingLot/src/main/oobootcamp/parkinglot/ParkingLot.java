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
        if (findByLicense(car.getLicense()) != null) {
            return null;
        }
        parkedCars.add(car);
        car.setParked(true);
        return new Receipt(name, car.getLicense());
    }

    private Car findByLicense(String carLicense) {
        return parkedCars.stream()
                .filter(parkedCar -> carLicense.equals(parkedCar.getLicense()))
                .findFirst().orElse(null);
    }

    public Car pick(Receipt receipt) {
        Car parkedCar = findByLicense(receipt.getCarLicense());
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

    public boolean isFull() {
        return getNumberOfAvailableSlots() == 0;
    }
}
