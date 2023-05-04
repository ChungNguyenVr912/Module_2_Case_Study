package utils;

import entity.Flight;

import java.util.Comparator;

public class FlightDepartTimeComparator implements Comparator<Flight> {
    private static final FlightDepartTimeComparator instance = new FlightDepartTimeComparator();
    private FlightDepartTimeComparator (){}

    public static FlightDepartTimeComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(Flight flight1, Flight flight2) {
        return flight1.getDepartTime().compareTo(flight2.getDepartTime());
    }
}
