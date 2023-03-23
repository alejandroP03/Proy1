package Model.HotelDataHolder;

import java.io.File;
import java.util.Map;

import Model.HotelObjects.RoomRelated.RoomFare;

public class FaresDataHandler extends HotelDataHolder<RoomFare> {

    FaresDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void objectCreator() {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<String, RoomFare> loadPersistentData() {
        // TODO Auto-generated method stub
        return null;
    }
}