package builder.abstraction;

import entity.Flight;
import entity.abstraction.Airlines;

import java.util.Date;

public interface FlightBuilder {
    FlightBuilder setProvider(Airlines provider);
    FlightBuilder setDeparture(String departure);
    FlightBuilder setDestination(String destination);
    FlightBuilder setDepartTime(Date departTime);
    FlightBuilder setArrivalTime(Date arrivalTime);
    Flight build();
}
