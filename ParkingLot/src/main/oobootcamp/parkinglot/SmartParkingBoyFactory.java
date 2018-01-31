package oobootcamp.parkinglot;

public class SmartParkingBoyFactory implements ParkingPersonFactory {
    @Override
    public ParkingPerson train(String name) {
        return new SmartParkingBoy(name);
    }
}
