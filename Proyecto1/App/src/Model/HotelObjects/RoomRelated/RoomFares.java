package Model.HotelObjects.RoomRelated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class RoomFares implements HotelObject {
    private ArrayList<Fare> faresForRoomType;
    private Set<Object> typeRoomFare;

    public RoomFares(Set<Object> typeRoomFare) {
        this.faresForRoomType = new ArrayList<Fare>();
        this.typeRoomFare = typeRoomFare;
    }
    
    public static void main(String[] args) {
        RoomModel roomModel = new RoomModel(TypeRoom.STANDARD, new HashMap<Bed, Integer>(Map.of(Bed.DOUBLE, 1)), new HashSet<RoomFeatures>());
        RoomFares roomFares = new RoomFares(roomModel.createTypeRoomId());
        roomFares.addFare(new Fare(10000, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 31), new ArrayList<DayOfWeek>(Set.of(DayOfWeek.MONDAY))));
        roomFares.addFare(new Fare(9000, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 31), new ArrayList<DayOfWeek>(Set.of(DayOfWeek.MONDAY))));
        roomFares.addFare(new Fare(8000, LocalDate.of(2019, 1, 1), LocalDate.of(2020, 1, 31), new ArrayList<DayOfWeek>(Set.of(DayOfWeek.MONDAY))));
        roomFares.addFare(new Fare(7000, LocalDate.of(2018, 1, 1), LocalDate.of(2021, 1, 31), new ArrayList<DayOfWeek>(Set.of(DayOfWeek.MONDAY))));
        System.out.println(roomFares.getJsonObject());
    }

    public void addFare(Fare newFare) {
        /*
         * Este código puede mejorarse mucho, pero por ahora funciona y son las 3:00 am
         * :(
         * Agrega una tarifa a la lista, si ya existe un valor para una fecha, se tomará
         * el valor menor y
         * se lanzará una excepción que indicará que se tomó el menor valor entre dos
         * tarifas
         * 
         * <b> pre: </b> <br>
         * faresForRoomType ya debe estar inicializado
         * faresForRoomType no tiene fechas que se solapen
         * <b> post: </b> <br>
         * fare se agrega a daresForRoomType
         * 
         * @param fare tarifa a ingresar
         * 
         */
        ArrayList<Fare> faresToAdd = new ArrayList<Fare>();
        ArrayList<Fare> intersectedFares = new ArrayList<Fare>();
        ArrayList<Integer> indexesToRemove = new ArrayList<Integer>();
        intersectedFares.add(newFare);
        for (int i = 0; i < faresForRoomType.size(); i++) {
            Fare fare = faresForRoomType.get(i);
            if (isIntersecting(fare, newFare)) {
                intersectedFares.add(fare);
                indexesToRemove.add(i);
            } else {
                faresToAdd.add(fare);
            }
        }

        for (int i = indexesToRemove.size() - 1; i >= 0; i--) {
            faresForRoomType.remove((int) indexesToRemove.get(i));
        }

        faresToAdd.addAll(getMinFares(intersectedFares, newFare));

        faresForRoomType.addAll(faresToAdd);

    }

    private ArrayList<Fare> getMinFares(ArrayList<Fare> intersectedFares, Fare newFare) {
        ArrayList<Fare> minFares = new ArrayList<Fare>();
        // sort by initial date
        intersectedFares.sort((Fare f1, Fare f2) -> f1.getInitialDate().compareTo(f2.getInitialDate()));

        for (Fare actualFare : intersectedFares) {
            Fare fare = actualFare;
            if (newFare.getInitialDate().isBefore(fare.getInitialDate())) {
                Fare fareToAdd = new Fare(newFare.getPrice(), newFare.getInitialDate(),
                        fare.getInitialDate().minusDays(1),
                        newFare.getDays());
                minFares.add(fareToAdd);
                newFare = new Fare(newFare.getPrice(), fare.getInitialDate(), newFare.getFinalDate(),
                        newFare.getDays());
            }
            if (fare.getInitialDate().isBefore(newFare.getInitialDate())) {
                Fare fareToAdd = new Fare(fare.getPrice(), fare.getInitialDate(), newFare.getInitialDate().minusDays(1),
                        fare.getDays());
                minFares.add(fareToAdd);
                fare = new Fare(fare.getPrice(), newFare.getInitialDate(), fare.getFinalDate(), fare.getDays());
            }
            if (newFare.getFinalDate().isBefore(fare.getFinalDate())) {
                Fare fareToAdd = new Fare(newFare.getPrice(), newFare.getFinalDate().plusDays(1), fare.getFinalDate(),
                        newFare.getDays());
                minFares.add(fareToAdd);
                newFare = new Fare(newFare.getPrice(), fare.getFinalDate(), newFare.getFinalDate(), newFare.getDays());
            }
            if (fare.getFinalDate().isBefore(newFare.getFinalDate())) {
                Fare fareToAdd = new Fare(fare.getPrice(), fare.getFinalDate().plusDays(1), newFare.getFinalDate(),
                        fare.getDays());
                minFares.add(fareToAdd);
                fare = new Fare(fare.getPrice(), newFare.getFinalDate(), fare.getFinalDate(), fare.getDays());
            }
            if (fare.getInitialDate().isBefore(fare.getFinalDate())
                    && newFare.getInitialDate().isBefore(newFare.getFinalDate())) {
                minFares.addAll(getIntersectionFares(fare, newFare));
            }
        }
        return minFares;

    }

    private ArrayList<Fare> getIntersectionFares(Fare fare1, Fare fare2) {
        ArrayList<Fare> intersectionFares = new ArrayList<Fare>();
        ArrayList<DayOfWeek> intersectionDays = new ArrayList<DayOfWeek>();
        for (DayOfWeek day : fare1.getDays()) {
            if (fare2.getDays().contains(day)) {
                intersectionDays.add(day);
            }
        }

        Fare badFare;
        if (fare1.getPrice() < fare2.getPrice()) {
            intersectionFares.add(fare1);
            badFare = fare2;
        } else {
            intersectionFares.add(fare2);
            badFare = fare1;
        }

        if (intersectionDays.size() < badFare.getDays().size()) {
            ArrayList<DayOfWeek> daysToAdd = new ArrayList<DayOfWeek>();
            for (DayOfWeek day : badFare.getDays()) {
                if (!intersectionDays.contains(day)) {
                    daysToAdd.add(day);
                }
            }
            Fare fareToAdd = new Fare(badFare.getPrice(), badFare.getInitialDate(), badFare.getFinalDate(), daysToAdd);
        }

        return intersectionFares;
    }

    private boolean isIntersecting(Fare fare1, Fare fare2) {
        if (fare1.getInitialDate().compareTo(fare2.getInitialDate()) <= 0) {
            if (fare1.getFinalDate().compareTo(fare2.getInitialDate()) >= 0) {
                return true;
            }
        } else {
            if (fare1.getInitialDate().compareTo(fare2.getFinalDate()) <= 0) {
                return true;
            }
        }
        return false;
    }

    public Set<Object> getTypeRoomFare() {
        return typeRoomFare;
    }

    public ArrayList<Fare> getFaresForRoomType() {
        return faresForRoomType;
    }

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
    public double getFare(LocalDate initialDate, LocalDate finalDate) throws Exception {

        double totalFare = 0;
        while (initialDate.compareTo(finalDate) <= 0) {
            Fare fare = getFareForDate(initialDate);
            if (fare == null) {
                throw new Exception("Las tarifas en el rango de la estadía están mal configuradas");
            }
            totalFare += fare.getPrice();
            initialDate = initialDate.plusDays(1);
        }

        return totalFare;
    }

    public Fare getFareForDate(LocalDate date) {
        for (Fare fare : faresForRoomType) {
            if (fare.getInitialDate().compareTo(date) <= 0 && fare.getFinalDate().compareTo(date) >= 0
                    && fare.getDays().contains(date.getDayOfWeek())) {
                return fare;
            }
        }
        return null;
    }

    public boolean hasFare(LocalDate initialDate, LocalDate finalDate) {
        while (initialDate.compareTo(finalDate) <= 0) {
            Fare fare = getFareForDate(initialDate);
            if (fare == null) {
                return false;
            }
            initialDate = initialDate.plusDays(1);
        }
        return true;
    }

    public JSONObject getJsonObject() {
        Map<Object, Object> roomFareData = new HashMap<Object, Object>();

        ArrayList<JSONObject> fareJsonObjects = new ArrayList<JSONObject>();

        for (Fare fare : this.getFaresForRoomType()) {
            fareJsonObjects.add(fare.getJsonObject());
        }
        roomFareData.put("fares", fareJsonObjects);

        JSONObject roomFareObject = new JSONObject(roomFareData);
        return roomFareObject;
    }

}