package oobootcamp.parkinglot;

public class ParkingBoyFactory implements ParkingPersonFactory {
    @Override
    public ParkingPerson train(String name) {
        return new ParkingBoy(name);
    }
}
