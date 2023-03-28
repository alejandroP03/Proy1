package Model.HotelDataHolder;

import java.io.File;

import Model.HotelObjects.Registration;

public class RegistrationDataHandler extends HotelDataHolder<Registration> {

    RegistrationDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void loadPersistentData() throws Exception {

    }

//    @Override
//    public void objectCreator() {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void loadPersistentData(File dataFile) {
//        // TODO Auto-generated method stub
//
//    }
}