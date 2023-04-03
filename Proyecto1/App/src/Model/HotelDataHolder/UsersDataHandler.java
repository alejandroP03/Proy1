package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.Admin;
import Model.HotelObjects.Employee;
import Model.HotelObjects.Receptionist;
import Model.HotelObjects.User;
import Model.HotelObjects.UserType;

public class UsersDataHandler extends HotelDataHolder<User> {


    public UsersDataHandler(File usersJSONFile){
        super(usersJSONFile);
    }

    /*
     * Crea un nuevo usuario y lo ingresa en la estructura que guarda los
     * usuarios
     *
     * <b> pre: </b> isFileLoaded == True
     * <b> post: </b> La estructura va a tener un nuevo objeto User<br>
     *
     * @throws Exception <br>
     * El archivo debe cargarse antes de crear un nuevo usuario
     */
    public void createNewUser(String userName, String password, UserType userType) throws Exception{


        if(super.getIsFileLoaded()){
            Map<Object, User> usersList = super.getData();

            if(userType.equals(UserType.ADMIN)){
                User newUser = new Admin(userName, password);
                usersList.put(userName, newUser);

            } else if(userType.equals(UserType.RECEPTIONIST)){
                User newUser = new Receptionist(userName, password);
                usersList.put(userName, newUser);

            } else if(userType.equals(UserType.EMPLOYEE)){
                User newUser = new Employee(userName,password);
                usersList.put(userName, newUser);

            }
        } else {
            throw new Exception("El archivo debe cargarse antes de crear un nuevo objeto");
        }

    }


    /*
     * Carga la información del archivo en la estructura
     *
     * <b> pre: </b> <br>
     * El archivo debe estar en formato JSON <br>
     * La estructura debe estar vacía<br>
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
    public void loadPersistentData() throws Exception{

        JSONParser pJsonParser = new JSONParser();
        try{
            Object jsonObjToFile = pJsonParser.parse(new FileReader(super.getjSONDataFile()));
            JSONObject obj = (JSONObject) jsonObjToFile;

            @SuppressWarnings("unchecked")
            Map<String, JSONObject> objMap = (Map<String, JSONObject>) obj;

            if (super.getData().isEmpty()){
                for (Map.Entry<String, JSONObject> userEntry : objMap.entrySet()){
                    String userName = (String) userEntry.getValue().get("userName");
                    String password = (String) userEntry.getValue().get("password");
                    UserType userType = UserType.valueOf((String) userEntry.getValue().get("userType"));

                    Map<Object, User> usersList = super.getData();

                    if(userType.equals(UserType.ADMIN)){
                        User newUser = new Admin(userName, password);
                        usersList.put(userName, newUser);

                    } else if(userType.equals(UserType.RECEPTIONIST)){
                        User newUser = new Receptionist(userName, password);
                        usersList.put(userName, newUser);

                    } else if(userType.equals(UserType.EMPLOYEE)){
                        User newUser = new Employee(userName,password);
                        usersList.put(userName, newUser);

                    }


                }
                super.setFileLoaded(true);

            } else {
                throw new Exception("La lista de usuarios contiene elementos");
            }

        } catch (Exception e) {
            throw new Exception("El archivo no tiene la estructura JSON ", e);
        }

    }
}
