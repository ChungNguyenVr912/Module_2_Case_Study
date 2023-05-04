package entity;

import entity.abstraction.Seat;

public class EconomySeat extends Seat {
    private double priceMulti = 1;

    public EconomySeat(String seatCode) {
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
