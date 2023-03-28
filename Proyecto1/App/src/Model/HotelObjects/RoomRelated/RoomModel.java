package Model.HotelObjects.RoomRelated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomModel {
    protected TypeRoom type;
    protected Map<Bed, Integer> beds;
    protected Set<RoomFeatures> featuresList;

    public RoomModel(TypeRoom type, Map<Bed, Integer> beds, Set<RoomFeatures> featuresList) {
        this.type = type;
        this.beds = beds;
        this.featuresList = featuresList;
    }

    public Set<Object> createTypeRoomId() {
        Set<Object> roomId = new HashSet<Object>();
        roomId.add(this.type);

        Map<Bed, Integer> bedComposition = new HashMap<Bed, Integer>();
        for (Map.Entry<Bed, Integer> bedEntry : this.beds.entrySet()) {
            bedComposition.put(bedEntry.getKey(), bedEntry.getValue());
        }

        roomId.add(bedComposition);

        for (RoomFeatures features : this.featuresList) {
            roomId.add(features);
        }

        return roomId;
    }
}
