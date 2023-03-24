package Controller.RegisterHandler;

import java.util.List;

public class CompanionGuest implements Guest {
    //attributes
    private String name;
    private String dni;

    //Constructor
    public CompanionGuest(String name, String dni ){
        this.name = name;
        this.dni = dni;

    }

    //methods

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDni() {
        return this.dni;
    }


}



