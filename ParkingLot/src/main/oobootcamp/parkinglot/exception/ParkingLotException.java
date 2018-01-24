package oobootcamp.parkinglot.exception;

public class ParkingLotException extends Exception{
    public enum Message {
        CAR_NOT_FOUND,
        CAR_IN_PARKING_LOT,
        NO_AVAILABLE_SPACE,
        PARKING_LOT_NOT_FOUND;

        @Override
        public String toString() {
            return this.equals(CAR_NOT_FOUND) ? "Car not found"
                    : this.equals(CAR_IN_PARKING_LOT) ? "Car is already in parking lot"
                    : this.equals(NO_AVAILABLE_SPACE) ? "No available space"
                    : this.equals(PARKING_LOT_NOT_FOUND) ? "Parking lot not found" : "";
        }
    }

    public ParkingLotException(String message) {
        super(message);
    }
}
