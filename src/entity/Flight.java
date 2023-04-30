package entity;

import entity.abstraction.Airlines;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {
    private Airlines provider;
    private AirPlane airPlane;
    private String departure;
    private String destination;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private String crewInfo;
    private String flightCode ="";
    private double basePrice;

    public Flight(Airlines provider, AirPlane airPlane, String departure, String destination
            , LocalDateTime departTime, LocalDateTime arrivalTime, String crewInfo, double basePrice) {
        this.provider = provider;
        this.airPlane = airPlane;
        this.departure = departure;
        this.destination = destination;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.crewInfo = crewInfo;
        this.basePrice = basePrice;
        setFlightCode();
    }

    private void setFlightCode() {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(provider.getFullName());
        while (matcher.find()) {
            result.append(matcher.group());
        }
        result.append(departTime.getDayOfMonth()).append(departTime.getMonthValue())
                .append("_").append(departTime.getDayOfWeek());
        result.append("_");
        matcher = pattern.matcher(departure);
        while (matcher.find()) {
            result.append(matcher.group());
        }
        result.append("-");
        matcher = pattern.matcher(destination);
        while (matcher.find()) {
            result.append(matcher.group());
        }
        flightCode =  result.toString();
    }

    public Airlines getProvider() {
        return provider;
    }

    public void setProvider(Airlines provider) {
        this.provider = provider;
    }

    public AirPlane getAirPlane() {
        return airPlane;
    }

    public void setAirPlane(AirPlane airPlane) {
        this.airPlane = airPlane;
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

    public LocalDateTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalDateTime departTime) {
        this.departTime = departTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCrewInfo() {
        return crewInfo;
    }

    public void setCrewInfo(String crewInfo) {
        this.crewInfo = crewInfo;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
