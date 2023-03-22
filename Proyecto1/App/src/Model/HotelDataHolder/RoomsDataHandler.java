package Model.HotelDataHolder;

import java.io.File;

import Model.HotelObjects.Room;

public class RoomsDataHandler extends HotelDataHolder<Room> {

    RoomsDataHandler(File roomsJSONFile) {
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