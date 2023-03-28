package Model.HotelObjects;

import Model.HotelObjects.User;
import org.json.simple.JSONObject;

public class Employee implements User {
    private String userName;
    private String password;
    private UserType userType;

    public Employee(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.userType = UserType.EMPLOYEE;
    }

    @Override
    public JSONObject getJsonObject() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setUserName() {

    }

    @Override
    public void setPassword() {

    }
}
