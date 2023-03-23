package Model.HotelObjects.RoomRelated;

public enum Bed {
    KING_PLUS(4),
    KING(3),
    QUEEN(3),
    DOUBLE(2),
    SINGLE(1),
    CABIN(2),
    KIDS(1);

    private final int peoplePerBed;

    public int getBedSize() {
        return peoplePerBed;
    }

    private Bed(int peoplePerBed) {
        this.peoplePerBed = peoplePerBed;
    }
}