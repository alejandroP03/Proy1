package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Controller.RegisterHandler.CompanionGuest;
import Controller.RegisterHandler.PrincipalGuest;
import Model.HotelObjects.Food;
import Model.HotelObjects.Registration;
import Model.HotelObjects.Service;

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
                for (JSONObject bookingEntry : objMap.values()) {

                    JSONObject principalGuestJson = (JSONObject) bookingEntry.get("principalGuest");

                    PrincipalGuest principalGuest = new PrincipalGuest(principalGuestJson.get("name").toString(),
                            principalGuestJson.get("dni").toString(), principalGuestJson.get("email").toString(),
                            principalGuestJson.get("phoneNumber").toString());

                    JSONArray groupOfGuestsJson = (JSONArray) bookingEntry.get("groupOfGuests");
                    ArrayList<CompanionGuest> groupOfGuests = new ArrayList<CompanionGuest>();
                    for (Object companionGuestJson : groupOfGuestsJson) {
                        CompanionGuest companionGuest = new CompanionGuest(
                                ((JSONObject) companionGuestJson).get("name").toString(),
                                ((JSONObject) companionGuestJson).get("dni").toString());
                        groupOfGuests.add(companionGuest);
                    }

                    JSONArray registerRoomsIdsJson = (JSONArray) bookingEntry.get("registerRooms");
                    ArrayList<String> registerRoomsIds = new ArrayList<String>();
                    for (Object roomId : registerRoomsIdsJson) {
                        registerRoomsIds.add(roomId.toString());
                    }
                    Registration newRegistration = new Registration(principalGuest, groupOfGuests, registerRoomsIds);

                    JSONArray consumedFoodsJson = (JSONArray) bookingEntry.get("consumedFoods");
                    for (Object food : consumedFoodsJson) {
                        Food newFood = new Food(((JSONObject) food).get("id").toString(),
                                ((JSONObject) food).get("name").toString(),
                                Double.parseDouble(((JSONObject) food).get("price").toString()),
                                Boolean.parseBoolean(((JSONObject) food).get("isRoomService").toString()),
                                ((JSONObject) food).get("availability").toString());
                        newRegistration.addConsumedFood(newFood);
                    }

                    JSONArray consumedServices = (JSONArray) bookingEntry.get("consumedServices");
                    for (Object service : consumedServices) {

                        ArrayList<DayOfWeek> daysAvailable = new ArrayList<DayOfWeek>();
                        for (Object day : (JSONArray) ((JSONObject) service).get("daysAvailable")) {
                            daysAvailable.add(DayOfWeek.valueOf(day.toString()));
                        }

                        Service newService = new Service(((JSONObject) service).get("id").toString(),
                                ((JSONObject) service).get("name").toString(),
                                Double.parseDouble(((JSONObject) service).get("price").toString()),
                                Boolean.parseBoolean(((JSONObject) service).get("isForGroup").toString()),
                                daysAvailable,
                                LocalTime.parse(((JSONObject) service).get("initialTime").toString()),
                                LocalTime.parse(((JSONObject) service).get("finalTime").toString()));

                        newRegistration.addConsumedService(newService);
                    }

                    super.getData().put(newRegistration.getPrincipalGuest().getDni(), newRegistration);
                }

            } else {
                throw new Exception("La lista de habitaciones contiene elementos");

            }
        } catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON ");
        }
    }
}