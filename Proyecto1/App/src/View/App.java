package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import Controller.Hotel;
import Model.HotelObjects.Admin;
import Model.HotelObjects.Employee;
import Model.HotelObjects.Receptionist;
import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.TypeRoom;
import Model.HotelObjects.User;

public class App {
    User activeUser;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Hotel hotel = new Hotel();

    public static void main(String[] args) throws Exception {

        System.out.println("Bienvenido al sistema del hotel!");
        App instanceApp = new App();
        instanceApp.authApp();
        instanceApp.showTypeUser();


    }
    // ----------------------  Autenticacion en la App de Hoteles----------------------
    private  void authApp() throws IOException {
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesion");
        System.out.print("Ingrese la opcion deseada: ");
        String opcionStr = br.readLine();
        int opcion = Integer.parseInt(opcionStr);
        if (opcion == 1){
            System.out.println(" ");
            System.out.println("----- Registro  -----");
            System.out.println("Como desea registrarse?");
            System.out.println("1. Como administrador");
            System.out.println("2. Como empleado");
            System.out.println("3. Como recepcionista");
            System.out.print("Ingrese la opcion deseada: ");
            String registroStr = br.readLine();
            int registro = Integer.parseInt(registroStr);
            if (registro == 1){
                System.out.println("Ingrese sus datos de Administrador:");
                //TODO falta crear el Auth usuarios

            }else if(registro == 2){
                System.out.println("Ingrese sus datos de Empleado:");
                //TODO falta crear el Auth usuarios

            }else if(registro == 3){
                System.out.println("Ingrese sus datos de recepcionista:");
                //TODO falta crear el Auth usuarios

            }
        }else if(opcion == 2){
            System.out.println(" ");
            System.out.println("----- Inicio de sesion  -----");
            System.out.print("Ingrese su usuario: ");
            String usuarioStr = br.readLine();
            //TODO llamar la funcion que verifica el login de un usuario

            System.out.print("Ingrese su contrasena: ");
            String contrasenaStr = br.readLine();
            // TODO implementar if que de acceso o denegue la entrada al usuario

        }

    }

    // ----------------------  Comprobacion tipo de usuario ----------------------
    private  void showTypeUser() throws IOException {
        if (activeUser instanceof Admin){
            showAdminScreen();

        }else if(activeUser instanceof Receptionist){
            showRecepcionistScreen();

        }else if(activeUser instanceof Employee){
            showEmployeeScreen();

        }
    }


    // ----------------------   Pantalla para el Administrador ----------------------
    private  void showAdminScreen() throws IOException{
        System.out.println("------  Inicio como administrador ------- ");
        System.out.println("1. Crear habitaciones (manual) ");
        System.out.println("2. Cargar archivo habitaciones");
        System.out.println("3. Cargar tarifas");
        System.out.println("4. Consultar inventario");
        System.out.print("Ingrese una opcion: ");
        String opcionStr = br.readLine();
        int opcion = Integer.parseInt(opcionStr);
        if(opcion == 1){
            System.out.println("Crear nueva habitacion");
        }else if(opcion == 2){
            System.out.println("Cargar archivo habitaciones");
        }else if(opcion == 3){
            System.out.println("Cargar archivo habitaciones");
        }else if(opcion == 4){
            System.out.println("Consultar inventario");
        }
    }
    // ----------------------  Funciones para el adminsitrador ----------------------
    private void createRoom() throws Exception {

        System.out.println("------ Crear habitaciones------- ");
        System.out.print("Cuantas habitaciones desea crear? : ");
        String numRoomsStr = br.readLine();
        int numRooms = Integer.parseInt(numRoomsStr);

        for(int i = 0; i <= numRooms; i++){
            System.out.println("----------- Datos para la"+i+"habitacion -----------");
            System.out.print("Ingrese la ubicacion de la habitacion: ");
            String location = br.readLine();
            System.out.println("Tipos de cama disponibles para agregar");
            for (TypeRoom typeRoom : TypeRoom.values() ) {
                int x = 1;
                System.out.println(x+"."+ typeRoom);
                x++;
            }
            System.out.println("Elija el tipo de cama que desea agregar a la habitacion");





        }


        //hotel.getRoomsHandler().createNewRoom(location,false,beds,featuresList,type);


    }




    private static void showRecepcionistScreen(){
        System.out.println("Pantalla funciones de Recepcionista");
    }

    private static void showEmployeeScreen(){
        System.out.println("Pantalla funciones de employee");

    }

}
