package builder;

import builder.abstraction.FlightBuilder;
import entity.Flight;
import entity.abstraction.Airlines;
import entity.abstraction.AirPlane;

import java.time.LocalDateTime;

public class FlightConcreteBuilder implements FlightBuilder {
    private Airlines provider;
    private AirPlane airPlane;
    private String departure;
    private String destination;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private String crewInfo;
    private double basePrice;

    public FlightConcreteBuilder() {
    }

    @Override
    public FlightConcreteBuilder setProvider(Airlines provider) {
        this.provider = provider;
        return this;
    }

    @Override
    public FlightConcreteBuilder setAirplane(AirPlane airPlane) {
        this.airPlane = airPlane;
        return this;
    }

    @Override
    public FlightConcreteBuilder setDeparture(String departure) {
        this.departure = departure;
        return this;
    }

    @Override
    public FlightConcreteBuilder setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    @Override
    public FlightConcreteBuilder setDepartTime(LocalDateTime departTime) {
        this.departTime = departTime;
        return this;
    }

    @Override
    public FlightConcreteBuilder setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    @Override
    public FlightConcreteBuilder setCrewInfo(String crewInfo) {
        this.crewInfo = crewInfo;
        return this;
    }

    public FlightConcreteBuilder setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public Flight build() {
        return new Flight(provider, airPlane, departure, destination, departTime, arrivalTime, crewInfo, basePrice);
    }
}
