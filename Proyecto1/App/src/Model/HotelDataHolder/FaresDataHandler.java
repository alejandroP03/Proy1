package Model.HotelDataHolder;

import java.io.File;

import Model.HotelObjects.RoomFare;

public class FaresDataHandler extends HotelDataHolder<RoomFare> {

    FaresDataHandler(File roomsJSONFile) {
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