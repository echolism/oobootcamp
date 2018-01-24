package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;

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

    public Receipt park(Car car) throws ParkingLotException {
        if (parkedCars.size() >= capacity) {
            throw new ParkingLotException(ParkingLotException.Message.NO_AVAILABLE_SPACE.toString());
        }
        if (findByLicense(car.getLicense()) != null) {
            throw new ParkingLotException(ParkingLotException.Message.CAR_IN_PARKING_LOT.toString());
        }
        parkedCars.add(car);
        return new Receipt(name, car.getLicense());
    }

    public Car findByLicense(String carLicense) {
        return parkedCars.stream()
                .filter(parkedCar -> carLicense.equals(parkedCar.getLicense()))
                .findFirst().orElse(null);
    }

    public Car pick(Receipt receipt) throws ParkingLotException {
        Car parkedCar = findByLicense(receipt.getCarLicense());
        if (parkedCar == null) {
            throw new ParkingLotException(ParkingLotException.Message.CAR_NOT_FOUND.toString());
        }
        parkedCars.remove(parkedCar);
        return parkedCar;
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
