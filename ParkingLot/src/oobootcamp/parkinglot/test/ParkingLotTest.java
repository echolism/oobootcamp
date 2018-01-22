package oobootcamp.parkinglot.test;

import oobootcamp.parkinglot.Car;
import oobootcamp.parkinglot.ParkingLot;
import oobootcamp.parkinglot.Receipt;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    /**
     * Given I have a parking lot and a car
     * When I park the car to the parking lot
     * Then I will be able to pick the car from the parking lot
     */
    @Test
    public void testGivenParkingLotAndCarWhenParkCarThenCanPickCar() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("ABC");

        Receipt receipt = parkingLot.park(car);

        assertEquals("ABC", receipt.getCarName());
    }

    /**
     * Given the parking lot is full
     * When I park a car to the parking lot
     * Then I will fail to park the car
     */
    @Test
    public void testGivenParkingLotFullWhenParkCarThenFailedToParkCar() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setFull(true);
        Car car = new Car("ABC");

        Receipt receipt = parkingLot.park(car);

        assertNull(receipt);
    }

    /**
     * Given the parking lot is full
     * When I pick a car from the parking lot
     * Then I will be able to park a car to the parking lot
     */

    /**
     * Given I pick a car parked in the parking lot
     * When I try to pick the same car from the parking lot
     * Then I will fail to pick the car
     */

    /**
     * Given I have a parking lot
     * When I try to pick a not-parked car from the parking lot
     * Then I will fail to pick the car
     */
}
