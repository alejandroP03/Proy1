package Model.HotelDataHolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public abstract class HotelDataHolder<HotelObj extends HotelObject> {
    private Map<Object, HotelObj> dataHolder;
    private File jSONDataFile;
    private boolean isFileLoaded;

    public HotelDataHolder(File jSONDataFile) {
        this.dataHolder = new HashMap<Object, HotelObj>();
        this.jSONDataFile = jSONDataFile;
        this.isFileLoaded = false;
    }

    public abstract void loadPersistentData() throws Exception;

    public void SavePersistentData() throws IOException {
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
         * dataHolder (el archivo se va a sobreescribir)
         * 
         * @param jSONDataFile Archivo donde se va a guardar la información de los
         * objetos
         * 
         */

        HashMap<Object, Object> objData = new HashMap<Object, Object>();

        for (Map.Entry<Object, HotelObj> jsonElementEntry : this.dataHolder.entrySet())

            objData.put(jsonElementEntry.getKey(), jsonElementEntry.getValue().getJsonObject());

        FileWriter fileReader = new FileWriter(this.jSONDataFile);
        fileReader.write(JSONObject.toJSONString(objData));
        fileReader.close();
    }

    public Map<Object, HotelObj> getData() {
        return dataHolder;
    }

    public void setDataHolder(Map<Object, HotelObj> dataHolder) {
        this.dataHolder = dataHolder;
    }

    public File getjSONDataFile() {
        return jSONDataFile;
    }

    public void setFileLoaded(boolean isFileLoaded) {
        this.isFileLoaded = isFileLoaded;
    }

    public boolean
    getIsFileLoaded() {
        return isFileLoaded;
    }
}