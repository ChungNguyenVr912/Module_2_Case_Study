package utils;

import entity.Flight;

import java.util.Comparator;

public class FlightAirlinesComparator implements Comparator<Flight> {
    private static final FlightAirlinesComparator instance = new FlightAirlinesComparator();
    private FlightAirlinesComparator(){}

    public static FlightAirlinesComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(Flight flight1, Flight flight2) {
        return flight1.getProviderName().compareTo(flight2.getProviderName());
    }
}
