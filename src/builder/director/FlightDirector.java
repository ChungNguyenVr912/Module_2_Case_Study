package builder.director;

import builder.FlightConcreteBuilder;
import builder.abstraction.FlightBuilder;
import entity.Flight;
import entity.abstraction.Airlines;

import java.util.Date;

public class FlightDirector {
    public static Flight buildFlight(Airlines provider, String departure, String destination, Date departTime, Date arrivalTime){
        FlightBuilder builder = new FlightConcreteBuilder();
        return builder
                .setProvider(provider)
                .setDeparture(departure)
                .setDestination(destination)
                .setDepartTime(departTime)
                .setArrivalTime(arrivalTime)
                .build();
    }
}
