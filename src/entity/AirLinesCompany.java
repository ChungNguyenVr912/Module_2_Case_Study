package entity;

import entity.abstraction.Airlines;
import entity.abstraction.User;

import java.util.ArrayList;

public class AirLinesCompany extends User implements Airlines {
    private ArrayList<Flight> flightList = new ArrayList<>();

    public AirLinesCompany(String fullName, String passWord, String email, long phoneNumber) {
        super(fullName, passWord, email, phoneNumber);
    }

    public ArrayList<Flight> getFlightList() {
        return flightList;
    }

    public void addFlight(Flight flight){
        flightList.add(flight);
    }
    public void exportFlightHistory(){

    }
    public void updateFlight(String flightCode){

    }
}
