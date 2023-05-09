package entity.user_impl;

import entity.Ticket;
import entity.abstraction.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends User {
    private String gender;
    private long citizenID;
    private LocalDate dayOfBirth;
    private ArrayList<String> bookedTicket = new ArrayList<>();

    public Customer(String fullName, String email, String passWord, long phoneNumber) {
        super(fullName, email, passWord, phoneNumber);
    }

    public Customer(String fullName, String passWord, String email, long phoneNumber, String gender, long citizenID, LocalDate dayOfBirth) {
        super(fullName, passWord, email, phoneNumber);
        this.gender = gender;
        this.citizenID = citizenID;
        this.dayOfBirth = dayOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(long citizenID) {
        this.citizenID = citizenID;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public ArrayList<String> getBookedTicket() {
        return bookedTicket;
    }

    public void setBookedTicket(String ticketCode) {
        this.bookedTicket.add(ticketCode);
    }
}
