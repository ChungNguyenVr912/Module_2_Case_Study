package builder;

import builder.abstraction.FlightBuilder;
import entity.Flight;
import entity.abstraction.Airlines;

import java.util.Date;

public class FlightConcreteBuilder implements FlightBuilder {
    private Airlines provider;
    private String departure;
    private String destination;
    private Date departTime;
    private Date arrivalTime;

    public FlightConcreteBuilder() {
    }

    @Override
    public FlightConcreteBuilder setProvider(Airlines provider) {
        this.provider = provider;
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
    public FlightConcreteBuilder setDepartTime(Date departTime) {
        this.departTime = departTime;
        return this;
    }

    @Override
    public FlightConcreteBuilder setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public Flight build() {
        return new Flight(provider, departure, destination, departTime, arrivalTime);
    }
}
