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


    public void getAsociatedBooking(String dni, Map<Object, Booking> bookingMap, ArrayList<CompanionGuest> groupGuests)
            throws Exception {
        /*
         * 
         * 
         * @param groupOfGuests debe coincidir con el numero de acompañantes con quien
         * va
         */

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
