package Controller.BookingHandler;

import java.time.LocalDate;

import Controller.Hotel;

public class BookingHandler {
    // Atributos
    private Booking openBooking;
    private Hotel bookingData;

    // Constructor
    // Metodos
    public void createBooking(String reserviourName, String reserviourDNI, String reserviourPhone,
            String reserviourEmail, String reserviourSupportCardNumber, int numberOfGuests, LocalDate initialDate,
            LocalDate finalDate) {
        openBooking = new Booking(reserviourName, reserviourDNI, reserviourPhone, reserviourEmail,
                reserviourSupportCardNumber, numberOfGuests, initialDate, finalDate);
        changeRoomStatus();
    }

    private void changeRoomStatus() {
        // Se busca en el hashMap de Room con bookingData

    }

}
