package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static oobootcamp.parkinglot.exception.ParkingLotException.Message.*;

public class ParkingLot {
    private final String name;
    private final int capacity;
    private List<Car> parkedCars;

    public ParkingLot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.parkedCars = new ArrayList<>();
    }

    public Receipt park(Car car) throws ParkingLotException {
        if (parkedCars.size() >= capacity) {
            throw new ParkingLotException(NO_AVAILABLE_SPACE.toString());
        }
        if (findByLicense(car.getLicense()).isPresent()) {
            throw new ParkingLotException(CAR_IN_PARKING_LOT.toString());
        }
        parkedCars.add(car);
        return new Receipt(name, car.getLicense());
    }

    public Optional<Car> findByLicense(String carLicense) {
        return parkedCars.stream()
                .filter(parkedCar -> carLicense.equals(parkedCar.getLicense()))
                .findFirst();
    }

    public Car pick(Receipt receipt) throws ParkingLotException {
        Optional<Car> targetCar = findByLicense(receipt.getCarLicense());
        targetCar.orElseThrow(() -> new ParkingLotException(CAR_NOT_FOUND.toString()));
        parkedCars.remove(targetCar.get());
        return targetCar.get();
    }

    public int getNumberOfAvailableSlots() {
        return capacity - parkedCars.size();
    }

    public boolean isFull() {
        return getNumberOfAvailableSlots() == 0;
    }

    public String getName() {
        return name;
    }
}
