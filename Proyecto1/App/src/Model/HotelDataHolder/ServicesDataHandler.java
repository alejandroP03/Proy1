package Model.HotelDataHolder;

import java.io.File;

import Model.HotelObjects.Service;

public class ServicesDataHandler extends HotelDataHolder<Service> {

    ServicesDataHandler(File roomsJSONFile) {
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