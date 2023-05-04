package services;

import builder.director.FlightDirector;
import entity.Flight;
import entity.abstraction.Airlines;
import entity.abstraction.AirPlane;
import utils.DataReader;
import utils.DataWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class FlightService {
    private static final String FLIGHT_DATA_URL = "src/data/flight_history.dat";
    private static final String AIR_PORT_LIST_URL = "src/data/air_port_list.txt";
    private static final String AIR_PORT_POS_URL = "src/data/airport_unreal_pos.txt";
    private static List<Flight> flightList;
    private static HashMap<String,String> airPorts;
    public static HashMap<String, Integer> airPortUnrealDistance;

    static {
        flightList = DataReader.getFlightHistory(FLIGHT_DATA_URL);
        airPorts = DataReader.getAirPortsList(AIR_PORT_LIST_URL);
        airPortUnrealDistance = DataReader.getAirPortPos(AIR_PORT_POS_URL);
    }
    public static void updateFlightList(){
        DataWriter.updateFlight(FLIGHT_DATA_URL);
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
    public static String getFlightDuration(LocalDateTime depart, LocalDateTime arrive){
        Duration duration = Duration.between(depart, arrive);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
