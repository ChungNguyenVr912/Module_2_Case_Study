package services;

import builder.director.FlightDirector;
import entity.AirPlane;
import entity.Flight;
import entity.abstraction.Airlines;
import utils.DataReader;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class FlightService {
    private static final String FLIGHT_DATA_URL = "src/data/flight_history.dat";
    private static final String AIR_PORT_LIST_URL = "src/data/air_port_list.txt";
    private static List<Flight> flightList;
    private static HashMap<String,String> airPorts;

    static {
        flightList = DataReader.getFlightHistory(FLIGHT_DATA_URL);
        airPorts = DataReader.getAirPortsList(AIR_PORT_LIST_URL);
    }

    public static List<Flight> getFlightList() {
        return flightList;
    }

    public static HashMap<String, String> getAirPorts() {
        return airPorts;
    }

    public static Flight createNewFlight(Airlines provider, AirPlane airPlane, String departure, String destination
            , LocalDateTime departTime, LocalDateTime arrivalTime, String crewInfo, double basePrice) {
        return FlightDirector
                .buildFlight(provider, airPlane, departure, destination, departTime, arrivalTime, crewInfo, basePrice);
    }
}
