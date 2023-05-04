package entity;

import entity.abstraction.AirPlane;
import entity.abstraction.Seat;

import java.util.List;

public class BusinessAirPlane implements AirPlane {
    private String name;
    private List<Seat> seatList;

    public BusinessAirPlane(String name, List<Seat> seatList) {
        this.name = name;
        this.seatList = seatList;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Seat> getSeatList() {
        return seatList;
    }
}
