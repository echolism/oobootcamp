package oobootcamp.parkinglot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingManagerTest {
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
     * Given parking manager has an empty parking lot and a car and a parking boy
     * When parking manager ask parking boy to park a car
     * Then parking boy can park a car and parking manager will be able get the receipt
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarAndParkingBoyWhenParkCarThenCanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        ParkingPerson parkingBoy = new ParkingBoy();
        Car car = new Car("A");

        Receipt receipt = parkingManager.parkByParkingBoy(parkingBoy, car);

        assertEquals("A", receipt.getCarLicense());
    }

    /**
     * Given parking manager has an empty parking lot and a car and manage a parking boy
     * When parking manager ask his own parking boy to park a car
     * Then parking boy Q can park a car and parking manager will be able get the receipt
     */
    @Test
    public void testGivenParkingManagerWithEmptyParkingLotAndCarManageParkingBoyWhenParkCarThenCanParkCar() {
        ParkingManager parkingManager = new ParkingManager();
        parkingManager.manage(new ParkingLot("1", 1));
        parkingManager.manageParkingBoy(new ParkingBoy("Q"));
        Car car = new Car("A");

        Receipt receipt = parkingManager.parkByParkingBoy("Q", car);

        assertEquals("A", receipt.getCarLicense());
        assertEquals("Q", receipt.getParkingPersonName());
    }

    /**
     * Given parking manager has an empty parking lot and a car and manage parking boy Q and W
     * When parking manager ask parking boy W to park a car
     * Then parking boy W can park a car and parking manager will be able get the receipt
     */
}
