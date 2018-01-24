package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParkingLotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Given I have an empty parking lot and a car
     * When I park the car to the parking lot
     * Then I will be able to park the car
     */
    @Test
    public void testGivenEmptyParkingLotAndCarWhenParkCarThenCanParkCar() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");

        Receipt receipt = parkingLot.park(car);

        assertEquals("A", receipt.getCarLicense());
    }

    /**
     * Given I have a full parking lot and a car
     * When I park the car to the parking lot
     * Then I will be failed to park the car
     */
    @Test
    public void testGivenFullParkingLotAndCarWhenParkCarThenFailedToParkCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage("No available space");

        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingLot.park(carA);

        parkingLot.park(carB);
    }

    /**
     * Given I have a parking lot and a car parked in it
     * When I pick the car from the parking lot
     * Then I will be able to pick the car
     */
    @Test
    public void testGivenParkingLotAndParkedCarWhenPickCarThenCanPickCar() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");
        Receipt receipt = parkingLot.park(car);

        Car pickedCar = parkingLot.pick(receipt);

        assertSame(car, pickedCar);
        assertEquals(1, parkingLot.getNumberOfAvailableSlots());
    }

    /**
     * Given I have an empty parking lot and a receipt
     * When I pick the car from the parking lot
     * Then I will be failed to pick the car
     */
    @Test
    public void testGivenParkingLotAndCarWhenPickCarThenFailedToPickCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage("Car is not in parking lot");

        ParkingLot parkingLot = new ParkingLot("1", 1);
        Receipt receipt = new Receipt("1", "A");

        parkingLot.pick(receipt);
    }

    /**
     * Given I have a parking lot and a with parked car A and B
     * When I pick car B from the parking lot
     * Then I will able to pick car B
     */
    @Test
    public void testGivenParkingLotAndParked2CarWhenPickCarBThenCanPickCarB() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot("1", 2);
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingLot.park(carA);
        Receipt receiptB = parkingLot.park(carB);

        Car pickedCar = parkingLot.pick(receiptB);

        assertSame(carB, pickedCar);
        assertEquals(1, parkingLot.getNumberOfAvailableSlots());
    }

    /**
     * Given I have a parking lot and a car parked in it
     * When I pick the car from the parking lot
     * Then I will be failed to pick the car again
     */
    @Test
    public void testGivenParkingLotAndParkedCarWhenPickCarThenFailedToPickCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage("Car is not in parking lot");

        ParkingLot parkingLot = new ParkingLot("1", 1);
        Car car = new Car("A");
        Receipt receipt = parkingLot.park(car);
        parkingLot.pick(receipt);

        parkingLot.pick(receipt);
    }

    /**
     * Given I have a parking lot and a car parked in it
     * When I park the car to the parking lot
     * Then I will be failed to park the car again
     */
    @Test
    public void testGivenParkingLotAndParkedCarWhenParkCarThenFailedToParkCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage("Car is already in parking lot");

        ParkingLot parkingLot = new ParkingLot("1", 2);
        Car car = new Car("A");

        parkingLot.park(car);
        parkingLot.park(car);
    }
}
