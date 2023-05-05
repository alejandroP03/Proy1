package Model.HotelObjects.RoomRelated;

public enum Bed {
    KING_PLUS(4, "King plus"),
    KING(3, "King"),
    QUEEN(3, "Queen"),
    DOUBLE(2, "Doble"),
    SINGLE(1, "Sencilla"),
    CABIN(2, "Camarote"),
    KIDS(1, "Niños");

    private final int peoplePerBed;
    private final String bedName;

    public int getBedSize() {
        return peoplePerBed;
    }

    public String getBedName() {
        return bedName;
    }

    private Bed(int peoplePerBed, String bedName) {
        this.peoplePerBed = peoplePerBed;
        this.bedName = bedName;
    }
}