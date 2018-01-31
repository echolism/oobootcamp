package oobootcamp.parkinglot;

public class ParkingBoy extends ParkingPerson {
    public ParkingBoy() {
        this("");
    }

    public ParkingBoy(String name) {
        super(name);
        this.parkingStrategy = new NormalParkingStrategy();
    }
}
