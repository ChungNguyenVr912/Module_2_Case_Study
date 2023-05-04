package utils;

import entity.Flight;

import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight> {
    private static final FlightPriceComparator instance = new FlightPriceComparator();

    private FlightPriceComparator() {
    }

    public static FlightPriceComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(Flight flight1, Flight flight2) {
        return (int) (flight1.getBasePrice() - flight2.getBasePrice());
    }
}
