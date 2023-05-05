package Model.HotelObjects.RoomRelated;

public enum RoomFeatures {
    BALCONY ("Balcón"), LANDSCAPE_VIEW ("Vista al paisaje"), KITCHEN ("Cocina");


    private final String featureName;

    public String getFeatureName() {
        return featureName;
    }

    private RoomFeatures(String featureName) {
        this.featureName = featureName;
    }
}
