package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static oobootcamp.parkinglot.exception.ParkingLotException.Message.*;
import static org.junit.Assert.*;

public class ParkingBoyTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Given parking boy has an empty parking lot and a car
     * When parking boy parks a car to the parking lot
     * Then parking boy will be able to park a car and get receipt
     */
    @Test
    public void testGivenParkingBoyWithEmptyParkingLotAndCarWhenParkCarThenCanParkCar() throws ParkingLotException {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");

        Receipt receipt = parkingBoy.park(car);

        assertEquals("A", receipt.getCarLicense());
    }

    /**
     * Given parking boy has a full parking lot and a car
     * When parking boy parks a car to the parking lot
     * Then parking boy will be failed to park a car
     */
    @Test
    public void testGivenParkingBoyWithFullParkingLotAndCarWhenParkCarThenFailedToParkCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(NO_AVAILABLE_SPACE.toString());

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingBoy.park(carA);

        parkingBoy.park(carB);
    }

    /**
     * Given parking boy has 2 empty parking lots and a car
     * When parking boy parks a car
     * Then parking boy will be able to park a car in 1st empty parking lot
     */
    @Test
    public void testGivenParkingBoyWith2EmptyParkingLotWhenParkCarThenCanParkCarTo1stParkingLot() throws ParkingLotException {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car car = new Car("A");

        Receipt receipt = parkingBoy.park(car);

        assertEquals("A", receipt.getCarLicense());
        assertEquals("1", receipt.getParkingLotName());
    }

    /**
     * Given parking boy has a full parking lot and an empty parking lot and a car
     * When parking boy parks a car
     * Then parking boy will be able to park a car in the 2nd parking lot
     */
    @Test
    public void testGivenParkingBoyWith1FullParkingLotAnd1EmptyParkingLotAndCarWhenParkCarThenCanParkCarToEmptyParkingLot() throws ParkingLotException {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingBoy.park(carA);

        Receipt receipt = parkingBoy.park(carB);

        assertEquals("B", receipt.getCarLicense());
        assertEquals("2", receipt.getParkingLotName());
    }

    /**
     * Given parking boy has 2 full parking lot and a car
     * When parking boy parks a car
     * Then parking boy will be failed to park a car
     */
    @Test
    public void testGivenParkingBoyWith2FullParkingLotAndCarWhenParkCarThenFailedToParkCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(NO_AVAILABLE_SPACE.toString());

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        Car carC = new Car("C");
        parkingBoy.park(carA);
        parkingBoy.park(carB);

        parkingBoy.park(carC);
    }

    /**
     * Given parking boy has 1 parking lot with a parked car
     * When parking boy picks a car from the parking lot
     * Then parking boy will be able to pick the car
     */
    @Test
    public void testGivenParkingBoyAndParkedCarWhenPickCarThenCanPickCar() throws ParkingLotException {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = parkingBoy.park(car);

        Car pickedCar = parkingBoy.pick(receipt);

        assertSame(car, pickedCar);
    }

    /**
     * Given parking boy has 1 parking lot and a car
     * When parking boy picks a car from the parking lot
     * Then parking boy will be failed to pick the car again
     */
    @Test
    public void testGivenParkingBoyAndCarWhenPickCarThenFailedToPickCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(CAR_NOT_FOUND.toString());

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = parkingBoy.park(car);

        parkingBoy.pick(receipt);
        parkingBoy.pick(receipt);
    }

    /**
     * Given parking boy has a parking lot 1 and a car parked in parking lot 1 and a fake receipt
     * When parking boy pick a car using the fake receipt
     * Then parking boy will be failed to pick the car
     */
    @Test
    public void testGivenParkingBoyAndParkingLot1AndParkedCarWhenParkingBoyPickCarFromParkingLot2ThenFailedToPickCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(PARKING_LOT_NOT_FOUND.toString());

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        parkingBoy.park(car);

        Receipt fakeReceipt = new Receipt("2", "A");
        parkingBoy.pick(fakeReceipt);
    }

    /**
     * Given parking boy has a full parking lot and an empty parking lot and a car
     * When parking boy picks a car A which is from parking lot 1
     * Then parking boy will be able to park the car to parking lot 1
     */
    @Test
    public void testGivenParkingBoyAndFullParkingLotAnd1EmptyParkingLotAndCarWhenPickCarFromParkingLot1ThenCanParkCarToParkingLot1() throws ParkingLotException {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        Receipt receiptA = parkingBoy.park(carA);

        parkingBoy.pick(receiptA);
        Receipt receiptB = parkingBoy.park(carB);

        assertEquals("B", receiptB.getCarLicense());
        assertEquals("1", receiptB.getParkingLotName());
    }
}
