package entity;

import entity.abstraction.User;

public class Customer extends User {
    private String gender;
    private long citizenID;

    public Customer(String fullName, String email, String passWord, long phoneNumber) {
        super(fullName, email, passWord, phoneNumber);
    }

    public Customer(String fullName, String passWord, String email, String gender, long citizenID, long phoneNumber) {
        super(fullName, passWord, email, phoneNumber);
        this.gender = gender;
        this.citizenID = citizenID;
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
}
