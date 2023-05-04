package entity;

import entity.abstraction.Airlines;
import entity.abstraction.AirPlane;
import entity.abstraction.FlightInfo;
import utils.MyDateTime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight implements FlightInfo,Serializable {
    private static int FID = 1;
    private Airlines provider;
    private AirPlane airPlane;
    private String departure;
    private String destination;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private String crewInfo;
    private String flightCode ="";
    private long basePrice;

    public Flight(Airlines provider, AirPlane airPlane, String departure, String destination
            , LocalDateTime departTime, LocalDateTime arrivalTime, String crewInfo, double basePrice) {
        this.provider = provider;
        this.airPlane = airPlane;
        this.departure = departure;
        this.destination = destination;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.crewInfo = crewInfo;
        this.basePrice = ((long)basePrice) - ((long)basePrice%10000);
        FID++;
        setFlightCode();
    }

    private void setFlightCode() {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(provider.getFullName());
        while (matcher.find()) {
            result.append(matcher.group());
        }
        result.delete(result.length()-1,result.length());
        result.append(MyDateTime.toDayAndMonth(departTime)).append(String.format("%04d",FID));
        flightCode = result.toString();
    }

    public String getProviderName() {
        return provider.getFullName();
    }

    public void setProvider(Airlines provider) {
        this.provider = provider;
    }

    public AirPlane getAirplane() {
        return airPlane;
    }

    public void setAirplaneInfo(AirPlane airPlane) {
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
        this.basePrice = (long)basePrice;
    }

    @Override
    public String toString() {
        return String.format("%-14s", flightCode) + MyDateTime.toHourAndMinute(departTime)
                +"\t" + departure + "-" + destination +"\t"
                + MyDateTime.toHourAndMinute(arrivalTime) + "\tDate:" + MyDateTime.toDayMMMMYear(departTime)
                + "\tTemp Price: " + basePrice + "VND";
    }
}
