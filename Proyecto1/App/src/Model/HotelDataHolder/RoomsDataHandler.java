package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.Room;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;

public class RoomsDataHandler extends HotelDataHolder<Room> {

    RoomsDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    public void createNewRoom(
            String location,
            int capacity,
            ArrayList<Bed> beds,
            ArrayList<RoomFeatures> featuresList,
            TypeRoom type) throws Exception {
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
            Map<String, Room> roomsList = super.getData();
            String roomId = getRoomId(type, roomsList);

            Room newRoom = new Room(roomId, location, capacity, false, beds, featuresList, type);

            roomsList.put(roomId, newRoom);
        } else {
            throw new Exception("El archivo debe cargarse antes de crear un nuevo objeto");
        }
    }

    private String getRoomId(TypeRoom type, Map<String, Room> roomsList) {
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
                    ArrayList<Object> bedsArray = (JSONArray) roomEntry.getValue().get("beds");
                    ArrayList<Bed> beds = new ArrayList<Bed>();
                    for (Object object : bedsArray) {
                        beds.add((Bed) object);
                    }

                    @SuppressWarnings("unchecked")
                    ArrayList<Object> featuresArray = (JSONArray) roomEntry.getValue().get("beds");
                    ArrayList<RoomFeatures> features = new ArrayList<RoomFeatures>();
                    for (Object object : featuresArray) {
                        features.add((RoomFeatures) object);
                    }

                    Map<String, Room> roomList = super.getData();
                    Room newRoom = new Room(roomId, location, isOccupied, beds, features, type);

                    @SuppressWarnings("unchecked")
                    ArrayList<Object> bookedDaysArray = (JSONArray) roomEntry.getValue().get("beds");
                    ArrayList<RoomFeatures> bookedDays = new ArrayList<>();
                    for (Object object : featuresArray) {
                        features.add((RoomFeatures) object);
                    }
                    
                    
                    // Despues de creada la habitación se buscan las tarifas relacionadas

                    roomList.put(roomId, newRoom);

                    // private ArrayList<LocalDate> bookedDates;
                    // private ArrayList<RoomFare> roomFares;
                }

                super.setFileLoaded(!super.getIsFileLoaded());
            } else {
                throw new Exception("La lista de habitaciones contiene elementos");
            }

        } catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON");
        }
    }

    public static void main(String[] args) throws Exception {
        RoomsDataHandler rh = new RoomsDataHandler(new File("App/data/rooms.json"));
        rh.loadPersistentData();

    }
}