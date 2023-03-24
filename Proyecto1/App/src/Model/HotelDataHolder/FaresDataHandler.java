package Model.HotelDataHolder;

import java.io.File;
import java.util.Map;

import Model.HotelObjects.RoomRelated.RoomFares;

public class FaresDataHandler extends HotelDataHolder<RoomFares> {

    FaresDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void objectCreator() {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<String, RoomFares> loadPersistentData() {
        // TODO Auto-generated method stub
        return null;
    }
}