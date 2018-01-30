package oobootcamp.parkinglot;

public class ParkingBoy extends ParkingPerson {
    public ParkingBoy() {
        this.parkingStrategy = new NormalParkingStrategy();
    }
}
