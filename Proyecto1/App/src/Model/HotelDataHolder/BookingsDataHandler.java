package Model.HotelDataHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import Model.HotelObjects.Booking;

public class BookingsDataHandler extends HotelDataHolder<Booking> {

    public BookingsDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void loadPersistentData() throws FileNotFoundException, IOException, ParseException {
        // TODO Auto-generated method stub
    }
}