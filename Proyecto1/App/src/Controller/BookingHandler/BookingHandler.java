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
    public void createBooking(String reserviourName, String reserviourDNI, String reserviourPhone,
            String reserviourEmail, String reserviourSupportCardNumber, int numberOfGuests, LocalDate initialDate,
            LocalDate finalDate, Map<Object, Booking> bookingsMap) {
        this.openBooking = new Booking(reserviourName, reserviourDNI, reserviourPhone, reserviourEmail,
                reserviourSupportCardNumber, numberOfGuests, initialDate, finalDate);

        bookingsMap.put(reserviourDNI, openBooking);
    }

    public void reserveRooms(List<String> selectedRooms, Map<Object, Room> allRooms) {
        /*
         * Agrega las habitaciones seleccionadas a la reserva y a cada una de las
         * habitaciones les agrega la fecha reservada
         * 
         * @params selectedRooms: Habitaciones seleccionadas para la reserva
         */
        this.openBooking.setReservedRooms(selectedRooms);

        for (String roomId : selectedRooms) {
            allRooms.get(roomId).addBookedDate(this.openBooking.getInitialDate(),
                    this.openBooking.getFinalDate());
        }

    }

}
