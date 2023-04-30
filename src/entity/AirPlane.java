package entity;

import entity.abstraction.Seat;

import java.util.List;

public class AirPlane {
    private String name;
    List<Seat> seatList;

    public List<Seat> getSeatList() {
        return seatList;
    }

    public String getName() {
        return name;
    }
}
