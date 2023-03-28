package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Model.HotelObjects.Admin;
import Model.HotelObjects.Employee;
import Model.HotelObjects.Recepcionist;
import Model.HotelObjects.User;

public class App {
    User activeUser;
    public static void main(String[] args) throws Exception {

        System.out.println("Bienvenido al sistema del hotel!");
        App instanceApp = new App();
        instanceApp.authApp();
        instanceApp.showTypeUser();



    }
    private  void authApp() throws IOException {

        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesion");
        System.out.print("Ingrese la opcion deseada: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
    private  void showTypeUser(){
        if (activeUser instanceof Admin){
            showAdminScreen();

        }else if(activeUser instanceof Recepcionist){
            showRecepcionistScreen();

        }else if(activeUser instanceof Employee){
            showEmployeeScreen();

        }
    }

    private static void showAdminScreen(){
        System.out.println("------  Inicio como administrador ------- ");
        System.out.println("1. crear habitacion");
        System.out.println("2. Cargar archivo habitaciones");
        System.out.println("3. Cargar tarifas");
        System.out.println("4. Consultar inventario");



    }
    private static void showRecepcionistScreen(){
        System.out.println("Pantalla funciones de Recepcionista");
    }

    private static void showEmployeeScreen(){
        System.out.println("Pantalla funciones de employee");

    }

}
