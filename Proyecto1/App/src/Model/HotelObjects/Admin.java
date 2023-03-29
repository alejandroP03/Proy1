package Model.HotelObjects;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Admin implements User{
    private String userName;
    private String password;
    private UserType userType;

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userType = UserType.ADMIN;
    }

    @Override
    public JSONObject getJsonObject() {
        Map<Object, Object> adminData = new HashMap<Object, Object>();
        adminData.put("userName", this.userName);
        adminData.put("password", this.password);
        adminData.put("userType", this.userType.toString());

        return new JSONObject(adminData);
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
    public UserType getUserType() {
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
