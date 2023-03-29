package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.Booking;

public class BookingsDataHandler extends HotelDataHolder<Booking> {

    public BookingsDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    @Override
    public void loadPersistentData() throws Exception {
        /*
         * Carga la información del archivo en la estructura
         * 
         * <b> pre: </b> <br>
         * El archivo debe estar en formato JSON <br>
         * La estructura debe estar vacia<br>
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
                for (Map.Entry<String, JSONObject> bookingEntry : objMap.entrySet()) {

                    String reserviourName = (String) bookingEntry.getValue().get("reserviourName");
                    String reserviourDNI = (String) bookingEntry.getValue().get("reserviourDNI");
                    String reserviourPhone = (String) bookingEntry.getValue().get("reserviourPhone");
                    String reserviourEmail = (String) bookingEntry.getValue().get("reserviourEmail");
                    String reserviourSupportCardNumber = (String) bookingEntry.getValue()
                            .get("reserviourSupportCardNumber");
                    int numberOfGuests = (int) bookingEntry.getValue().get("numberOfGuests");
                    LocalDate initialDate = (LocalDate) bookingEntry.getValue().get("initialDate");
                    LocalDate finalDate = (LocalDate) bookingEntry.getValue().get("finalDate");

                    JSONArray reservedRoomsIdsJson = (JSONArray) bookingEntry.getValue().get("reservedRoomsIds");
                    ArrayList<String> reservedRoomsIds = new ArrayList<String>();
                    for (Object roomId : reservedRoomsIdsJson) {
                        reservedRoomsIds.add((String) roomId);
                    }

                    Booking newBooking = new Booking(reserviourName, reserviourDNI, reserviourPhone, reserviourEmail,
                            reserviourSupportCardNumber, numberOfGuests, initialDate, finalDate);

                    newBooking.setReservedRooms(reservedRoomsIds);

                    super.getData().put(reserviourDNI, newBooking);

                }

                // Esto es lo que confirma que el archivo ya fue cargado y ya se van a poder
                // crear objetos
                super.setFileLoaded(true);
            } else {
                throw new Exception("La lista de habitaciones contiene elementos");
            }

        } catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON ", e);
        }
    }

    public void saveNewBooking(Booking newBooking) {
        super.getData().put(newBooking.getReserviourDNI(), newBooking);
    }
}