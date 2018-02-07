package oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingDirector {
    private List<ParkingManager> parkingManagers;
// TODO: observer / visitor pattern
    public ParkingDirector() {
        this.parkingManagers = new ArrayList<>();
    }

    public void employ(ParkingManager manager) {
        parkingManagers.add(manager);
    }

    public ParkingReport showReport() {
        ParkingReport report = parkingManagers.get(0).getParkingReport();
        return report;
    }
}
