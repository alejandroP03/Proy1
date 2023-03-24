package Controller.RegisterHandler;

import java.util.ArrayList;

public class Group {
    // attributes
    private ArrayList<CompanionGuest> groupGuests;
    private CompanionGuest companionGuest;

    // Constructor
    public Group(ArrayList<CompanionGuest> groupGuests) {
        this.groupGuests = groupGuests;
    }

    // methods
    public void addCompanionGuest(String name, String dni) {
        companionGuest = new CompanionGuest(name, dni);
        this.groupGuests.add(companionGuest);

    }

}
