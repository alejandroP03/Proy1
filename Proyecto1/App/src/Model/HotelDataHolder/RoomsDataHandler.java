package Model.HotelDataHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    public void loadPersistentData() throws FileNotFoundException, IOException, ParseException {
        /*
         * Carga la información del archivo en la estructura
         * 
         * <b> pre: </b> <br>
         * El archivo debe estar en formato JSON <br>
         * La estructura debe estar vacia<br>
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
        
        Object jsonObjToFile = pJsonParser.parse(new FileReader(getjSONDataFile()));
        JSONObject obj = (JSONObject) jsonObjToFile;
        
        System.out.println(obj);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        RoomsDataHandler rh = new RoomsDataHandler(new File("App/data/rooms.json"));
        rh.loadPersistentData();
    }
}