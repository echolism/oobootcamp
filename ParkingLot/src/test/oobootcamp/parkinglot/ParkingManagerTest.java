package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static oobootcamp.parkinglot.exception.ParkingLotException.Message.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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
     * Given parking manager has an empty parking lot and a car and employs parking boy Q
     * When parking manager ask parking boy Q to park a car
     * Then parking boy Q will be able to park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWhenAskQToParkCarThenQCanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.delegate("Q", new ParkingLot("1", 1));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("Q", report.getParkingPersonName());
    }

    /**
     * Given parking manager has a car and employs parking boy Q
     * When parking manager ask parking boy Q to park a car
     * Then parking boy Q will not be able to park a car
     */
    @Test
    public void testGivenParkingManagerAndCarEmployParkingBoyQWhenAskQToParkCarThenFailedToParkCar() {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(PARKING_BOY_HAS_NO_PARKING_LOT.toString());

        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        Car car = new Car("A");

        parkingManager.parkByParkingBoy("Q", car);
    }

    /**
     * Given parking manager has an empty parking lot and a car and employs parking boy Q
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
     * Given parking manager has an empty parking lot and a car and employs parking boy Q and W
     * When parking manager ask parking boy Q to park a car
     * Then parking boy Q will be able to park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWWhenAskQToParkCarThenQCanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        parkingManager.delegate("Q", new ParkingLot("1", 1));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("Q", report.getParkingPersonName());
    }

    /**
     * Given parking manager has an empty parking lot and a car and employs parking boy Q and W
     * When parking manager ask parking boy W to park a car
     * Then parking boy W will be able to park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWWhenAskWToParkCarThenWCanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        parkingManager.delegate("W", new ParkingLot("1", 1));
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
     * Given parking manager has an empty parking lot and a car and employs parking boy Q, W and E
     * When parking manager ask parking boy E to park a car
     * Then parking boy E can park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmployParkingBoyQWEWhenAskEToParkCarThenECanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        parkingManager.employ(parkingBoyFactory.train("E"));
        parkingManager.delegate("E", new ParkingLot("1", 1));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("E", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("E", report.getParkingPersonName());
    }

    /**
     * Given parking manager has two empty parking lot and a car and employs parking boy Q
     * When parking manager ask parking boy Q to park a car
     * Then parking boy Q will be able to park a car in 1st parking lot and parking manager will be able to get the report
     */
    @Test
    public void testGivenParkingManagerWith2EmptyParkingLotAndCarEmployParkingBoyQWhenAskQToParkCarThenQCanParkTo1stParkingLot() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot("1", 1), new ParkingLot("2", 2));
        parkingManager.delegate("Q", parkingLots);
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("1", report.getParkingLotName());
        assertEquals("Q", report.getParkingPersonName());
    }
    
    /**
     * Given parking manager has an empty parking lot and a car and employs smart parking boy Q
     * When parking manager ask smart parking boy Q to park a car
     * Then smart parking boy Q will be able to park a car and parking manager will be able get the report
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarEmploySmartParkingBoyQWhenAskQToParkCarThenQCanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory smartParkingBoyFactory = new SmartParkingBoyFactory();
        parkingManager.employ(smartParkingBoyFactory.train("Q"));
        parkingManager.delegate("Q", new ParkingLot("1", 1));
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("Q", report.getParkingPersonName());
    }

    /**
     * Given parking manager has 3 parking lots which available slots are 1, 20, 1 and a car and employs parking boy Q
     * When parking manager ask parking boy Q to park a car
     * Then parking boy Q will be able to park a car in 2nd parking lot and parking manager will be able to get the report
     */
    @Test
    public void testGivenParkingManagerWith3ParkingLotWith2ndHasMoreAvailableSlotsAndCarEmploySmartParkingBoyQWhenAskQToParkCarThenQCanParkTo2ndParkingLot() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory smartParkingBoyFactory = new SmartParkingBoyFactory();
        parkingManager.employ(smartParkingBoyFactory.train("Q"));
        List<ParkingLot> parkingLots = Arrays.asList(new ParkingLot("1", 1), new ParkingLot("2", 20), new ParkingLot("3", 1));
        parkingManager.delegate("Q", parkingLots);
        Car car = new Car("A");

        Report report = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", report.getCarLicense());
        assertEquals("2", report.getParkingLotName());
        assertEquals("Q", report.getParkingPersonName());
    }

    /**
     * Given parking manager has 1 parking lot with a parked car
     * When parking manager picks a car from the parking lot
     * Then parking manager will be able to pick the car
     */
    @Test
    public void testGivenParkingManagerAndParkedCarWhenPickCarThenCanPickCar() throws ParkingLotException {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = parkingManager.park(car);

        Car pickedCar = parkingManager.pick(receipt);

        assertSame(car, pickedCar);
    }

    /**
     * Given parking manager has 1 parking lot with a parked car managed by parking boy Q
     * When parking manager picks a car according to the receipt
     * Then parking manager will be able to pick the car
     */
    @Test
    public void testGivenParkingManagerAndParkedCarAndEmployParkingByQWhenPickCarThenCanPickCar() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.delegate("Q", new ParkingLot("1", 1));
        Car car = new Car("A");
        Report report = parkingManager.parkByParkingBoy("Q", car);

        Car pickedCar = parkingManager.pick(report.getReceipt());

        assertSame(car, pickedCar);
    }

    /**
     * Given parking manager manages parking lot 1 and has parking lot 2 managed by parking boy Q and a car parked in parking lot 1
     * When parking manager picks a car according to the receipt
     * Then parking manager will be able to pick the car
     */
    @Test
    public void testGivenParkingManagerManageParkingLot1AndCarAndParkingBoyManageParkingLot2WhenPickCarThenCanPickCar() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.delegate("Q", new ParkingLot("2", 1));
        Car car = new Car("A");
        Receipt receipt = parkingManager.park(car);

        Car pickedCar = parkingManager.pick(receipt);

        assertSame(car, pickedCar);
    }

    /**
     * Given parking manager has parking lot 1 managed by parking boy Q and parking lot 2 managed by parking boy W with a car parked in it
     * When parking manager picks a car according to the receipt
     * Then parking manager will be able to pick the car
     */
    @Test
    public void testGivenParkingManagerEmployParkingBoyQWithParkingLot1AndParkingBoyWWithParkingLot2AndCarInParkingLot2WhenPickCarThenCanPickCar() {
        ParkingManager parkingManager = new ParkingManager();
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        parkingManager.delegate("Q", new ParkingLot("1", 1));
        parkingManager.delegate("W", new ParkingLot("2", 1));
        Car car = new Car("A");
        Report report = parkingManager.parkByParkingBoy("W", car);

        Car pickedCar = parkingManager.pick(report.getReceipt());

        assertSame(car, pickedCar);
    }

    /**
     * Given parking manager manages parking lot 1 has parking lot 2 managed by parking boy Q and parking lot 3 managed by parking boy W with a car A parked in it
     * When parking manager picks a car B according to the receipt
     * Then parking manager will be failed to pick the car
     */
    @Test
    public void testGivenParkingManagerManageParkingLot1EmployParkingBoyQWithParkingLot2AndParkingBoyWWithParkingLot3AndCarAInParkingLot3WhenPickCarBThenFailedPickCar() {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(CAR_NOT_FOUND.toString());

        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPersonFactory parkingBoyFactory = new ParkingBoyFactory();
        parkingManager.employ(parkingBoyFactory.train("Q"));
        parkingManager.employ(parkingBoyFactory.train("W"));
        parkingManager.delegate("Q", new ParkingLot("2", 1));
        parkingManager.delegate("W", new ParkingLot("3", 1));
        Car car = new Car("A");
        parkingManager.parkByParkingBoy("W", car);

        Receipt fakeReceipt = new Receipt("3", "B");
        parkingManager.pick(fakeReceipt);
    }
}
