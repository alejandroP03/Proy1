package Model.HotelObjects.RoomRelated;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public void addFare(Fare fare) {
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
         * <b> post: </b> <br>
         * fare se agrega a daresForRoomType
         * 
         * @param fare tarifa a ingresar
         * 
         */

        ArrayList<Fare> faresToAdd = new ArrayList<Fare>();
        // Como una tarifa puede bajar varias entonces toca crear una cola de tarifas
        // que se
        ArrayList<Fare> faresQuee = new ArrayList<Fare>();
        faresQuee.add(fare);
        Fare fareBase = faresQuee.remove(0);
        while (faresQuee.size() > 0 || fareBase != null) {

            int ind = 0;
            while (ind < faresForRoomType.size() && fareBase != null) {
                Fare fareFloor = faresForRoomType.get(ind);
                if (fareFloor.getInitialDate().compareTo(fareBase.getInitialDate()) <= 0) {
                    // No se solapan
                    if (fareFloor.getFinalDate().compareTo(fareBase.getInitialDate()) < 0) {
                        faresToAdd.add(fareFloor);
                        faresToAdd.add(fareBase);
                    } else {

                        if (fareFloor.getFinalDate().compareTo(fareBase.getFinalDate()) < 0) {
                            // Como la tarifa no acaba antes de que empiece la nueva tarifa se divide en dos
                            // tarifas
                            // de lo contrario la segunda es null y se cierra el ciclo

                            if (fareFloor.getFinalDate().plusDays(1).isBefore(fareBase.getFinalDate())) {
                                faresQuee.add(new Fare(fareBase.getPrice(), fareFloor.getFinalDate().plusDays(1),
                                        fareBase.getFinalDate(), fareBase.getDays()));
                            }

                            fareBase = new Fare(fareBase.getPrice(), fareBase.getInitialDate(),
                                    fareFloor.getFinalDate(), fareBase.getDays());
                        }
                        if (isNotIntersectingDays(fareFloor, fareBase)) {
                            faresToAdd.add(fareFloor);
                            faresToAdd.add(fareBase);
                        } else if (isFareFloorMinPrice(fareFloor, fareBase)) {
                            faresToAdd.add(fareFloor);

                        } else {
                            if (fareFloor.getInitialDate().compareTo(fareBase.getInitialDate()) != 0) {
                                // Comoo fareFloor tiene una fecha inicial menor a fareBase se crea una tarifa
                                // que cubra este rango
                                Fare fareToAdd = new Fare(fareFloor.getPrice(), fareFloor.getInitialDate(),
                                        fareBase.getInitialDate().minusDays(1), fareFloor.getDays());
                                faresToAdd.add(fareToAdd);
                            }
                            // Tarifa menor entre las dos
                            Fare minFare = getMinPriceFare(fareFloor, fareBase);
                            // Toca volverlo un set porque hay un sideeffect raro y ya estamos sobre el
                            // tiempo
                            Set<DayOfWeek> daysOfFareBase = new HashSet<DayOfWeek>(fareBase.getDays());
                            daysOfFareBase.addAll(getIntersectionDays(fareFloor, fareBase));
                            Fare fareToAdd2 = new Fare(minFare.getPrice(), fareBase.getInitialDate(),
                                    fareBase.getFinalDate(), new ArrayList<DayOfWeek>(daysOfFareBase));
                            faresToAdd.add(fareToAdd2);

                            // Tarifa con los dias de la semana que no estan cubiertos por fareBase
                            Fare fareToAdd3 = getFaresForNonCoverDays(fareFloor, fareBase.getDays(), fareBase);
                            if (fareToAdd3 != null)
                                faresToAdd.add(fareToAdd3);
                            if (fareFloor.getFinalDate().compareTo(fareBase.getFinalDate()) != 0) {
                                // Como fareFloor tiene una fecha final mayor a fareBase se crea una tarifa
                                // que cubra este rango
                                Fare fareToAdd4 = new Fare(fareFloor.getPrice(), fareBase.getFinalDate().plusDays(1),
                                        fareFloor.getFinalDate(), fareFloor.getDays());
                                faresToAdd.add(fareToAdd4);
                            }
                        }
                    }
                    faresForRoomType.remove(ind);
                }
                ind++;

            }
            // La lista estaba vacía
            if (ind == 0) {
                faresToAdd.add(fareBase);
            }
            faresForRoomType.addAll(faresToAdd);
            faresToAdd.clear();
            if (faresQuee.size() > 0)
            fareBase = faresQuee.remove(0);
            else
                fareBase = null;
        }
    }

    /*
     *
     *
     *
     */
    private Fare getFaresForNonCoverDays(Fare fareFloor, ArrayList<DayOfWeek> daysCovered, Fare fareBase) {
        ArrayList<DayOfWeek> daysToAdd = new ArrayList<DayOfWeek>();
        for (DayOfWeek day : fareFloor.getDays()) {
            if (!daysCovered.contains(day)) {
                daysToAdd.add(day);
            }
        }
        if (daysToAdd.size() > 0) {
            Fare fareToAdd = new Fare(fareFloor.getPrice(), fareBase.getInitialDate(), fareBase.getFinalDate(),
                    daysToAdd);
            return fareToAdd;
        }
        return null;
    }

    private boolean isNotIntersectingDays(Fare fare1, Fare fare2) {
        for (DayOfWeek day : fare1.getDays()) {
            if (fare2.getDays().contains(day)) {
                return false;
            }
        }
        return true;
    }

    private Fare getMinPriceFare(Fare fare1, Fare fare2) {
        return fare1.getPrice() <= fare2.getPrice() ? fare1 : fare2;
    }

    private boolean isFareFloorMinPrice(Fare fareFloor, Fare fareBase) {
        return fareFloor.getPrice() <= fareBase.getPrice();
    }

    private ArrayList<DayOfWeek> getIntersectionDays(Fare fare1, Fare fare2) {
        ArrayList<DayOfWeek> intersectionDays = new ArrayList<DayOfWeek>();
        for (DayOfWeek day : fare1.getDays()) {
            if (fare2.getDays().contains(day)) {
                intersectionDays.add(day);
            }
        }
        return intersectionDays;
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


        double fare = 0;
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
                min = mid + 1;
            else
                max = mid - 1;
        }
        int ind = (max + min) / 2;

        // Como retorna el valor estrictamente menor, si el siguiente elemento es la
        // fecha exacta se retorna esa fecha, si no, la anterior
        return ind + 1 > fareList.size() ? ind - 1
                : fareList.get(ind + 1).getInitialDate().equals(initialDate) ? ind + 1 : ind;
    }

    private class FareComparator implements Comparator<Fare> {
        @Override
        public int compare(Fare fare1, Fare fare2) {
            return fare1.getInitialDate().compareTo(fare2.getInitialDate());
        }
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