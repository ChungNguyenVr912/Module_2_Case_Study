package builder.director;

import builder.FlightConcreteBuilder;
import builder.abstraction.FlightBuilder;
import entity.Flight;
import entity.abstraction.Airlines;
import entity.abstraction.AirPlane;

import java.time.LocalDateTime;

public class FlightDirector {
    public static Flight buildFlight(Airlines provider, AirPlane airPlane
            , String departure, String destination
            , LocalDateTime departTime, LocalDateTime arrivalTime, String crewInfo, double basePrice){
        FlightBuilder builder = new FlightConcreteBuilder();
        return builder
                .setProvider(provider)
                .setAirplane(airPlane)
                .setDeparture(departure)
                .setDestination(destination)
                .setDepartTime(departTime)
                .setArrivalTime(arrivalTime)
                .setCrewInfo(crewInfo)
                .setBasePrice(basePrice)
                .build();
    }
}
