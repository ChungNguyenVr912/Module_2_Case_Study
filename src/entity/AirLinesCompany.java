package entity;

import entity.abstraction.Airlines;
import entity.abstraction.User;

public class AirLinesCompany extends User implements Airlines {
    private double priceMulti;

    public AirLinesCompany(String fullName, String email, String passWord, long phoneNumber, double priceMulti) {
        super(fullName, email, passWord, phoneNumber);
        this.priceMulti = priceMulti;
    }
@Override
    public double getPriceMulti() {
        return priceMulti;
    }
@Override
    public void setPriceMulti(double priceMulti) {
        this.priceMulti = priceMulti;
    }

    @Override
    public String toString() {
        return super.toString() + "\tPrice multi: " + priceMulti;
    }
}
