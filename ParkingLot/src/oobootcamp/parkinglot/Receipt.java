package oobootcamp.parkinglot;

public class Receipt {
    private final String parkingLotName;
    private final String carLicense;

    public Receipt(String parkingLotName, String carLicense) {
        this.parkingLotName = parkingLotName;
        this.carLicense = carLicense;
    }

    public String getCarLicense() {
        return carLicense;
    }
}
