package entity;

import entity.abstraction.Seat;

public class BusinessSeat extends Seat {

    private double priceMulti = 2;

    public BusinessSeat(String seatCode) {
        this.seatCode = seatCode;
    }

    @Override
    public double getPriceMulti() {
        return priceMulti;
    }

    @Override
    public void setPriceMulti(double priceMulti) {
        this.priceMulti = priceMulti;
    }
}
