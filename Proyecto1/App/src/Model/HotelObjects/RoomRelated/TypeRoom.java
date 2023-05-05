package Model.HotelObjects.RoomRelated;

public enum TypeRoom {
    STANDARD("Estandar"), SUITE("Suite"), DOUBLE_SUITE("Doble suite");

    private final String typeName;

    public String getTypeName() {
        return typeName;
    }

    private TypeRoom(String typeName) {
        this.typeName = typeName;
    }
}
