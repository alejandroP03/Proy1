package Controller.RegisterHandler;

import java.util.ArrayList;
import java.util.List;

public class Group {
    //attributes
    private ArrayList<CompanionGuest> groupGuests;

    //Constructor
    public Group(ArrayList<CompanionGuest> groupGuests){
        this.groupGuests = groupGuests;
    }

    //methods
    public void addCompanionGuest(CompanionGuest companionGuest){

        this.groupGuests.add(companionGuest);

    }

}
