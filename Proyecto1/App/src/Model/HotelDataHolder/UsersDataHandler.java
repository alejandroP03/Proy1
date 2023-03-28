package Model.HotelDataHolder;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Model.HotelObjects.HotelObject;
import Model.HotelObjects.UserType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.HotelObjects.User;

public class UsersDataHandler extends HotelDataHolder<User> {


    public UsersDataHandler(File usersJSONFile){
        super(usersJSONFile);
    }

    public void createNewUser(String username, String password, UserType userType){
        /*
         * Crea un nuevoi usuerio y lo ingresa en la estructura que guarda las
         * habitaciones
         *
         * <b> pre: </b> isFileLoaded == True
         * <b> post: </b> La estructura va a tener un nuevo objeto User<br>
         *
         * @throws Exception <br>
         * El archivo debe cargarse antes de crear una nueva habitación
         */

        if(super.getIsFileLoaded()){
            Map<Object, User> usersList = super.getData();



        }

    }



    @Override
    public void loadPersistentData() throws Exception{

    }
}
