package oobootcamp.parkinglot;

import oobootcamp.parkinglot.exception.ParkingLotException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SmartParkingBoyTest { // Composition over inheritance -> strategy design pattern?
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Given smart parking boy has an empty parking lot and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car
     */
    @Test
    public void testGivenSmartParkingBoyWithEmptyParkingLotAndCarWhenParkCarThenCanParkCar() throws ParkingLotException {
        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");

        Receipt receipt = smartParkingBoy.park(car);

        assertEquals("A", receipt.getCarLicense());
    }

    /**
     * Given smart parking boy has a full parking lot and a car
     * When smart parking boy parks a car to the parking lot
     * Then smart parking boy will be failed to park a car
     */
    @Test
    public void testGivenSmartParkingBoyWithFullParkingLotAndCarWhenParkCarThenFailedToParkCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(ParkingLotException.Message.NO_AVAILABLE_SPACE.toString());

        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        smartParkingBoy.park(carA);

        smartParkingBoy.park(carB);
    }

    /**
     * Given smart parking boy has 1 parking lot with a parked car
     * When smart parking boy picks a car from the parking lot
     * Then smart parking boy will be able to pick the car
     */
    @Test
    public void testGivenSmartParkingBoyAndParkedCarWhenPickCarThenCanPickCar() throws ParkingLotException {
        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        Car pickedCar = smartParkingBoy.pick(receipt);

        assertSame(car, pickedCar);
    }

    /**
     * Given smart parking boy has 1 parking lot and a car
     * When smart parking boy picks a car from the parking lot
     * Then smart parking boy will be failed to pick the car again
     */
    @Test
    public void testGivenSmartParkingBoyAndCarWhenPickCarThenFailedToPickCar() throws ParkingLotException {
        thrown.expect(ParkingLotException.class);
        thrown.expectMessage(ParkingLotException.Message.CAR_NOT_FOUND.toString());

        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        smartParkingBoy.pick(receipt);
        smartParkingBoy.pick(receipt);
    }

    /**
     * Given smart parking boy has 2 parking lots with same available slots and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car to 1st parking lot
     */
    @Test
    public void testGivenSmartParkingBoyWith2SameAvailableSlotsParkingLotAndCarWhenParkCarThenCanParkTo1stParkingLot() throws ParkingLotException {
        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        smartParkingBoy.manage(new ParkingLot("2", 1));
        Car car = new Car("A");

        Receipt receipt = smartParkingBoy.park(car);

        assertEquals("1", receipt.getParkingLotName());
    }

    /**
     * Given smart parking boy has 2 parking lots 1st parking lot has more available slots then 2nd and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car to 1st parking lot
     */
    @Test
    public void testGivenSmartParkingBoyWith2SlotsParkingLotWith1stHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() throws ParkingLotException {
        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 200));
        smartParkingBoy.manage(new ParkingLot("2", 1));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        assertEquals("1", receipt.getParkingLotName());
    }

    /**
     * Given smart parking boy has 2 parking lots 2nd parking lot has more available slots then 1st and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car to 2nd parking lot
     */
    @Test
    public void testGivenSmartParkingBoyWith2SlotsParkingLotWith2ndHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() throws ParkingLotException {
        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        smartParkingBoy.manage(new ParkingLot("2", 200));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        assertEquals("2", receipt.getParkingLotName());
    }

    /**
     * Given smart parking boy has 3 parking lots which available slots are 1, 20, 1 and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car to 2nd parking lot
     */
    @Test
    public void testGivenSmartParkingBoyWith3SlotsParkingLotWith2ndHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() throws ParkingLotException {
        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        smartParkingBoy.manage(new ParkingLot("2", 20));
        smartParkingBoy.manage(new ParkingLot("3", 1));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        assertEquals("2", receipt.getParkingLotName());
    }

    /**
     * Given smart parking boy has 3 parking lots which available slots are 1, 1, 20 and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car to 3rd parking lot
     */
    @Test
    public void testGivenSmartParkingBoyWith3SlotsParkingLotWith3rdHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() throws ParkingLotException {
        ParkingPerson smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        smartParkingBoy.manage(new ParkingLot("2", 1));
        smartParkingBoy.manage(new ParkingLot("3", 20));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        assertEquals("3", receipt.getParkingLotName());
    }
}
