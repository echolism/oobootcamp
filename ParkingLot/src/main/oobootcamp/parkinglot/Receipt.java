package oobootcamp.parkinglot;

public class Receipt {
    private final String parkingLotName;
    private final String carLicense;
    private String parkingPersonName;

    public Receipt(String parkingLotName, String carLicense) {
        this.parkingLotName = parkingLotName;
        this.carLicense = carLicense;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public String getParkingPersonName() {
        return parkingPersonName;
    }

    public void setParkingPersonName(String parkingPersonName) {
        this.parkingPersonName = parkingPersonName;
    }
}
