package Model.HotelDataHolder;

import java.io.File;

import Model.HotelObjects.Registration;

public class RegistrationDataHandler extends HotelDataHolder<Registration> {

    public RegistrationDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void loadPersistentData(File dataFile) {
        // TODO Auto-generated method stub
    }
}