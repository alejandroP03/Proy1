package Controller.BookingHandler;

import Controller.Hotel;

import java.time.LocalDate;

public class BookingHandler {
    //Atributos
    private Booking openBooking;
    private Hotel bookingData;
    //Constructor
    //Metodos
    public void createBooking(String reserviourName , String reserviourDNI, String reserviourPhone, String reserviourEmail, String reserviourSupportCardNumber, int numberOfGuests, LocalDate initialDate, LocalDate finalDate){
        openBooking = new Booking(reserviourName,reserviourDNI, reserviourPhone, reserviourEmail, reserviourSupportCardNumber, numberOfGuests, initialDate,finalDate );

    }
    private void changeRoomStatus(Booking openBooking){
        // Se busca en el hashMap de Room con bookingData

    }





}
