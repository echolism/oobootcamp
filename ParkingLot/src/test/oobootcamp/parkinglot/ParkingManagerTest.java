package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static oobootcamp.parkinglot.exception.ParkingLotException.Message.*;
import static org.junit.Assert.assertEquals;

public class ParkingManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Given parking manager has an empty parking lot and a car
     * When parking manager park a car
     * Then parking manager will be able to park a car and get the receipt
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarWhenParkCarThenCanParkCar() {
        ParkingPerson parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        Car car = new Car("A");

        Receipt receipt = parkingManager.park(car);

        assertEquals("A", receipt.getCarLicense());
    }

    /**
     * Given parking manager has an empty parking lot and a car and manage a parking boy Q
     * When parking manager ask parking boy Q to park a car
     * Then parking boy Q can park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWhenAskQToParkCarThenQCanParCar() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("Q", report.getParkingPersonName());
    }

    /**
     * Given parking manager has an empty parking lot and a car and manage a parking boy Q
     * When parking manager ask parking boy E to park a car
     * Then parking manager will be failed to find E to park the car
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWhenAskEToParkCarThenFailedToParkCar() {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(PARKING_BOY_NOT_FOUND.toString());

        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        Car car = new Car("A");

        parkingManager.parkByParkingBoy("E", car);
    }

    /**
     * Given parking manager has an empty parking lot and a car and employ parking boy Q and W
     * When parking manager ask parking boy Q to park a car
     * Then parking boy Q can park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWWhenAskQToParkCarThenQCanParCar() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("Q", report.getParkingPersonName());
    }

    /**
     * Given parking manager has an empty parking lot and a car and employ parking boy Q and W
     * When parking manager ask parking boy W to park a car
     * Then parking boy W can park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWWhenAskWToParkCarThenWCanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("W", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("W", report.getParkingPersonName());
    }

    /**
     * Given parking manager has an empty parking lot and a car but manage no parking boy
     * When parking manager ask parking boy Q to park a car
     * Then parking manager will be failed to find anyone to park the car
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarWhenAskQToParkCarThenFailedToParkCar() {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(PARKING_BOY_NOT_FOUND.toString());

        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        Car car = new Car("A");

        parkingManager.parkByParkingBoy("Q", car);
    }

    /**
     * Given parking manager has an empty parking lot and a car and employ parking boy Q, W and E
     * When parking manager ask parking boy E to park a car
     * Then parking boy E can park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWEWhenAskEToParkCarThenECanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        parkingManager.employ(parkingBoyFactory.train("E"));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("E", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("E", report.getParkingPersonName());
    }
}
