package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.Service;

public class ServicesDataHandler extends HotelDataHolder<Service> {

    public ServicesDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    public void createNewService(String id,
            String name,
            double price,
            boolean isForGroup,
            ArrayList<DayOfWeek> daysAvailable,
            LocalTime initialTime,
            LocalTime finalTime) throws Exception {
        /*
         * Crea una nuevo servicio y la almacena en la estructura que
         * guarda los servicios
         *
         * <b> pre: </b> isFileLoaded == true
         * <b> post: </b> La estructura va a tener un nuevo objeto Service<br>
         *
         * @throws Exception <br>
         * El archivo debe cargarse antes de crear una nuevo servicio
         *
         */
        if (super.getIsFileLoaded()) {
            Map<Object, Service> services = super.getData();
            Service newService = new Service(id, name, price, isForGroup, daysAvailable, initialTime, finalTime);
            int getId = getServiceId(services);
            services.put(getId, newService);

        } else {
            throw new Exception("El archivo debe cargarse antes de crear un nuevo objeto");
        }
    }

    private int getServiceId(Map<Object, Service> serviceList) {
        return (serviceList.size() + 1);
    }

    @Override
    public void loadPersistentData() throws Exception {
        /*
         * Carga la información del archivo en la estructura
         * <b> pre: </b> <br>
         * El archivo debe estar en formato JSON <br>
         * La estructura debe estar vacia<br>
         *
         * <b> post: </b>
         * En el atributo dataHandler va a estar la información del archivo
         *
         */

        JSONParser pJsonParser = new JSONParser();
        Object jsonObjToFile = pJsonParser.parse(new FileReader(super.getjSONDataFile()));
        JSONObject obj = (JSONObject) jsonObjToFile;
        Map<String, JSONObject> objMap = (Map<String, JSONObject>) obj;

        if (super.getData().isEmpty()) {
            for (Map.Entry<String, JSONObject> serviceEntry : objMap.entrySet()) {
                String id = (String) serviceEntry.getValue().get("id");
                String name = (String) serviceEntry.getValue().get("name");
                double price = (double) serviceEntry.getValue().get("price");
                boolean isForGroup = (boolean) serviceEntry.getValue().get("isForGroup");
                LocalTime initialTime = (LocalTime) serviceEntry.getValue().get("initialTime");
                LocalTime finalTime = (LocalTime) serviceEntry.getValue().get("finalTime");

                ArrayList<String> daysString = (ArrayList<String>) serviceEntry.getValue().get("daysAvailable");

                ArrayList<DayOfWeek> daysArray = new ArrayList<DayOfWeek>();
                for (String day : daysString) {
                    daysArray.add(DayOfWeek.valueOf(day.toUpperCase()));

                }

                Map<Object, Service> services = super.getData();
                Service newService = new Service(id, name, price, isForGroup, daysArray, initialTime, finalTime);

                services.put(id, newService);
            }

            super.setFileLoaded(!super.getIsFileLoaded());
        } else {
            throw new Exception("La lista de comidas tiene elementos");
        }

    }

}