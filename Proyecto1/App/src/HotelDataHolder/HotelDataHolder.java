package HotelDataHolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class HotelDataHolder {
    Map<String, Object> dataHolder;
    Map<String, JSONObject> jsonDataHolder;
    File dataFile;

    HotelDataHolder(HashMap<String, Object> dataHolder){
        this.dataHolder = dataHolder;
    }

    public abstract void loadPersistentData(File dataFile);
    public abstract void objectCreator();
    
    public void SavePersistentData(File dataFile) throws JSONException, IOException{
        JSONObject dataHolderJsonObject = new JSONObject();
        for (Map.Entry<String, JSONObject> jsonElementEntry : jsonDataHolder.entrySet()) {
            dataHolderJsonObject.put(jsonElementEntry.getKey(), jsonElementEntry.getValue());
        }

        FileWriter fileReader = new FileWriter(dataFile);
        fileReader.write(dataHolderJsonObject.toString());
        fileReader.close();
    }   
}
