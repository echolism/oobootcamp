package oobootcamp.parkinglot.exception;

public class ParkingLotException extends RuntimeException {
    public enum Message {
        CAR_NOT_FOUND("Car not found"),
        CAR_IN_PARKING_LOT("Car is already in parking lot"),
        NO_AVAILABLE_SPACE("No available space"),
        PARKING_LOT_NOT_FOUND("Parking lot not found"),
        PARKING_BOY_NOT_FOUND("Parking boy not found"),
        PARKING_BOY_HAS_NO_PARKING_LOT("Parking boy has no parking lot");

        private String msg;

        Message(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return msg;
        }
    }

    public ParkingLotException(String message) {
        super(message);
    }
}
