package Controller.BookingHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import Model.HotelObjects.Booking;
import Model.HotelObjects.RoomRelated.Room;

public class BookingHandler {
    // Atributos
    private Booking openBooking;

    // Metodos

    /* Crea una nueva instancia de Booking a partir de los parámetros y se la asigna a openBooking
     *
     * <b> pre: </b> BookingDataHandler ya debe estar instanciado.
     * <b>pos: </b> Se crea una nueva reserva. <br>
     *
     * @param reserviourName: Nombre de la persona responsable de la reserva. reserviourName != null && reserviourName != "".
     * @param reserviourDNI: DNI de la persona responsable de la reserva. reserviourDNI != null && reserviourDNI != "".
     * @param reserviourPhone: Número de teléfono de la persona responsable de la reserva. reserviourPhone != null && reserviourPhone != "".
     * @param reserviourEmail: Email de la persona responsable de la reserva. reserviourEmail != null && reserviourEmail != "".
     * @param reserviourSupportCardNumber: Número de tarjeta de crédito del responsable de la reserva. reserviourSupportCardNumber != null && reserviourSupportCardNumber != "".
     * @param numberOfGuests: Número de acompañantes que vienen con el responsable. numberOfGuests != null.
     * @param initialDate: Fecha inicial de la estadía. initialDate != null.
     * @param finalDate: Fecha final de la estadía. finalDate != null.
     *
     */
    public void createBooking(String reserviourName, String reserviourDNI, String reserviourPhone,
            String reserviourEmail, String reserviourSupportCardNumber, int numberOfGuests, LocalDate initialDate,
            LocalDate finalDate) {


        this.openBooking = new Booking(reserviourName, reserviourDNI, reserviourPhone, reserviourEmail,
                reserviourSupportCardNumber, numberOfGuests, initialDate, finalDate);

    }

    /*
     * Agrega las habitaciones seleccionadas a la reserva y a cada una de las
     * habitaciones les agrega la fecha reservada
     *
     * <b>pre: </b> La lista de habitaciones debe estar cargada
     * <b>pos: </b> A las habitaciones seleccionadas se les asigna la fecha en la que son reservadas
     *
     * @params selectedRooms: Habitaciones seleccionadas para la reserva
     * @params allRoms: Mapa de todas las habitaciones
     */
    public void reserveRooms(List<String> selectedRooms, Map<Object, Room> allRooms) {

        this.openBooking.setReservedRooms(selectedRooms);

        for (String roomId : selectedRooms) {
            allRooms.get(roomId).addBookedDate(this.openBooking.getInitialDate(),
                    this.openBooking.getFinalDate());
        }

    }

    public Booking getOpenBooking() {
        return openBooking;
    }

}
