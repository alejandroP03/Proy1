package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.out.println(typeRoomId);
            System.out.println(roomFareList);
        } else {
            throw new Exception("El archivo debe cargarse antes de crear un nuevo objeto");
        }
    }

    @Override
    public void loadPersistentData() {
        /*
         * Carga la información del archivo en la estructura
         * 
         
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

                for (Map.Entry<String, JSONObject> fareListEntry : objMap.entrySet()) {
                    Set<Object> typeRoomId = this.createTypeRoomId(fareListEntry.getKey());
                    
                    RoomFares newRoomFares = getRoomFare(fareListEntry.getValue(), typeRoomId);
                    super.getData().put(typeRoomId, newRoomFares);
                }
                super.setFileLoaded(true);
            } else
                throw new Exception("La lista de habitaciones contiene elementos");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private RoomFares getRoomFare(JSONObject faresObj, Set<Object> roomFareId){
    /*
     * A partir de la persistencia, carga un objeto RoomFare
     * 
     * <b> post: </b> <br>
     *  Devuelve un objeto de tipo RoomFare
     * 
     * @param faresObj El objeto JSON con las tarifas de una habitación
     * @param roomFareId El identificador del tipo de habitación
     */
        RoomFares roomFare = new RoomFares(roomFareId);
        JSONArray faresObjects = (JSONArray) faresObj.get("fares");

        for (Object object : faresObjects) {
            JSONObject fareInfo = (JSONObject) object;
            Fare newFare = getFare(fareInfo);
            roomFare.addFare(newFare);
        }
        
        return roomFare;
    }

    private Fare getFare(JSONObject fareObj){
        /*
         * Crea un objeto de tipo Fare a partir de la persistencia
         *  
         * <b> post: </b> <br>
         * Devuelve un objeto de tipo Fare
         * 
         * @params fareObj Objeto JSON con la información de la tarifa
         */
        float price = Float.parseFloat(fareObj.get("price").toString());
        LocalDate initialDate = LocalDate.parse((String) fareObj.get("initialDate"));
        LocalDate finalDate = LocalDate.parse((String) fareObj.get("finalDate"));

        JSONArray daysArray = (JSONArray) fareObj.get("days");
        ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();

        for (Object object : daysArray) {
            DayOfWeek day = DayOfWeek.valueOf((String) object);
            days.add(day);
        }



        Fare newFare = new Fare(price, initialDate, finalDate, days);
        
        return newFare;
    }

    private Set<Object> createTypeRoomId(String roomFareId){
        /*
         * Se castea el string con la información del tipo de la habitación (camas, características, tipo)
         * para crear el Set con dicha información (Que actua como nuestro ID de tipo de habitación)
         * 
         * <b> pre: </b> <br>
         * El String debe tener la siguiente estructura "
         * [
         *  <<Tipo de habitación>>, 
         *  <<Característica>>, 
         *  <<Característica>>, 
         *  {
         *     <<Cama>> = <<número de camas>>,
         *     <<Cama>> = <<número de camas>>,
         *  }
         * ]"
         * 
        */

        Set<Object> typeRoomId = new HashSet<Object>();

        final String regex = "\\[*\\{*\\}*\\]*";
        final String subst = "";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(roomFareId);
        final String key = matcher.replaceAll(subst);

        String[] roomElems = key.split(", ");
        
        //Mapa con la composición de las camas
        Map<Bed, Integer> bedComposition = new HashMap<Bed, Integer>();

        for (String roomElem : roomElems) {
            String[] elem = roomElem.split("=");
            
            //Si el array tiene mas de un elemtno significa que es del mapa
            if(elem.length > 1){
                for (Bed bedType : Bed.values()) {
                    if (elem[0].equals(bedType.toString()))
                        bedComposition.put(bedType, Integer.parseInt(elem[1]));
                }
            }
            
            else{
                for (TypeRoom roomType : TypeRoom.values()) {
                    if (elem[0].equals(roomType.toString()))
                        typeRoomId.add(roomType);
                }

                for (RoomFeatures roomFeature : RoomFeatures.values()) {
                    if (elem[0].equals(roomFeature.toString()))
                        typeRoomId.add(roomFeature);
                }
            }
        }
        typeRoomId.add(bedComposition);

        return typeRoomId;
    }

}