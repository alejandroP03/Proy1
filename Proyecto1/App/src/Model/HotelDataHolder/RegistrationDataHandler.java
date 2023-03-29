package Model.HotelDataHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Controller.RegisterHandler.Group;
import Controller.RegisterHandler.PrincipalGuest;
import Model.HotelObjects.Registration;
import Model.HotelObjects.RoomRelated.Room;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegistrationDataHandler extends HotelDataHolder<Registration> {

    public RegistrationDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void loadPersistentData() throws Exception {
        JSONParser pJsonParser = new JSONParser();
        try {
            Object jsonObjToFile = pJsonParser.parse(new FileReader(super.getjSONDataFile()));

            JSONObject obj = (JSONObject) jsonObjToFile;

            @SuppressWarnings("unchecked")
            Map<String, JSONObject> objMap = (Map<String, JSONObject>) obj;

            if (super.getData().isEmpty()) {
                Map<String, Room> registerRoomsMap = null;
                Group groupOfGuests = null;
                PrincipalGuest principalGuest = null;
                for (Map.Entry<String, JSONObject> bookingEntry : objMap.entrySet()) {
                    principalGuest = (PrincipalGuest) bookingEntry.getValue().get("principalGuest");
                    groupOfGuests = (Group) bookingEntry.getValue().get("groupOfGuests");
                    JSONArray registerRooms = (JSONArray) bookingEntry.getValue().get("registerRooms");
                    JSONArray consumedFoods = (JSONArray) bookingEntry.getValue().get("consumedFoods");
                    JSONArray consumedServices = (JSONArray) bookingEntry.getValue().get("consumedServices");

                    registerRoomsMap = new HashMap<>();
                    RoomsDataHandler roomsHandler = new RoomsDataHandler(new File("App/data/rooms.json"));

                    for (Object roomId : registerRooms) {
                        registerRoomsMap.put((String) roomId, roomsHandler.getData().get((String) roomId));
                    }
                }
                Registration newRegistration = new Registration(principalGuest, groupOfGuests, registerRoomsMap);
                super.getData().put(principalGuest, newRegistration);



            }else{
                throw new Exception("La lista de habitaciones contiene elementos");

            }
        }catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON ");
        }
    }
}