package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.Fare;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFares;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;

public class RoomsDataHandler extends HotelDataHolder<Room> {
    Map<Object, RoomFares> roomFaresMap;

    public RoomsDataHandler(File roomsJSONFile, Map<Object, RoomFares> roomFaresMap) {
        super(roomsJSONFile);
        this.roomFaresMap = roomFaresMap;
    }

    public void createNewRoom(String location,
            boolean isOccupied,
            Map<Bed, Integer> beds,
            Set<RoomFeatures> featuresList,
            TypeRoom type)
            throws Exception {
        /*
         * Crea una nueva habitación y la ingresa en la estructura que guarda las
         * habitaciones
         * 
         * <b> pre: </b> isFileLoaded == True
         * <b> post: </b> La estructura va a tener un nuevo objeto Room<br>
         * 
         * @throws Exception <br>
         * El archivo debe cargarse antes de crear una nueva habitación
         */

        if (super.getIsFileLoaded()) {
            Map<Object, Room> roomsList = super.getData();
            String roomId = getRoomId(type, roomsList);
            Room newRoom = new Room(roomId, location, isOccupied, beds, featuresList, type);

            RoomFares newRoomFares = this.roomFaresMap.get(newRoom.createTypeRoomId());
            if (newRoomFares != null) {
                ArrayList<Fare> listOfFares = newRoomFares.getFaresForRoomType();
                newRoom.setRoomFares(listOfFares);
            }

            roomsList.put(roomId, newRoom);
        } else {
            throw new Exception("El archivo debe cargarse antes de crear un nuevo objeto");
        }

    }

    private String getRoomId(TypeRoom type, Map<Object, Room> roomsList) {
        return type.name() + '_' + (roomsList.size() + 1);
    }

    @Override
    public void loadPersistentData() throws Exception {
        /*
         * Carga la información del archivo en la estructura
         * 
         * <b> pre: </b> <br>
         * El archivo debe estar en formato JSON <br>
         * La estructura debe estar vacia<br>
         * La lista de tarifas ya debe estar inicializada
         * 
         * <b> post: </b>
         * En el atributo dataHandler va a estar la información del archivo
         * 
         * @throws Exception <br>
         * El archivo está vacío (No tiene ni siquiera la estructura {} creada)
         * 
         * @throws Exception <br>
         * La estructura contiene elementos
         * 
         */

        JSONParser pJsonParser = new JSONParser();
        try {
            Object jsonObjToFile = pJsonParser.parse(new FileReader(super.getjSONDataFile()));

            JSONObject obj = (JSONObject) jsonObjToFile;

            @SuppressWarnings("unchecked")
            Map<String, JSONObject> objMap = (Map<String, JSONObject>) obj;

            if (super.getData().isEmpty()) {
                for (Map.Entry<String, JSONObject> roomEntry : objMap.entrySet()) {
                    String roomId = (String) roomEntry.getValue().get("roomId");
                    String location = (String) roomEntry.getValue().get("location");
                    boolean isOccupied = (boolean) roomEntry.getValue().get("isOccupied");
                    TypeRoom type = (TypeRoom) roomEntry.getValue().get("type");

                    @SuppressWarnings("unchecked")
                    Map<String, Integer> bedsMap = (JSONObject) roomEntry.getValue().get("beds");

                    Map<Bed, Integer> beds = new HashMap<Bed, Integer>();
                    for (Map.Entry<String, Integer> bed : bedsMap.entrySet()) {
                        beds.put(Bed.valueOf(bed.getKey()), Integer.parseInt(String.valueOf(bed.getValue())));
                    }

                    @SuppressWarnings("unchecked")
                    ArrayList<String> featuresArray = (JSONArray) roomEntry.getValue().get("featuresList");

                    Set<RoomFeatures> features = new HashSet<RoomFeatures>();
                    for (String featureName : featuresArray) {
                        features.add(RoomFeatures.valueOf(featureName));
                    }

                    Map<Object, Room> roomList = super.getData();
                    Room newRoom = new Room(roomId, location, isOccupied, beds, features, type);

                    @SuppressWarnings("unchecked")
                    Map<String, String> bookedDaysMap = (JSONObject) roomEntry.getValue().get("bookedDates");
                    HashMap<LocalDate, LocalDate> bookedDays = new HashMap<LocalDate, LocalDate>();
                    for (Map.Entry<String, String> bookedDay : bookedDaysMap.entrySet()) {
                        LocalDate initialDate = LocalDate.parse(bookedDay.getKey());
                        LocalDate finalDate = LocalDate.parse(bookedDay.getValue());
                        bookedDays.put(initialDate, finalDate);
                    }
                    newRoom.setBookedDates(bookedDays);
                    // Despues de creada la habitación se buscan las tarifas relacionadas

                    RoomFares newRoomFares = this.roomFaresMap.get(newRoom.createTypeRoomId());
                    if (newRoomFares != null) {
                        ArrayList<Fare> listOfFares = newRoomFares.getFaresForRoomType();
                        newRoom.setRoomFares(listOfFares);
                    }

                    roomList.put(roomId, newRoom);
                }

                // Esto es lo que confirma que el archivo ya fue cargado y ya se van a poder
                // crear objetos
                super.setFileLoaded(true);
            } else {
                throw new Exception("La lista de habitaciones contiene elementos");
            }

        } catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON ");
        }
    }

}