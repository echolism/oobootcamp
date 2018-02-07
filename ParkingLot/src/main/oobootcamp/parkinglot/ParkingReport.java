package oobootcamp.parkinglot;

public class ParkingReport {

    private final String role;
    private final int numberOfParkedCars;
    private final int capacity;

    public ParkingReport(String role) {
        this(role, 0, 0);
    }

    public ParkingReport(String role, int numberOfParkedCars, int capacity) {
        this.role = role;
        this.numberOfParkedCars = numberOfParkedCars;
        this.capacity = capacity;
    }

    public String getContent() {
        return "M 0 0";
    }
}
