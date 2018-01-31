package oobootcamp.parkinglot;

public class SmartParkingBoy extends ParkingPerson {
    public SmartParkingBoy() {
        this("");
    }

    public SmartParkingBoy(String name) {
        super(name);
        this.parkingStrategy = new SmartParkingStrategy();
    }
}
