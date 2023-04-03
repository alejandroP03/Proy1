package Controller.RegisterHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.HotelObjects.Booking;
import Model.HotelObjects.Registration;

public class RegisterHandler {
    // attributes

    private PrincipalGuest responsibleGuest;
    private Registration openRegister;

    // methods
    /*
     * Crea una nueva instancia de Registration con los parámetros
     *
     * <b>pre: </b> RegisterHandler está instanciado. <br>
     * <b>pos: </b> Se crea un nuevo registro. <br>
     *
     * @param name: Nombre del huésped principal. name != null && name != "".
     * @param dni: DNI del huésped principal.
     * @param email: Email del huésped principal.
     * @param phoneNumber: Número de teléfono del huésped principal.
     * @param group: Lista que contiene los acompañantes del huésped principal.
     * @param registeredRoomsIds: Lista que contiene de los ids de las habitaciones que se van a usar.
     * @param initialDate: Fecha inicial de la estadía
     * @param finalDate: Fecha final de la estadía
     */
    public void createRegister(String name,
            String dni,
            String email,
            String phoneNumber,
            ArrayList<CompanionGuest> group, List<String> registeredRoomsIds,
                               LocalDate initialDate,
                               LocalDate finalDate) {
        // crea instncia del prinicpalGuest
        this.responsibleGuest = new PrincipalGuest(name, dni, email, phoneNumber);
        // crea la instancia de la reserva
        this.openRegister = new Registration(responsibleGuest, group, registeredRoomsIds,initialDate,finalDate);
    }

    /*
    * Obtiene la reserva asociada a un dni y un grupo de huéspedes.
    * @param dni: DNI de la persona que realizó la reserva. dni != null && dni != "".
    * @param bookingMap: Mapa completo de las reservas realizadas.
    * @param groupGuest: Lista de los acompañantes del huésped principal
    * @throws Exception <br>
    *   1. Si el tamaño del grupo que se desea registrar es mayor al tamaño del grupo que tiene la reserva
    */
    public void getAsociatedBooking(String dni, Map<Object, Booking> bookingMap, ArrayList<CompanionGuest> groupGuests)
            throws Exception {
        Booking previousBooking = bookingMap.get(dni);

        if (groupGuests.size() == previousBooking.getNumberOfGuests()) {
            createRegister(previousBooking.getReserviourName(), previousBooking.getReserviourDNI(),
                    previousBooking.getReserviourEmail(), previousBooking.getReserviourPhone(), groupGuests,
                    previousBooking.getReservedRoomsIds(), previousBooking.getInitialDate(), previousBooking.getFinalDate());
        } else
            throw new Exception(
                    String.format("La reserva que existe, es para %d personas, el grupo contiene %d personas",
                            previousBooking.getNumberOfGuests(), groupGuests.size()));

    }

    public Registration getOpenRegister() {
        return this.openRegister;
    }


}
