package entity.abstraction;

import java.io.Serializable;

public abstract class Seat implements Serializable {
    protected String seatCode;
    protected boolean booked = false;

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public abstract double getPriceMulti();

    public abstract void setPriceMulti(double priceMulti);
}
