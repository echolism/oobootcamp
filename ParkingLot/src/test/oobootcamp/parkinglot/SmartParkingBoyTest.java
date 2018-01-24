package oobootcamp.parkinglot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class SmartParkingBoyTest { // Composition over inheritance -> strategy design pattern?
    /**
     * Given smart parking boy has an empty parking lot and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car
     */
    @Test
    public void testGivenSmartParkingBoyWithEmptyParkingLotAndCarWhenParkCarThenCanParkCar() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
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
    public void testGivenSmartParkingBoyWithFullParkingLotAndCarWhenParkCarThenFailedToParkCar() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        Car carA = new Car("A");
        Car carB = new Car("B");
        smartParkingBoy.park(carA);

        Receipt receipt = smartParkingBoy.park(carB);

        assertNull(receipt);
    }

    /**
     * Given smart parking boy has 1 parking lot with a parked car
     * When smart parking boy picks a car from the parking lot
     * Then smart parking boy will be able to pick the car
     */
    @Test
    public void testGivenSmartParkingBoyAndParkedCarWhenPickCarThenCanPickCar() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
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
    public void testGivenSmartParkingBoyAndCarWhenPickCarThenFailedToPickCar() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1", 1));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        smartParkingBoy.pick(receipt);
        Car pickedCar = smartParkingBoy.pick(receipt);

        assertNull(pickedCar);
    }

    /**
     * Given smart parking boy has 2 parking lots with same available slots and a car
     * When smart parking boy park a car
     * Then smart parking boy will be able to park a car to 1st parking lot
     */
    @Test
    public void testGivenSmartParkingBoyWith2SameAvailableSlotsParkingLotAndCarWhenParkCarThenCanParkTo1stParkingLot() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1",1));
        smartParkingBoy.manage(new ParkingLot("2",1));
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
    public void testGivenSmartParkingBoyWith2SlotsParkingLotWith1stHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1",200));
        smartParkingBoy.manage(new ParkingLot("2",1));
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
    public void testGivenSmartParkingBoyWith2SlotsParkingLotWith2ndHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1",1));
        smartParkingBoy.manage(new ParkingLot("2",200));
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
    public void testGivenSmartParkingBoyWith3SlotsParkingLotWith2ndHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1",1));
        smartParkingBoy.manage(new ParkingLot("2",20));
        smartParkingBoy.manage(new ParkingLot("3",1));
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
    public void testGivenSmartParkingBoyWith3SlotsParkingLotWith3rdHasMoreAvailableSlotsAndCarWhenParkCarThenCanParkTo1stParkingLot() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manage(new ParkingLot("1",1));
        smartParkingBoy.manage(new ParkingLot("2",1));
        smartParkingBoy.manage(new ParkingLot("3",20));
        Car car = new Car("A");
        Receipt receipt = smartParkingBoy.park(car);

        assertEquals("3", receipt.getParkingLotName());
    }
}
