package Model.HotelObjects;

public interface User extends HotelObject {
    public String getUserName();

    public String getPassword();

    public void setUserName(String userName);

    public void setPassword(String password);

}
