package Model.HotelObjects.RoomRelated;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import Model.HotelDataHolder.FaresDataHandler;
import Model.HotelObjects.HotelObject;

public class RoomFares implements HotelObject {
    private ArrayList<Fare> faresForRoomType;
    private Set<Object> typeRoomFare;

    public RoomFares(Set<Object> typeRoomFare) {
        this.faresForRoomType = new ArrayList<Fare>();
        this.typeRoomFare = typeRoomFare;
    }

    public void addFare(Fare fare) {
        this.faresForRoomType.add(fare);
    }

    public Set<Object> getTypeRoomFare() {
        return typeRoomFare;
    }

    public ArrayList<Fare> getFaresForRoomType() {
        return faresForRoomType;
    }

    public Integer getFare(LocalDate initialDate, LocalDate finalDate) throws Exception {
        /*
         * Retorna la tarifa completa de los dias de estadía
         *
         * <b> pre: </b> <br>
         * - La lista no debe estar vacía
         * - La lista debe contener las fechas que cubren la estadía
         * - Para todos los días de la semana de la estadía debe existir una tarifa que
         * lo cubra
         * <br>
         * 
         * @params initialDate Dia de inicio de la estadía
         * 
         * @params finalDate Dia en el que termina la estadía
         *
         * @throws Exception Las tarifas en el rango de la estadía están mal
         * configuradas
         *
         */
        int fare = 0;
        ArrayList<Fare> sortedFares = this.faresForRoomType;
        Collections.sort(sortedFares, new FareComparator());
        int initialIndex = searchIndexFloorInitialDate(sortedFares, initialDate);
        LocalDate actualDate = initialDate;
        while (actualDate.compareTo(finalDate) <= 0 && initialIndex < sortedFares.size()) {
            Fare fareDate = sortedFares.get(initialIndex);
            // Revisa si esta dentro del rango
            if (fareDate.getInitialDate().compareTo(actualDate) <= 0
                    && fareDate.getFinalDate().compareTo(actualDate) >= 0) {
                // Revisa si el dia hace parte de la tarifa
                if (fareDate.getDays().contains(actualDate.getDayOfWeek())) {
                    fare += fareDate.getPrice();
                    actualDate = actualDate.plusDays(1);
                } else
                    ++initialIndex;
            } else
                ++initialIndex;

        }

        if (fare != 0)
            return fare;
        else
            throw new Exception(String.format("La tarifa entre %s y %s está mal configurada", initialDate.toString(),
                    finalDate.toString()));
    }

    private int searchIndexFloorInitialDate(ArrayList<Fare> fareList, LocalDate initialDate) {
        /*
         * Retorna el índice de la fecha estrictamente menor a la fecha dada
         */

        int mid;
        int min = 0;
        int max = fareList.size();

        while (min < max) {
            mid = (max + min) / 2;
            if (fareList.get(mid).getInitialDate().compareTo(initialDate) < 0)
                min = mid;
            else
                max = mid;
        }

        int ind = (max + min) / 2;

        // Como retorna el valor estrictamente menor, si el siguiente elemento es la
        // fecha exacta se retorna esa fecha, si no, la anterior
        return fareList.get(ind + 1).getInitialDate().equals(initialDate) ? ind + 1 : ind;
    }

    private class FareComparator implements Comparator<Fare> {
        @Override
        public int compare(Fare fare1, Fare fare2) {
            return fare1.getInitialDate().compareTo(fare2.getInitialDate());
        }
    }

    public JSONObject getJsonObject(){
        Map<Object, Object> roomFareData = new HashMap<Object, Object>();

        ArrayList<JSONObject> fareJsonObjects = new ArrayList<JSONObject>();

        for (Fare fare : this.getFaresForRoomType()) {
            fareJsonObjects.add(fare.getJsonObject());
        }
        roomFareData.put("fares", fareJsonObjects);

        JSONObject roomFareObject = new JSONObject(roomFareData);
        System.out.println(roomFareObject);
        return roomFareObject;
}

    public static void main(String[] args) throws Exception {

        Room room = new Room("null", "null", false, new HashMap<Bed, Integer>(Map.of(Bed.CABIN, 1, Bed.KING, 2)),
                new HashSet<RoomFeatures>(Arrays.asList(RoomFeatures.BALCONY, RoomFeatures.LANDSCAPE_VIEW)),
                TypeRoom.STANDARD);

        FaresDataHandler faredh = new FaresDataHandler(new File("App/data/room_fares.json"));

        faredh.loadPersistentData();

        faredh.FareCreator(room.createTypeRoomId(), 120000, LocalDate.of(2023, Month.AUGUST, 30), LocalDate.of(2023, Month.OCTOBER, 30),
        new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)));

        faredh.FareCreator(room.createTypeRoomId(), 150000, LocalDate.of(2024, Month.AUGUST, 30), LocalDate.of(2025, Month.OCTOBER, 30),
        new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.SUNDAY)));
        faredh.SavePersistentData();
    }

}