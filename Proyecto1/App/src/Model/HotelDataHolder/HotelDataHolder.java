package Model.HotelDataHolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import Model.HotelObjects.HotelObject;

public abstract class HotelDataHolder<HotelObj extends HotelObject> {
    Map<String, HotelObj> dataHolder;
    File dataFile;

    public abstract void loadPersistentData(File dataFile);

    public abstract void objectCreator();

    public void SavePersistentData(File dataFile) throws JSONException, IOException {
        /*
         * Guarda en un archivo un objeto json con los pares llave valor, donde la llave
         * es el identificador del objeto (habitacion, tarifa, reserva, etc)
         * y el objeto, el retorno de el metodo getJsonObject
         * <br>
         * 
         * <b>pre:<b>
         * 1. El mapa ya debe estar inicializado
         * 2. El archivo existe en App/Data
         * <b>post:<b> El archivo <<objetoDeHotel>>.json va a tener todos los objetos de
         * dataHolder
         * 
         * @param dataFile Archivo donde se va a guardar la información de los objetos
         * 
         */
        JSONObject jsonObj = new JSONObject();

        for (Map.Entry<String, HotelObj> jsonElementEntry : dataHolder.entrySet())
            jsonObj.put(jsonElementEntry.getKey(), jsonElementEntry.getValue().getJsonObject());

        FileWriter fileReader = new FileWriter(dataFile);
        fileReader.write(jsonObj.toString());
        fileReader.close();
    }
}
