package entity;

import entity.abstraction.Airlines;
import entity.abstraction.User;

public class AirLinesCompany extends User implements Airlines {
    public AirLinesCompany(String fullName, String email, String passWord, long phoneNumber) {
        super(fullName, email, passWord, phoneNumber);
    }
}
