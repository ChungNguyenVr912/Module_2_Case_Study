package entity;

import entity.abstraction.Airlines;

import java.util.Date;

public class Flight{
    private Airlines provider;
    private String departure;
    private String destination;
    private Date departTime;
    private Date arrivalTime;

    public Flight(Airlines provider, String departure, String destination, Date departTime, Date arrivalTime) {
        this.provider = provider;
        this.departure = departure;
        this.destination = destination;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
    }

    public Airlines getProvider() {
        return provider;
    }

    public void setProvider(Airlines provider) {
        this.provider = provider;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
