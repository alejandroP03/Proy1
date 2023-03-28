package Controller.WorkersAuth;

import Model.HotelDataHolder.UsersDataHandler;
import Model.HotelObjects.*;
import org.json.simple.JSONObject;



import java.util.Map;
import java.io.File;


public class HotelWorkersAuth {

    public HotelWorkersAuth(){}



    // methods
    public User login(String userName, String password, UserType userType, Map<Object, User> usersList) throws Exception {
        /*
        * Comprueba que el usuario que va a iniciar sesión existe en la lista de usuarios, de ser así,
        * lo retorna <br>
        *
        * <b> pre: </b> La estructura ya debe estar cargada
        * <b> post: </b> Se retorna el usuario
        *
        * @throws Exception <br>
        * El usuario no se encuentra registrado
         */

        User newUser;
        if(userExists(userName,password,userType, usersList)){

            if(userType.equals(UserType.ADMIN)){
                newUser = new Admin(userName, password);
                return newUser;

            } else if(userType.equals(UserType.RECEPTIONIST)){
                newUser = new Receptionist(userName, password);
                return newUser;

            } else if(userType.equals(UserType.EMPLOYEE)){
                newUser = new Employee(userName,password);
                return newUser;


            }
        } else {
            throw new Exception("El usuario no se encuentra registrado");

        }
        return null;


    }

    public User register(String userName, String password, UserType userType, Map<Object, User> usersList) throws Exception {
        /*
         * Comprueba que el usuario que se va a registrar no existe en la lista de usuarios, de ser así,
         * lo retorna <br>
         *
         * <b> pre: </b> La estructura ya debe estar cargada
         * <b> post: </b> Se retorna el usuario
         *
         * @throws Exception <br>
         * El usuario ya se encuentra registrado
         */
        User newUser;
        if(!userExists(userName,password,userType,usersList)){

            if(userType.equals(UserType.ADMIN)){
                newUser = new Admin(userName, password);
                usersList.put(newUser.getUserName(),newUser);
                return newUser;

            } else if(userType.equals(UserType.RECEPTIONIST)){
                newUser = new Receptionist(userName, password);
                usersList.put(newUser.getUserName(),newUser);
                return newUser;

            } else if(userType.equals(UserType.EMPLOYEE)){
                newUser = new Employee(userName,password);
                usersList.put(newUser.getUserName(),newUser);
                return newUser;


            }
        } else {
            throw new Exception("El usuario ya se encuentra registrado");

        }
        return null;

    }

    public boolean userExists(String userName, String password, UserType userType, Map<Object, User> usersList){
        /*
         * Función auxiliar de login y register. Comprueba que el usuario existe en la lista de usuarios, de ser así,
         * retorna true.  <br>
         *
         * <b> pre: </b> La estructura ya debe estar cargada
         * <b> post: </b> Se retorna un booleano que indica si el usuario existe o no
         *
         */
        boolean exists = false;


        for (Map.Entry<Object, User> userEntry : usersList.entrySet()){

            String localUserName = (String) userEntry.getKey();
            User localUser = userEntry.getValue();
            String localPassword = localUser.getPassword();
            UserType localUserType = localUser.getUserType();

            if(userName.equals(localUserName) && password.equals(localPassword) && userType.equals(localUserType)) {
                exists = true;
            }


        }

        return exists;
    }


}
