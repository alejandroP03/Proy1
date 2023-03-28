package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

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
    private  void showTypeUser() throws Exception {
        if (activeUser instanceof Admin){
            showAdminScreen();

        }else if(activeUser instanceof Receptionist){
            showRecepcionistScreen();

        }else if(activeUser instanceof Employee){
            showEmployeeScreen();

        }
    }


    // ----------------------   Pantalla para el Administrador ----------------------
    private  void showAdminScreen() throws Exception {
        System.out.println("------  Inicio como administrador ------- ");
        System.out.println("1. Crear habitaciones (manual) ");
        System.out.println("2. Cargar archivo habitaciones");
        System.out.println("3. Cargar tarifas");
        System.out.println("4. Consultar inventario");
        System.out.println("5. Crear servicios (manual) ");
        System.out.println("6. Cargar archivo de servcios");
        System.out.print("Ingrese una opcion: ");
        String opcionStr = br.readLine();
        int opcion = Integer.parseInt(opcionStr);
        if(opcion == 1){
            System.out.println("Crear nueva habitacion");
            createRoom();
        }else if(opcion == 2){
            System.out.println("Cargar archivo habitaciones");
            loadDataRooms();

        }else if(opcion == 3){
            System.out.println("Cargar tarifas");
        }else if(opcion == 4){
            System.out.println("Consultar inventario");
        }
    }
    // ----------------------  Funciones para el adminsitrador ----------------------
    // Crear una nueva habitacion a mano
    private void createRoom() throws Exception {

        System.out.println("------ Crear habitaciones------- ");
        System.out.print("Cuantas habitaciones desea crear? : ");
        String numRoomsStr = br.readLine();
        int numRooms = Integer.parseInt(numRoomsStr);
        Bed[] typeBeds = Bed.values();
        RoomFeatures[] typeFeeatures = RoomFeatures.values();
        TypeRoom[] typeRooms = TypeRoom.values();

        Map<Bed,Integer> mapBeds = new HashMap<Bed,Integer>();
        Set<RoomFeatures> featuresList = new HashSet<RoomFeatures>();

        for(int i = 0; i <= numRooms; i++){
            System.out.println("----------- Datos para la"+i+"habitacion -----------");
            System.out.print("Ingrese la ubicacion de la habitacion: ");
            String location = br.readLine();
            boolean isMoreBeds = true;

            // Creacion del mapa  para las camas

            while(isMoreBeds){
                System.out.println("Tipos de cama disponibles para agregar");
                for (Bed typeRoom : typeBeds ) {
                    int x = 1;
                    System.out.println(x+"."+ typeRoom);
                    x++;
                }

                System.out.println("Elija el tipo de cama que desea agregar a la habitacion: ");
                String chooseBedStr = br.readLine();
                int chooseBed = Integer.parseInt(chooseBedStr);
                Bed bedChoose = typeBeds[chooseBed];
                System.out.print("Cuantas camas de este tipo desea agregar? : ");
                String numBedsStr = br.readLine();
                int numBeds = Integer.parseInt(numBedsStr);
                mapBeds.put(bedChoose,numBeds);

                System.out.println("Desea agregar mas tipos de cama?");
                System.out.println("1.Si \n 2. No ");
                String isMoreBedsStr = br.readLine();
                int moreBeds = Integer.parseInt(isMoreBedsStr);
                if(moreBeds == 2){
                    break;
                }
            }
            // Creacion del set para las caracteristicas

            while(isMoreBeds){
                System.out.println("Caracteristicas posibles para la habitacion:");
                for (RoomFeatures typeFeature : typeFeeatures ) {
                    int x = 1;
                    System.out.println(x+"."+ typeFeature);
                    x++;
                }
                System.out.print("Elija el tipo de caracteristica que tendra la habitacion: ");
                String chooseFeatureStr = br.readLine();
                int chooseFeature = Integer.parseInt(chooseFeatureStr);
                RoomFeatures featuresChoose = typeFeeatures[chooseFeature];
                featuresList.add(featuresChoose);

                System.out.println("Desea agregar mas caracterisitcas a la habitacion?");
                System.out.println("1.Si \n 2. No ");
                String isMoreFeaturesStr = br.readLine();
                int moreFeatures = Integer.parseInt(isMoreFeaturesStr);
                if(moreFeatures == 2){
                    break;
                }
            }
            // Creacion del tipo de room

            System.out.println("Tipos posibles para la habitacion: ");
            for (TypeRoom typeRoom: typeRooms ){
                int x = 1;
                System.out.println(x+"."+ typeRoom);
                x++;
            }
            System.out.print("Elija el tipo de la habitacion: ");
            String chooseTypeStr = br.readLine();
            int chooseType= Integer.parseInt(chooseTypeStr);
            TypeRoom typeChoose = typeRooms[chooseType];

            hotel.getRoomsHandler().createNewRoom(location,false,mapBeds,featuresList,typeChoose);
        }

        try{
            createRoom();
        }catch(Exception e){
            System.out.println(e);
        }


    }
    // Metodo que carga un archivo con datos predeterminaodos por el adminsitrador (Reader de un JSON)
    private void loadDataRooms() throws Exception {
        hotel.getRoomsHandler().loadPersistentData();
        try{
            loadDataRooms();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // Metodo que carga las tarifas ???
    private void loadFares(){


    }

    //Metodo que el administrador usa para cargar los servicios del hotel
    private void createService() throws Exception {
        System.out.println("------ Crear servicio ------- ");
        System.out.println("Ingrese el id del servicio????");
        String idStr = br.readLine();
        int id= Integer.parseInt(idStr);

        System.out.print("Ingrese el nombre del serivicio");
        String name = br.readLine();

        System.out.print("Ingrese el precio del servicio");
        String precioStr = br.readLine();
        double precio= Double.parseDouble(precioStr);

        System.out.println("El servicio es para varias personas? ");
        System.out.println("1.Si \n 2. No ");
        String isGroupStr = br.readLine();
        int isGroup= Integer.parseInt(isGroupStr);
        boolean isForGroup ;
        if(isGroup == 1){
            isForGroup = true;
        }else  isForGroup = false;

        System.out.println("Que dias a la semana va a estar disponible el servicio?");
        DayOfWeek[] daysWeek = DayOfWeek.values();
        Set<DayOfWeek> daySet = new HashSet<DayOfWeek>();
        while(true){
            for (DayOfWeek dayWeek : daysWeek ) {
                int x = 1;
                System.out.println(x+"."+ dayWeek);
                x++;
            }
            System.out.println("Ingrese el dia a la semana que desea agregar:");
            String dayWeekStr = br.readLine();
            int dayWeek= Integer.parseInt(dayWeekStr);
            DayOfWeek dayChoose = daysWeek[dayWeek];
            daySet.add(dayChoose);

            System.out.println("Desea ingresar otro dia?");
            System.out.println("1.Si \n 2. No ");
            String otherDayStr = br.readLine();
            int otherDay= Integer.parseInt(otherDayStr);
            if(otherDay == 2){
                break;
            }

        }
        System.out.println("Ingrese la hora inicial disponible del servicio");
        String initialDateStr = br.readLine();
        int initialDateOption = Integer.parseInt(initialDateStr);

        System.out.println("Ingrese la hora final disponible del servicio");
        String finalDateStr = br.readLine();
        int finalDateOption = Integer.parseInt(finalDateStr);

        LocalTime initialDate = LocalTime.of(initialDateOption, 0);
        LocalTime finalDate = LocalTime.of(finalDateOption, 0);

        ArrayList<DayOfWeek> dayList = new ArrayList<DayOfWeek>(daySet);
        hotel.getServices().createNewService(id, name,precio,isForGroup,dayList,initialDate,finalDate);


    }




    private static void showRecepcionistScreen(){
        System.out.println("Pantalla funciones de Recepcionista");
    }

    private static void showEmployeeScreen(){
        System.out.println("Pantalla funciones de employee");

    }

}
