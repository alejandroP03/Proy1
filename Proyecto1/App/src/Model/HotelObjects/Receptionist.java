package Model.HotelObjects;

import org.json.simple.JSONObject;

import java.util.Map;
import java.util.HashMap;

public class Receptionist implements User {
    private String userName;
    private String password;
    private UserType userType;

    public Receptionist(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.userType = UserType.RECEPTIONIST;

    }


    @Override
    public JSONObject getJsonObject() {
        Map<Object,Object> receptionistData = new HashMap<Object,Object>();
        receptionistData.put("userName",this.userName);
        receptionistData.put("password",this.password);
        receptionistData.put("userType",this.userType.toString());



        return new JSONObject(receptionistData);
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
