package oobootcamp.parkinglot.test;

import oobootcamp.parkinglot.Car;
import oobootcamp.parkinglot.ParkingLot;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    /**
     * Given I have an empty parking lot and a car
     * When I park the car to the parking lot
     * Then I will be able to park the car
     */
    @Test
    public void testGivenEmptyParkingLotAndCarWhenParkCarThenCanParkCar() {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");

        parkingLot.park(car);

        assertTrue(car.isParked());
    }

    /**
     * Given I have an full parking lot and a car
     * When I park the car to the parking lot
     * Then I will be failed to park the car
     */
    @Test
    public void testGivenFullParkingLotAndCarWhenParkCarThenFailedToParkCar() {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingLot.park(carA);

        parkingLot.park(carB);

        assertFalse(carB.isParked());
    }
}
