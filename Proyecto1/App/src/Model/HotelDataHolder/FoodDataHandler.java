package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.Food;

public class FoodDataHandler extends HotelDataHolder<Food> {



    public FoodDataHandler(File roomsJSONFile) {
        super(roomsJSONFile);
    }

    /*
     * Crea una nueva comida y la almacena en la estructura que
     * guarda las comidas
     *
     * <b> pre: </b> isFileLoaded == true
     * <b> post: </b> La estructura va a tener un nuevo objeto Food<br>
     *
     * @throws Exception <br>
     * El archivo debe cargarse antes de crear una nueva comida
     *
     */
    public void createNewFood(String id, String name, double price, boolean isRoomService, String availability) throws Exception{

        if (super.getIsFileLoaded()){
            Map<Object, Food> menu = super.getData();
            Food newFood = new Food(id, name, price,isRoomService, availability);
            int getId = getFoodId(menu);
            menu.put(getId,newFood);
        } else {
            throw new Exception("El archivo debe cargarse antes de crear un nuevo objeto");
        }
    }

    private int getFoodId(Map<Object, Food> foodsList) {
        return  (foodsList.size() + 1);
    }

    /*
     * Carga la información del archivo en la estructura
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
    @Override
    public void loadPersistentData() throws Exception {



        JSONParser pJsonParser = new JSONParser();
        try{
            Object jsonObjToFile = pJsonParser.parse(new FileReader(super.getjSONDataFile()));
            JSONObject obj = (JSONObject) jsonObjToFile;

            @SuppressWarnings("unchecked")
            Map<String, JSONObject> objMap = (Map<String, JSONObject>) obj;

            if (super.getData().isEmpty()) {
                for (Map.Entry<String, JSONObject> foodEntry : objMap.entrySet()) {
                    String foodId = (String) foodEntry.getValue().get("id");
                    String name = (String) foodEntry.getValue().get("name");
                    double price = (double) foodEntry.getValue().get("price");
                    boolean isRoomService = (boolean) foodEntry.getValue().get("isRoomService");
                    String availability = (String) foodEntry.getValue().get("availability");

                    Map<Object,Food> menu = super.getData();

                    Food newFood = new Food(foodId,name,price,isRoomService,availability);

                    menu.put(foodId,newFood);
                }

                super.setFileLoaded(!super.getIsFileLoaded());
            } else{
                throw new Exception("La lista de comidas tiene elementos");
            }

        } catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON " + e);
        }

    }


}