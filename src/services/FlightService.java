package services;

import entity.Flight;
import utils.DataReader;
import utils.DataWriter;
import utils.MyDateTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class FlightService {
    private static final String FLIGHT_DATA_URL = "src/data/flight_history.dat";
    private static final String AIR_PORT_LIST_URL = "src/data/air_port_list.txt";
    private static final String AIR_PORT_POS_URL = "src/data/airport_unreal_pos.txt";
    private static List<Flight> flightList;
    private static HashMap<String, String> airPorts;
    public static HashMap<String, Integer> airPortUnrealDistance;

    static {
        flightList = DataReader.getFlightList(FLIGHT_DATA_URL);
        airPorts = DataReader.getAirPortsList(AIR_PORT_LIST_URL);
        airPortUnrealDistance = DataReader.getAirPortPos(AIR_PORT_POS_URL);
    }

    public static void updateFlightList() {
        DataWriter.updateFlights(flightList, FLIGHT_DATA_URL);
    }

    public static List<Flight> getFlightList() {
        return flightList;
    }

    public static HashMap<String, String> getAirPorts() {
        return airPorts;
    }

    public static String getFlightDuration(LocalDateTime depart, LocalDateTime arrive) {
        Duration duration = Duration.between(depart, arrive);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    public static int getFlightDuration(String departure, String destination) {
        final float timeMulti = 3.85f;
        final int baseFlyTime = 45;// in minute
        int startPos = airPortUnrealDistance.get(departure);
        int endPos = airPortUnrealDistance.get(destination);
        int distance = Math.abs(startPos - endPos);
        return (int) (distance * timeMulti) - ((int) (distance * timeMulti)) % 5 + baseFlyTime;
    }

    public static String getFlightInfoByFlightCode(String flightCode) {
        for (Flight flight : flightList) {
            if (flight.getFlightCode().equals(flightCode)) {
                return String.format("%-14s", flightCode) + MyDateTime.toHourAndMinute(flight.getDepartTime())
                        + "\t" + flight.getDeparture() + "-" + flight.getDestination() + "\t"
                        + MyDateTime.toHourAndMinute(flight.getArrivalTime())
                        + "\nDate:" + MyDateTime.toDayMMMMYear(flight.getDepartTime());
            }
        }
        return "null";
    }
}
