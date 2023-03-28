package Model.HotelObjects;

import Model.HotelObjects.User;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
        Map<Object,Object> employeeData = new HashMap<Object,Object>();
        employeeData.put("userName",this.userName);
        employeeData.put("password",this.password);
        employeeData.put("userType",this.userType.toString());



        return new JSONObject(employeeData);
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public UserType getUserType(){
        return this.userType;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;

    }

    @Override
    public void setPassword(String password) {
        this.password = password;

    }
}
