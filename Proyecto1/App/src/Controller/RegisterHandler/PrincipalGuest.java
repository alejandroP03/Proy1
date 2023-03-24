package Controller.RegisterHandler;

public class PrincipalGuest implements Guest {

    // attributes

    private String name;
    private String dni;
    private String email;
    private String phoneNumber;

    // constructor

    public PrincipalGuest(String name, String dni, String email, String phoneNumber) {
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // methods

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDni() {
        return this.dni;
    }
}
