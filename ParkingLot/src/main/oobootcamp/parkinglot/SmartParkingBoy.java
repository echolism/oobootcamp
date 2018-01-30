package oobootcamp.parkinglot;

public class SmartParkingBoy extends ParkingPerson {
    public SmartParkingBoy() {
        this.parkingStrategy = new SmartParkingStrategy();
    }
}
