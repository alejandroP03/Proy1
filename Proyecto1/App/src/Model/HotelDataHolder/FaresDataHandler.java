package Model.HotelDataHolder;

import java.io.File;

import Model.HotelObjects.RoomRelated.RoomFares;

public class FaresDataHandler extends HotelDataHolder<RoomFares> {


    FaresDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    public void FareCreator() {
        
    }

    @Override
    public void loadPersistentData() {
        // TODO Auto-generated method stub
        
    }
}