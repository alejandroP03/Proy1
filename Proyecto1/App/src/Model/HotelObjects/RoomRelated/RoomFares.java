package Model.HotelObjects.RoomRelated;

import java.time.DayOfWeek;
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
        * Este código puede mejorarse mucho, pero por ahora funciona y son las 3:00 am :(    
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
        // Verificar que la tarifa no es para un rango vacio
        if (fareBase.getInitialDate().compareTo(fareBase.getFinalDate()) <= 0) {

            ArrayList<Fare> faresToAdd = new ArrayList<Fare>();

            // Encontrar la tarifa menor o igual
            ArrayList<Fare> sortedFares = this.faresForRoomType;
            Collections.sort(sortedFares, new FareComparator());
            int fareInd = this.searchIndexFloorInitialDate(sortedFares, fareBase.getInitialDate());

            // La tarifa que mas se le acerca (por debajo)
            Fare fareFloor = faresForRoomType.get(fareInd);
            faresForRoomType.remove(fareInd);

            // Si no hay una tarifa menor se debe agregar la tarifa desde la fecha inicial
            // hasta antes de la intersección con fareFloor

            // Casos posibles
            // fareFloor inicia despues de que fareBase termina (No hay intersección)
            // farebase empieza despues de que fareFloor termine (No hay intersección ya que
            // fareBase es la ultima tarifa)

            // farebase inicia antes de que fareFloor inicie (En este caso, se divide la
            // tarifa en dos, se agrega la que no se intersecta y se revisa con la otra, que
            // ya va a ser uno de los casos siguientes)

            // fareBase inicia al mismo tiempo que fareFloor inicia
            // Subcaso 1: fareBase termina al tiempo que fareFLoor
            // Subcaso 2: fareBase termina antes de que fareFloor termine
            // Subcaso 3: fareBase termina despues de que fareFloor termine

            // fareBase inicia despues de que fareFloor inicie
            // Subcaso 1: fareBase termina al tiempo que fareFLoor
            // Subcaso 2: fareBase termina antes de que fareFloor termine
            // Subcaso 3: fareBase termina despues de que fareFloor termine

            // Se debe tener en cuenta que solo hay interseccion si se cruzan las tarifas
            // para los mismos dias de la semana

            if (fareFloor.getInitialDate().compareTo(fareBase.getFinalDate()) > 0) {
                // No hay intersección
                faresToAdd.add(fareBase);
            }

            if (fareBase.getInitialDate().compareTo(fareFloor.getFinalDate()) > 0) {
                // No hay intersección
                faresToAdd.add(fareBase);
            }

            if (fareBase.getInitialDate().isBefore(fareFloor.getInitialDate())) {
                Fare fareToAdd = new Fare(fareBase.getPrice(), fareBase.getInitialDate(),
                        getMinDate(fareBase.getFinalDate(), fareFloor.getInitialDate().minusDays(1)),
                        fareBase.getDays());
                faresToAdd.add(fareToAdd);
                fareBase = new Fare(fareBase.getPrice(), fareFloor.getInitialDate(), fareBase.getFinalDate(),
                        fareBase.getDays());
            }

            if (fareBase.getInitialDate().isEqual(fareFloor.getInitialDate())) {
                // Subcaso 1
                if (fareBase.getFinalDate().isEqual(fareFloor.getFinalDate())) {
                    Fare fareToAdd = new Fare(getMinPriceFare(fareBase, fareFloor).getPrice(),
                            fareBase.getInitialDate(),
                            fareBase.getFinalDate(), getIntersectionDays(fareBase, fareFloor));
                    faresToAdd.add(fareToAdd);
                    if (getIntersectionDays(fareFloor, fareToAdd) != fareFloor.getDays()) {
                        Fare fareToAdd2 = getFaresForNonCoverDays(fareFloor, getIntersectionDays(fareFloor, fareToAdd));
                        faresToAdd.add(fareToAdd2);
                    }

                }
                // Subcaso 2
                if (fareBase.getFinalDate().isBefore(fareFloor.getFinalDate())) {
                    Fare fareToAdd = new Fare(getMinPriceFare(fareBase, fareFloor).getPrice(),
                            fareBase.getInitialDate(),
                            fareBase.getFinalDate(), getIntersectionDays(fareBase, fareFloor));
                    faresToAdd.add(fareToAdd);

                    if (getIntersectionDays(fareFloor, fareToAdd) != fareFloor.getDays()) {
                        Fare fareToAdd2 = getFaresForNonCoverDays(fareFloor, getIntersectionDays(fareFloor, fareToAdd));
                        faresToAdd.add(fareToAdd2);
                    }

                    Fare fareToAdd3 = new Fare(fareFloor.getPrice(), fareBase.getFinalDate().plusDays(1),
                            fareFloor.getFinalDate(),
                            fareFloor.getDays());
                    faresToAdd.add(fareToAdd3);
                }
                // Subcaso 3
                if (fareBase.getFinalDate().isAfter(fareFloor.getFinalDate())) {
                    Fare fareToAdd = new Fare(getMinPriceFare(fareBase, fareFloor).getPrice(),
                            fareBase.getInitialDate(),
                            fareFloor.getFinalDate(), getIntersectionDays(fareBase, fareFloor));
                    faresToAdd.add(fareToAdd);

                    if (getIntersectionDays(fareFloor, fareToAdd) != fareFloor.getDays()) {
                        Fare fareToAdd2 = getFaresForNonCoverDays(fareFloor, getIntersectionDays(fareFloor, fareToAdd));
                        faresToAdd.add(fareToAdd2);
                    }

                    Fare fareToAdd3 = new Fare(fareBase.getPrice(), fareFloor.getFinalDate().plusDays(1),
                            fareBase.getFinalDate(),
                            fareBase.getDays());
                    addFare(fareToAdd3);
                }

            }
            if (fareBase.getInitialDate().isAfter(fareFloor.getInitialDate())) {
                // Subcaso 1
                if (fareBase.getFinalDate().isEqual(fareFloor.getFinalDate())) {
                    Fare fareToAdd = new Fare(getMinPriceFare(fareBase, fareFloor).getPrice(),
                            fareFloor.getInitialDate(),
                            fareFloor.getFinalDate(), getIntersectionDays(fareBase, fareFloor));
                    faresToAdd.add(fareToAdd);
                    if (getIntersectionDays(fareFloor, fareToAdd) != fareFloor.getDays()) {
                        Fare fareToAdd2 = getFaresForNonCoverDays(fareFloor, getIntersectionDays(fareFloor, fareToAdd));
                        faresToAdd.add(fareToAdd2);
                    }

                }
                // Subcaso 2
                if (fareBase.getFinalDate().isBefore(fareFloor.getFinalDate())) {
                    Fare fareToAdd = new Fare(getMinPriceFare(fareBase, fareFloor).getPrice(),
                            fareFloor.getInitialDate(),
                            fareBase.getFinalDate(), getIntersectionDays(fareBase, fareFloor));
                    faresToAdd.add(fareToAdd);

                    if (getIntersectionDays(fareFloor, fareToAdd) != fareFloor.getDays()) {
                        Fare fareToAdd2 = getFaresForNonCoverDays(fareFloor, getIntersectionDays(fareFloor, fareToAdd));
                        faresToAdd.add(fareToAdd2);
                    }

                    Fare fareToAdd3 = new Fare(fareFloor.getPrice(), fareBase.getFinalDate().plusDays(1),
                            fareFloor.getFinalDate(),
                            fareFloor.getDays());
                    faresToAdd.add(fareToAdd3);
                }
                // Subcaso 3
                if (fareBase.getFinalDate().isAfter(fareFloor.getFinalDate())) {
                    Fare fareToAdd = new Fare(getMinPriceFare(fareBase, fareFloor).getPrice(),
                            fareFloor.getInitialDate(),
                            fareFloor.getFinalDate(), getIntersectionDays(fareBase, fareFloor));
                    faresToAdd.add(fareToAdd);

                    if (getIntersectionDays(fareFloor, fareToAdd) != fareFloor.getDays()) {
                        Fare fareToAdd2 = getFaresForNonCoverDays(fareFloor, getIntersectionDays(fareFloor, fareToAdd));
                        faresToAdd.add(fareToAdd2);
                    }

                    Fare fareToAdd3 = new Fare(fareBase.getPrice(), fareFloor.getFinalDate().plusDays(1),
                            fareBase.getFinalDate(),
                            fareBase.getDays());
                    addFare(fareToAdd3);
                }
            }
        }
    }

    private Fare getFaresForNonCoverDays(Fare fareFloor, ArrayList<DayOfWeek> daysCovered) {
        ArrayList<DayOfWeek> daysToAdd = new ArrayList<DayOfWeek>();
        for (DayOfWeek day : fareFloor.getDays()) {
            if (!daysCovered.contains(day)) {
                daysToAdd.add(day);
            }
        }
        Fare fareToAdd = new Fare(fareFloor.getPrice(), fareFloor.getInitialDate(), fareFloor.getFinalDate(),
                daysToAdd);
        return fareToAdd;
    }

    private Fare getMinPriceFare(Fare fare1, Fare fare2) {
        return fare1.getPrice() <= fare2.getPrice() ? fare1 : fare2;
    }

    private LocalDate getMinDate(LocalDate date1, LocalDate date2) {
        return date1.compareTo(date2) <= 0 ? date1 : date2;
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