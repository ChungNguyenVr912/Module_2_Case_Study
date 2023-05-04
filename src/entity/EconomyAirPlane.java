package entity;

import entity.abstraction.AirPlane;
import entity.abstraction.Seat;
import java.util.List;

public class EconomyAirPlane implements AirPlane {
    private String name;
    private List<Seat> seatList;

    public EconomyAirPlane(String name, List<Seat> seatList) {
        this.name = name;
        this.seatList = seatList;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
