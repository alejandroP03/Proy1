package Model.HotelObjects;

import org.json.simple.JSONObject;

public interface HotelObject {
    /*
     * Esta función obtiene el JSONObject de cada objeto que implemente HotelObject.
     *
     * <b>pre: </b> Los atributos o datos que se metan al JSONObject ya deben estar inicializado. Es decir != null. <br>
     * <b>pos: </b> Se retorna el JSONObject con los respectivos datos de cada objeto.
     */
    public JSONObject getJsonObject();
}