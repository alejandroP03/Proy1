package View;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Model.HotelDataHolder.FaresDataHandler;
import Model.HotelDataHolder.RoomsDataHandler;
import Model.HotelObjects.RoomRelated.Bed;
import Model.HotelObjects.RoomRelated.RoomFeatures;
import Model.HotelObjects.RoomRelated.RoomModel;
import Model.HotelObjects.RoomRelated.TypeRoom;

public class App {
    public static void main(String[] args) throws Exception {
        FaresDataHandler faresDH = new FaresDataHandler(new File("App/data/room_fares.json"));
        RoomsDataHandler roomDH = new RoomsDataHandler(new File("App/data/rooms.json"), faresDH.getData());
        faresDH.loadPersistentData();
        roomDH.loadPersistentData();
        

        RoomModel roomModel = new RoomModel(TypeRoom.STANDARD, new HashMap<Bed, Integer>(Map.of(Bed.KING_PLUS, 2)),
                new HashSet<RoomFeatures>(Arrays.asList(RoomFeatures.BALCONY, RoomFeatures.KITCHEN)));

        faresDH.FareCreator(roomModel.createTypeRoomId(), 180000, LocalDate.of(2022, 10, 20),
                LocalDate.of(2023, 10, 20),
                new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)));

        faresDH.FareCreator(roomModel.createTypeRoomId(), 180000, LocalDate.of(2022, 10, 20),
                LocalDate.of(2023, 10, 20),
                new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)));

        faresDH.FareCreator(roomModel.createTypeRoomId(), 180000, LocalDate.of(2022, 10, 20),
                LocalDate.of(2023, 10, 21),
                new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)));

        faresDH.FareCreator(roomModel.createTypeRoomId(), 190000, LocalDate.of(2022, 11, 25),
                LocalDate.of(2024, 10, 20),
                new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)));



        faresDH.SavePersistentData();
    }
}
