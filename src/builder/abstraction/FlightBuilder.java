package builder.abstraction;

import entity.Flight;
import entity.abstraction.Airlines;
import entity.abstraction.AirPlane;
import java.time.LocalDateTime;

public interface FlightBuilder {
    FlightBuilder setProvider(Airlines provider);
    FlightBuilder setAirplane(AirPlane airPlane);
    FlightBuilder setDeparture(String departure);
    FlightBuilder setDestination(String destination);
    FlightBuilder setDepartTime(LocalDateTime departTime);
    FlightBuilder setArrivalTime(LocalDateTime arrivalTime);
    FlightBuilder setCrewInfo(String crewInfo);
    FlightBuilder setBasePrice(double basePrice);
    Flight build();
}
