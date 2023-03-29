package Controller.RegisterHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.Hotel;
import Model.HotelObjects.Booking;
import Model.HotelObjects.Registration;
import Model.HotelObjects.RoomRelated.Room;

public class RegisterHandler {
    // attributes

    private Group group;
    private PrincipalGuest responsibleGuest;
    private Registration openRegister;
    private Hotel avaiableRooms;

    // methods
    public void createRegister(String name,
            String dni,
            String email,
            String phoneNumber,
            ArrayList<CompanionGuest> group, List<String> registeredRoomsIds) {
        // crea instncia del prinicpalGuest
        this.responsibleGuest = new PrincipalGuest(name, dni, email, phoneNumber);
        // crea la instancia de group
        this.group = new Group(group);
        
    }

    public void getAsociatedBooking(String dni, Map<String, Booking> bookingMap, ArrayList<CompanionGuest> groupGuests)
            throws Exception {
        /*
         * 
         * 
         * @param groupOfGuests debe coincidir con el numero de acompañantes con quien
         * va
         */
        Booking previousBooking = bookingMap.get(dni);

        if (groupGuests.size() == previousBooking.getNumberOfGuests()){
            createRegister(previousBooking.getReserviourName(), previousBooking.getReserviourDNI(),
            previousBooking.getReserviourEmail(), previousBooking.getReserviourPhone(), groupGuests, previousBooking.getReservedRoomsIds());
        }
        else
            throw new Exception(
                    String.format("La reserva que existe, es para %d personas, el grupo contiene %d personas",
                            previousBooking.getNumberOfGuests(), groupGuests.size()));

    }

    public PrincipalGuest getResponsibleData() {
        return this.responsibleGuest;

    }

    public Group getGroupData() {
        return this.group;
    }

    public void roomAsigner(List<String> selectedRooms, HashMap<String, Room> availableRooms) {



    }

    public void closeRegistration() {

    }

    private void saveRegistration(Map<String, Registration> registrationMap, String dni) {
        registrationMap.put(dni, this.openRegister);
    }

}
