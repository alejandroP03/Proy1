package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Controller.RegisterHandler.CompanionGuest;
import Controller.RegisterHandler.PrincipalGuest;
import Model.HotelObjects.Registration;

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

                    JSONArray groupOfGuestsJson = (JSONArray) bookingEntry.get("group");
                    ArrayList<CompanionGuest> groupOfGuests = new ArrayList<CompanionGuest>();
                    for (Object companionGuestJson : groupOfGuestsJson) {
                        CompanionGuest companionGuest = new CompanionGuest(
                                ((JSONObject) companionGuestJson).get("name").toString(),
                                ((JSONObject) companionGuestJson).get("dni").toString());
                        groupOfGuests.add(companionGuest);
                    }

                    JSONArray registerRoomsIdsJson = (JSONArray) bookingEntry.get("registerRoomsIds");
                    ArrayList<String> registerRoomsIds = new ArrayList<String>();
                    for (Object roomId : registerRoomsIdsJson) {
                        registerRoomsIds.add(roomId.toString());
                    }
                    LocalDate initialDate = LocalDate.parse(((String) bookingEntry.get("initialDate")));
                    LocalDate finalDate = LocalDate.parse(((String) bookingEntry.get("finalDate")));

                    Registration newRegistration = new Registration(principalGuest, groupOfGuests, registerRoomsIds, initialDate, finalDate);


                    JSONArray consumedFoodsJson = (JSONArray) bookingEntry.get("consumedFoodsIds");
                    for (Object foodId : consumedFoodsJson) {
                        newRegistration.addConsumedFood(foodId.toString());
                    }

                    JSONArray consumedServicesIds = (JSONArray) bookingEntry.get("consumedServicesIds");
                    for (Object serviceId : consumedServicesIds) {
                        newRegistration.addConsumedServiceId(serviceId.toString());
                    }

                    super.getData().put(newRegistration.getPrincipalGuest().getDni(), newRegistration);
                }

            } else {
                throw new Exception("La lista de habitaciones contiene elementos");

            }
        } catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON " + e);
        }
    }
}