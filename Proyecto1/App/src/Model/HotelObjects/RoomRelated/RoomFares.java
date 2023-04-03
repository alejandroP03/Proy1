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
        ArrayList<Fare> sortedFares = new ArrayList<Fare>(faresForRoomType);
        sortedFares.sort((Fare fare1, Fare fare2) -> fare1.getInitialDate().compareTo(fare2.getInitialDate()));

        ArrayList<Fare> newFares = new ArrayList<Fare>();
        ArrayList<Integer> removeFares = new ArrayList<Integer>();

        if (sortedFares.size() > 0) {
            if (newFare.getInitialDate().isBefore(sortedFares.get(0).getInitialDate())) {
                newFares.add(new Fare(newFare.getPrice(), newFare.getInitialDate(),
                        sortedFares.get(0).getInitialDate().minusDays(1), newFare.getDays()));
                newFare = new Fare(newFare.getPrice(), sortedFares.get(0).getInitialDate(), newFare.getFinalDate(),
                        newFare.getDays());

            }
        }

        for (int i = 0; i < sortedFares.size(); i++) {
            if (newFare.getInitialDate().compareTo(newFare.getFinalDate()) > 0) {
                break;
            }

            Fare fare = sortedFares.get(i);

            if (fare.getInitialDate().compareTo(newFare.getInitialDate()) <= 0
                    && fare.getFinalDate().compareTo(newFare.getInitialDate()) >= 0) {

                if (!fare.getInitialDate().isEqual(newFare.getInitialDate())) {
                    newFares.add(
                            new Fare(fare.getPrice(), fare.getInitialDate(), newFare.getInitialDate().minusDays(1),
                                    fare.getDays()));
                }
                if (newFare.getFinalDate().compareTo(fare.getFinalDate()) < 0) {
                    newFares.add(new Fare(fare.getPrice(), newFare.getFinalDate().plusDays(1), fare.getFinalDate(),
                            fare.getDays()));
                }
                newFares.addAll(getMinFares(fare, newFare));
                newFare = new Fare(newFare.getPrice(), fare.getFinalDate().plusDays(1), newFare.getFinalDate(),
                        newFare.getDays());
                removeFares.add(i);
            } else if (fare.getInitialDate().compareTo(newFare.getFinalDate()) <= 0
                    && fare.getFinalDate().compareTo(newFare.getFinalDate()) >= 0) {

                if (!fare.getFinalDate().isEqual(newFare.getFinalDate())) {
                    newFares.add(new Fare(fare.getPrice(), newFare.getFinalDate().plusDays(1), fare.getFinalDate(),
                            fare.getDays()));

                }
                newFares.addAll(getMinFares(fare, newFare));
                newFare = new Fare(newFare.getPrice(), fare.getFinalDate().plusDays(1), newFare.getFinalDate(),
                        newFare.getDays());
                removeFares.add(i);

            }

        }

        if (newFare.getInitialDate().compareTo(newFare.getFinalDate()) < 0) {
            newFares.add(newFare);
        }

        for (int i = removeFares.size() - 1; i >= 0; i--) {
            sortedFares.remove((int) removeFares.get(i));
        }

        sortedFares.addAll(newFares);

        this.faresForRoomType = sortedFares;

    }

    /*
     * Recibe dos tarifas y para el mismo rango de tiempo retorna las tarifas
     * correspondientes a los dias de la semana
     */
    private ArrayList<Fare> getMinFares(Fare fare, Fare newFare) {
        float minPrice;
        float badPrice;
        Fare badFareInter;
        Fare minFareInter;
        LocalDate initialDateInter = fare.getInitialDate().compareTo(newFare.getInitialDate()) > 0
                ? fare.getInitialDate()
                : newFare.getInitialDate();
        LocalDate finalDateInter = fare.getFinalDate().compareTo(newFare.getFinalDate()) < 0 ? fare.getFinalDate()
                : newFare.getFinalDate();

        ArrayList<DayOfWeek> interceptionDays = getInterceptionDays(fare, newFare);
        ArrayList<DayOfWeek> nonCoverDays = new ArrayList<DayOfWeek>();
        if (fare.getPrice() < newFare.getPrice()) {
            minPrice = fare.getPrice();
            badPrice = newFare.getPrice();
            interceptionDays.addAll(fare.getDays());
            interceptionDays = new ArrayList<DayOfWeek>(new HashSet<DayOfWeek>(interceptionDays));

            for (DayOfWeek day : newFare.getDays()) {
                if (!interceptionDays.contains(day)) {
                    nonCoverDays.add(day);
                }
            }
            badFareInter = new Fare(badPrice, initialDateInter, finalDateInter, nonCoverDays);
            minFareInter = new Fare(minPrice, initialDateInter, finalDateInter, interceptionDays);

        } else {
            minPrice = newFare.getPrice();
            badPrice = fare.getPrice();
            interceptionDays.addAll(newFare.getDays());
            interceptionDays = new ArrayList<DayOfWeek>(new HashSet<DayOfWeek>(interceptionDays));
            for (DayOfWeek day : fare.getDays()) {
                if (!interceptionDays.contains(day)) {
                    nonCoverDays.add(day);
                }
            }
            badFareInter = new Fare(badPrice, initialDateInter, finalDateInter, nonCoverDays);
            minFareInter = new Fare(minPrice, initialDateInter, finalDateInter, interceptionDays);
        }

        if (nonCoverDays.size() == 0) {
            return new ArrayList<Fare>(Set.of(minFareInter));
        } else {
            return new ArrayList<Fare>(Set.of(minFareInter, badFareInter));
        }
    }

    private ArrayList<DayOfWeek> getInterceptionDays(Fare fare1, Fare fare2) {
        ArrayList<DayOfWeek> interceptionDays = new ArrayList<DayOfWeek>();
        for (DayOfWeek day : fare1.getDays()) {
            if (fare2.getDays().contains(day)) {
                interceptionDays.add(day);
            }
        }
        return interceptionDays;
    }

    private LocalDate minDate(LocalDate date1, LocalDate date2) {
        if (date1.compareTo(date2) <= 0) {
            return date1;
        } else {
            return date2;
        }
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