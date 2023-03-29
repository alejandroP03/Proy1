package Controller.RegisterHandler;

import java.util.ArrayList;
import java.util.HashMap;

import Controller.Hotel;
import Model.HotelObjects.RoomRelated.Room;

public class RegisterHandler {
    // attributes

    private Hotel availableRooms;

    private Group group;
    private PrincipalGuest responsibleGuest;
    private boolean hasBooking;

    // methods
    public void createRegister(String name,
                               String dni,
                               String email,
                               String phoneNumber,
                               ArrayList<CompanionGuest> group) {
        // crea instncia del prinicpalGuest
        this.responsibleGuest = new PrincipalGuest(name, dni, email, phoneNumber);
        // crea la instancia de group
        this.group = new Group(group);

    }

    private void getAsociatedBooking() {
        // buscar en hotel
    }

    public PrincipalGuest getResponsibleData() {
        return this.responsibleGuest;

    }

    public Group getGroupData() {
        return this.group;
    }

    public void roomAsigner(HashMap<String, Room> availableRooms) {


    }

    public void closeRegistration() {

    }

    private void saveRegistration() {

    }

}
