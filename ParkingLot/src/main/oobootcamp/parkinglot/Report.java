package oobootcamp.parkinglot;

public class Report {
    private final Receipt receipt;
    private final String parkingPersonName;

    public Report(String parkingBoyName, Receipt receipt) {
        this.parkingPersonName = parkingBoyName;
        this.receipt = receipt;
    }

    public String getCarLicense() {
        return receipt.getCarLicense();
    }

    public String getParkingPersonName() {
        return parkingPersonName;
    }

    public String getParkingLotName() {
        return receipt.getParkingLotName();
    }
}
