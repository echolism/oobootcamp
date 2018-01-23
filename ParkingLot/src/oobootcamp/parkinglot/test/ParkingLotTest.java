package oobootcamp.parkinglot.test;

import oobootcamp.parkinglot.Car;
import oobootcamp.parkinglot.ParkingLot;
import oobootcamp.parkinglot.Receipt;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    /**
     * Given I have an empty parking lot and a car
     * When I park the car to the parking lot
     * Then I will be able to park the car and get receipt
     */
    @Test
    public void testGivenEmptyParkingLotAndCarWhenParkCarThenCanParkCar() {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");

        Receipt receipt = parkingLot.park(car);

        assertTrue(car.isParked());
        assertEquals("A", receipt.getCarLicense());
    }

    /**
     * Given I have a full parking lot and a car
     * When I park the car to the parking lot
     * Then I will be failed to park the car and cannot get receipt
     */
    @Test
    public void testGivenFullParkingLotAndCarWhenParkCarThenFailedToParkCar() {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingLot.park(carA);

        Receipt receipt = parkingLot.park(carB);

        assertFalse(carB.isParked());
        assertNull(receipt);
    }

    /**
     * Given I have a parking lot and a car parked in it
     * When I pick the car from the parking lot
     * Then I will be able to pick the car
     */
    @Test
    public void testGivenParkingLotAndParkedCarWhenPickCarThenCanPickCar() {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");
        parkingLot.park(car);

        Car pickedCar = parkingLot.pick(car);

        assertSame(car, pickedCar);
        assertFalse(pickedCar.isParked());
        assertEquals(1, parkingLot.getNumberOfAvailableSlots());
    }

    /**
     * Given I have an empty parking lot and a car
     * When I pick the car from the parking lot
     * Then I will be failed to pick the car
     */
    @Test
    public void testGivenParkingLotAndCarWhenPickCarThenFailedToPickCar() {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");

        Car pickedCar = parkingLot.pick(car);

        assertNull(pickedCar);
    }

    /**
     * Given I have a parking lot and a with parked car A and B
     * When I pick car B from the parking lot
     * Then I will able to pick car B
     */
    @Test
    public void testGivenParkingLotAndParked2CarWhenPickCarBThenCanPickCarB() {
        ParkingLot parkingLot = new ParkingLot("1", 2);
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingLot.park(carA);
        parkingLot.park(carB);

        Car pickedCar = parkingLot.pick(carB);

        assertSame(carB, pickedCar);
        assertFalse(pickedCar.isParked());
        assertEquals(1, parkingLot.getNumberOfAvailableSlots());
    }

    /**
     * Given I have a parking lot and a car parked in it
     * When I pick the car from the parking lot
     * Then I will be failed to pick the car again
     */
    @Test
    public void testGivenParkingLotAndParkedCarWhenPickCarThenFailedToPickCar() {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");
        parkingLot.park(car);
        parkingLot.pick(car);

        Car pickedCar = parkingLot.pick(car);

        assertNull(pickedCar);
    }

    /**
     * Given I have a parking lot and a car parked in it
     * When I park the car to the parking lot
     * Then I will be failed to park the car again
     */
    @Test
    public void testGivenParkingLotAndParkedCarWhenParkCarThenFailedToParkCar() {
        ParkingLot parkingLot = new ParkingLot("1", 2);
        Car car = new Car("A");
        parkingLot.park(car);

        Receipt receipt = parkingLot.park(car);

        assertTrue(car.isParked());
        assertNull(receipt);
    }
}
