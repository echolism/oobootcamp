package oobootcamp.parkinglot;

import oobootcamp.parkinglot.Car;
import oobootcamp.parkinglot.ParkingBoy;
import oobootcamp.parkinglot.ParkingLot;
import oobootcamp.parkinglot.Receipt;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingBoyTest {
    /**
     * Given parking boy has an empty parking lot and a car
     * When parking boy parks a car to the parking lot
     * Then parking boy will be able to park a car and get receipt
     */
    @Test
    public void testGivenParkingBoyWithEmptyParkingLotAndCarWhenParkCarThenCanParkCar() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");

        Receipt receipt = parkingBoy.park(car);

        assertTrue(car.isParked());
        assertEquals("A", receipt.getCarLicense());
    }

    /**
     * Given parking boy has a full parking lot and a car
     * When parking boy parks a car to the parking lot
     * Then parking boy will be failed to park a car
     */
    @Test
    public void testGivenParkingBoyWithFullParkingLotAndCarWhenParkCarThenFailedToParkCar() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingBoy.park(carA);

        Receipt receipt = parkingBoy.park(carB);

        assertFalse(carB.isParked());
        assertNull(receipt);
    }

    /**
     * Given parking boy has 2 empty parking lots and a car
     * When parking boy parks a car
     * Then parking boy will be able to park a car in 1st empty parking lot
     */
    @Test
    public void testGivenParkingBoyWith2EmptyParkingLotWhenParkCarThenCanParkCarTo1stParkingLot() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car car = new Car("A");

        Receipt receipt = parkingBoy.park(car);

        assertTrue(car.isParked());
        assertEquals("A", receipt.getCarLicense());
        assertEquals("1", receipt.getParkingLotName());
    }

    /**
     * Given parking boy has a full parking lot and an empty parking lot and a car
     * When parking boy parks a car
     * Then parking boy will be able to park a car in the 2nd parking lot
     */
    @Test
    public void testGivenParkingBoyWith1FullParkingLotAnd1EmptyParkingLotAndCarWhenParkCarThenCanParkCarToEmptyParkingLot() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        parkingBoy.park(carA);

        Receipt receipt = parkingBoy.park(carB);

        assertTrue(carB.isParked());
        assertEquals("B", receipt.getCarLicense());
        assertEquals("2", receipt.getParkingLotName());
    }

    /**
     * Given parking boy has 2 full parking lot and a car
     * When parking boy parks a car
     * Then parking boy will be failed to park a car
     */
    @Test
    public void testGivenParkingBoyWith2FullParkingLotAndCarWhenParkCarThenFailedToParkCar() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        Car carC = new Car("C");
        parkingBoy.park(carA);
        parkingBoy.park(carB);

        Receipt receipt = parkingBoy.park(carC);

        assertFalse(carC.isParked());
        assertNull(receipt);
    }

    /**
     * Given parking boy has 1 parking lot with a parked car
     * When parking boy picks a car from the parking lot
     * Then parking boy will be able to pick the car
     */
    @Test
    public void testGivenParkingBoyAndParkedCarWhenPickCarThenCanPickCar() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = parkingBoy.park(car);

        Car pickedCar = parkingBoy.pick(receipt);

        assertSame(car, pickedCar);
        assertFalse(pickedCar.isParked());
    }

    /**
     * Given parking boy has 1 parking lot and a car
     * When parking boy picks a car from the parking lot
     * Then parking boy will be failed to pick the car again
     */
    @Test
    public void testGivenParkingBoyAndCarWhenPickCarThenFailedToPickCar() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = parkingBoy.park(car);

        parkingBoy.pick(receipt);
        Car pickedCar = parkingBoy.pick(receipt);

        assertNull(pickedCar);
    }

    /**
     * Given parking boy has a full parking lot and an empty parking lot and a car
     * When parking boy picks a car A which is from parking lot 1
     * Then parking boy will be able to park the car to parking lot 1
     */
    @Test
    public void testGivenParkingBoyAndFullParkingLotAnd1EmptyParkingLotAndCarWhenPickCarFromParkingLot1ThenCanParkCarToParkingLot1() {
        ParkingBoy parkingBoy = new ParkingBoy("Boy");
        parkingBoy.manage(new ParkingLot("1", 1));
        parkingBoy.manage(new ParkingLot("2", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        Receipt receiptA = parkingBoy.park(carA);

        parkingBoy.pick(receiptA);
        Receipt receiptB = parkingBoy.park(carB);

        assertTrue(carB.isParked());
        assertEquals("B", receiptB.getCarLicense());
        assertEquals("1", receiptB.getParkingLotName());
    }
}
