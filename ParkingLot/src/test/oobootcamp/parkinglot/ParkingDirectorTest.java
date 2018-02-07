package oobootcamp.parkinglot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingDirectorTest {
    /**
     * Given a Parking Director has a Parking Manager and no Parking Lot and no Parking Boy
     * When Parking Director show parking report
     * Then M 0 0 will be shown
     */
    @Test
    public void testGivenParkingDirectorHasParkingManagerWithoutParkingLotNorParkingBoyWhenShowParkingReportThenM00() {
        ParkingDirector director = new ParkingDirector();
        ParkingManager manager = new ParkingManager();
        director.employ(manager);

        ParkingReport report = director.showReport();

        assertEquals("M 0 0", report.getContent());
    }

    /**
     * Given a Parking Direct has a Parking Manager who manage 1 empty Parking Lot (0, 1)
     * When Parking Director show parking report
     * Then report below will be shown
     *  M 0 1
     *      P 0 1
     */
    @Test
    public void testGivenParkingDirectorHasParkingManagerWith1EmptyParkingLotWith1AvailableSpaceWhenShowParkingReportThenM01P01() {
        ParkingDirector director = new ParkingDirector();
        ParkingManager manager = new ParkingManager();
        manager.manage(new ParkingLot("1", 1));
        director.employ(manager);

        ParkingReport report = director.showReport();

        assertEquals("M 0 1\n\tP 0 1", report.getContent());
    }
}
