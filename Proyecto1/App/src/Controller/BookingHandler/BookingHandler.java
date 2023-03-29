package Controller.BookingHandler;

import java.time.LocalDate;
import java.util.Map;

import Model.HotelObjects.Booking;
import Model.HotelObjects.RoomRelated.Room;

public class BookingHandler {
    // Atributos
    private Booking openBooking;

    // Metodos
    public void createBooking(String reserviourName, String reserviourDNI, String reserviourPhone,
            String reserviourEmail, String reserviourSupportCardNumber, int numberOfGuests, LocalDate initialDate,
            LocalDate finalDate) {
        this.openBooking = new Booking(reserviourName, reserviourDNI, reserviourPhone, reserviourEmail,
                reserviourSupportCardNumber, numberOfGuests, initialDate, finalDate);
    }

    public void reserveRooms(Map<String, Room> selectedRooms, Map<String, Room> allRooms) {
        /*
         * Agrega las habitaciones seleccionadas a la reserva y a cada una de las
         * habitaciones les agrega la fecha reservada
         * 
         * @params selectedRooms: Habitaciones seleccionadas para la reserva
         */
        this.openBooking.setReservedRooms(selectedRooms);
        for (Room room : selectedRooms.values()) {
            allRooms.get(room.getRoomId()).addBookedDate(this.openBooking.getInitialDate(),
                    this.openBooking.getFinalDate());
        }

    }

}
