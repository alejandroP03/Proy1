package Controller.WorkersAuth;

import java.util.Map;

import Model.HotelObjects.Admin;
import Model.HotelObjects.Employee;
import Model.HotelObjects.Receptionist;
import Model.HotelObjects.User;
import Model.HotelObjects.UserType;

public class HotelWorkersAuth {

    private static HotelWorkersAuth instance;

    private HotelWorkersAuth() {
    };

    public static HotelWorkersAuth getInstance() {
        if (instance == null) {
            instance = new HotelWorkersAuth();
        }
        return instance;
    }
    // methods

    /*
     * Comprueba que el usuario que va a iniciar sesión existe en la lista de
     * usuarios, de ser así,
     * lo retorna <br>
     *
     * <b> pre: </b> La estructura ya debe estar cargada
     * <b> post: </b> Se retorna el usuario
     *
     * @throws Exception <br>
     * Nombre de usuario o contraseña inválido
     *
     *
     * @throws Exception <br>
     * El usuario no se encuentra registrado
     */
    public User login(String userName, String password, Map<Object, User> usersList)
            throws Exception {

        if (userExists(userName, password, usersList)) {
            if (!(userName.isBlank() || password.isBlank())) {
                return usersList.get(userName);
            } else {
                throw new Exception("Nombre de usuario invalido o contraseña inválido");
            }

        } else {
            throw new Exception(
                    "Nombre de usuario invalido o contraseña incorrecto (Verifique si se encuentra registrado)");

        }
    }

    /*
     * Comprueba que el usuario que se va a registrar no existe en la lista de
     * usuarios, de ser así,
     * lo retorna <br>
     *
     * <b> pre: </b> La estructura ya debe estar cargada
     * <b> post: </b> Se retorna el usuario
     *
     * @throws Exception <br>
     * El usuario ya se encuentra registrado
     *
     * * @throws Exception <br>
     * Nombre de usuario invalido o contraseña invalido
     *
     */
    public User register(String userName, String password, UserType userType, Map<Object, User> usersList)
            throws Exception {

        User newUser = null;
        if (!userExists(userName, password, usersList)) {

            if (!(userName.isBlank() || password.isBlank())) {

                if (userType.equals(UserType.ADMIN)) {
                    newUser = new Admin(userName, password);
                    usersList.put(newUser.getUserName(), newUser);

                } else if (userType.equals(UserType.RECEPTIONIST)) {
                    newUser = new Receptionist(userName, password);
                    usersList.put(newUser.getUserName(), newUser);

                } else if (userType.equals(UserType.EMPLOYEE)) {
                    newUser = new Employee(userName, password);
                    usersList.put(newUser.getUserName(), newUser);

                }
            } else {
                throw new Exception("Nombre de usuario invalido o contraseña invalido");
            }
        } else {
            throw new Exception("El usuario ya se encuentra registrado");

        }
        return newUser;
    }

    /*
     * Función auxiliar de login y register. Comprueba que el usuario existe en la
     * lista de usuarios, de ser así,
     * retorna true. <br>
     *
     * <b> pre: </b> La estructura ya debe estar cargada
     * <b> post: </b> Se retorna un booleano que indica si el usuario existe o no
     *
     */
    public boolean userExists(String userName, String password, Map<Object, User> usersList) {

        User userQuery = usersList.get(userName);
        if (userQuery != null && password.equals(userQuery.getPassword())) {
            return true;
        }

        return false;
    }

}
