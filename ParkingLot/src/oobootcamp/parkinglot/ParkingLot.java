package oobootcamp.parkinglot;

public class ParkingLot {
    private boolean isFull;

    public Receipt park(Car car) {
        if (isFull) {
            return null;
        }
        Receipt receipt = new Receipt(car.getName());
        return receipt;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }
}
