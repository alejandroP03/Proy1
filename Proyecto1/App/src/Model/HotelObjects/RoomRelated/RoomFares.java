package Model.HotelObjects.RoomRelated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONObject;

import Model.HotelObjects.HotelObject;

public class RoomFares implements HotelObject {
    private ArrayList<Fare> FaresForRoomType;

    public ArrayList<Fare> getFaresForRoomType() {
        return FaresForRoomType;
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
        ArrayList<Fare> sortedFares = this.FaresForRoomType;
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

    public JSONObject getJsonObject() {
        return null;
    }

    public static void main(String[] args) {

        int fareList[] = { 1, 3, 5, 6, 7, 9, 11, 15 };
        int initialDate = 11;
        int mid;
        int min = 0;
        int max = fareList.length;

        while (min < max) {
            mid = (max + min) / 2;
            if (fareList[mid] < initialDate)
                min = mid + 1;
            else
                max = mid - 1;
        }

        System.out.println((max + min) / 2);
    }
}