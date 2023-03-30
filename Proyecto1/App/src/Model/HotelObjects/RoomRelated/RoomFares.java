package Model.HotelObjects.RoomRelated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

    public void addFare(Fare fareBase) {
        /*
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
        try {
            //TODO: Manejar los conflictos entre tarifas
            this.faresForRoomType.add(fareBase);

        } catch (Exception e){
            System.out.println(e);
        }

         /* 
        ArrayList<Fare> faresToAdd = new ArrayList<Fare>();

        // Encontrar la tarifa menor o igual
        ArrayList<Fare> sortedFares = this.faresForRoomType;
        Collections.sort(sortedFares, new FareComparator());
        int fareInd = this.searchIndexFloorInitialDate(sortedFares, fareBase.getInitialDate());

        // La tarifa que mas se le acerca (por debajo)
        Fare fareFloor = faresForRoomType.get(fareInd);

        // Si no hay una tarifa menor se debe agregar la tarifa desde la fecha inicial
        // hasta antes de la intersección con fareFloor

        if (fareBase.getInitialDate().isBefore(fareFloor.getInitialDate())) {

            // Si la tarifa se aplica en una fecha anterior a cualquier otra
            if (fareBase.getFinalDate().isBefore(fareFloor.getFinalDate())) {
                faresToAdd.add(fareBase);
            } else {
                Fare fareBefore = new Fare(fareBase.getPrice(), fareBase.getInitialDate(), fareFloor.getFinalDate(),
                        fareBase.getDays());
                faresToAdd.add(fareBefore);

                // Fecha intersección
                fareBase = new Fare(fareBase.getPrice(), fareFloor.getInitialDate(), fareBase.getFinalDate(),
                        fareBase.getDays());

                // La nueva fecha empieza despues de la ultima de fareFloor 
                if (fareBase.getInitialDate().isAfter(fareFloor.getFinalDate())) {
                    faresToAdd.add(fareBase);
                }
                // La nueva fecha inicia al mismo tiempo que otra 
                else if (fareBase.getInitialDate().isEqual(fareFloor.getInitialDate())) {
                    // La nueva fecha termina al mismo tiempo de fareFloor 
                    if (fareBase.getFinalDate().isEqual(fareFloor.getFinalDate())) {
                        

                    } // La nueva fecha termina antes de fareFloor 
                    else if (fareBase.getFinalDate().isBefore(fareFloor.getFinalDate())) {

                    } // La nueva fecha termina después de fareFloor 
                    else {

                    }
                    // La nueva fecha inicia después de fareFloor 
                } else if (fareBase.getInitialDate().isAfter(fareFloor.getInitialDate())) {
                    // La nueva fecha termina al mismo tiempo de fareFloor 
                    if (fareBase.getFinalDate().isEqual(fareFloor.getFinalDate())) {

                    } // La nueva fecha termina antes de fareFloor 
                    else if (fareBase.getFinalDate().isBefore(fareFloor.getFinalDate())) {

                    } // La nueva fecha termina después de fareFloor 
                    else {

                    }
                }
            }

        }*/

    }

    private Fare getMinPriceFare(Fare fare1, Fare fare2){
        return fare1.getPrice() <= fare2.getPrice() ? fare1 : fare2;
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

        // TODO: Probar si funciona cuando la primera tarifa es mayor que el initial
        // date aun asi sea la menor
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