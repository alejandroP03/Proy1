package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.Fare;
import Model.HotelObjects.RoomRelated.RoomFares;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;

public class FaresDataHandler extends HotelDataHolder<RoomFares> {

    public FaresDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    public void FareCreator(Set<Object> typeRoomId, float price, LocalDate initialDate, LocalDate finalDate,
            ArrayList<DayOfWeek> daysList) throws Exception {
        /*
         * Crea una nueva tarifa y la ingresa en la estructura que guarda las
         * tarifas, si la estructura RoomFares ya tiene el id del tipo de habitación, solo la agrega,
         * si no, crea una nueva habitación
         * 
         * <b> pre: </b> isFileLoaded == True
         * 
         * <b> post: </b> La estructura va a tener un nuevo objeto Fare<br>
         * 
         * @throws Exception <br>
         * El archivo debe cargarse antes de crear una nueva habitación
         */
        
        if (super.getIsFileLoaded()) {
            RoomFares roomFareList;
            if (!super.getData().containsKey(typeRoomId)) {
                roomFareList = new RoomFares(typeRoomId);
            } else
                roomFareList = super.getData().get(typeRoomId);

            Fare newFare = new Fare(price, initialDate, finalDate, daysList);
            roomFareList.addFare(newFare);
            super.getData().put(typeRoomId, roomFareList);

        } else {
            throw new Exception("El archivo debe cargarse antes de crear un nuevo objeto");
        }
    }

    @Override
    public void loadPersistentData() {
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
            Map<Object, JSONObject> objMap = (Map<Object, JSONObject>) obj;

            if (super.getData().isEmpty()) {

                for (Map.Entry<Object, JSONObject> fareListEntry : objMap.entrySet()) {
                    System.out.println(fareListEntry.getKey());
                    JSONArray roomFareId = (JSONArray) pJsonParser.parse((String) fareListEntry.getKey());
                    System.out.println(roomFareId);
                    Set<Object> typeRoomId = new HashSet<Object>();

                    @SuppressWarnings("unchecked")
                    Iterator<String> iterator = roomFareId.iterator();

                    while (iterator.hasNext()) {
                        String elem = iterator.next();
                        
                        for (TypeRoom roomType : TypeRoom.values()) {
                            if (elem.equals(roomType.toString()))
                                typeRoomId.add(roomType);
                        }

                        for (Bed bedType : Bed.values()) {
                            if (elem.equals(bedType.toString()))
                                typeRoomId.add(bedType);
                        }

                        for (RoomFeatures roomFeature : RoomFeatures.values()) {
                            if (elem.equals(roomFeature.toString()))
                                typeRoomId.add(roomFeature);
                        }

                    }

                }
                super.setFileLoaded(true);
            } else
                throw new Exception("La lista de habitaciones contiene elementos");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}