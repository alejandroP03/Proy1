package Model.HotelDataHolder;

import java.io.File;

import Model.HotelObjects.Booking;

public class BookingsDataHandler extends HotelDataHolder<Booking> {

    BookingsDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void objectCreator() {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadPersistentData(File dataFile) {
        // TODO Auto-generated method stub

    }
}