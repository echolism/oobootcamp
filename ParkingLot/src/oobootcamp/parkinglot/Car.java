package oobootcamp.parkinglot;

public class Car {
    private final String license;
    private boolean isParked;

    public Car(String license) {
        this.license = license;
        this.isParked = false;
    }

    public void setParked(boolean isParked) {
        this.isParked = isParked;
    }

    public boolean isParked() {
        return isParked;
    }
}
