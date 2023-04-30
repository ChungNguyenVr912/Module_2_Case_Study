package entity;

import entity.abstraction.Seat;

public class BusinessSeat extends Seat {
    private float priceMulti = 2;
    public BusinessSeat(){
    }

    @Override
    public float getPriceMulti() {
        return priceMulti;
    }

    @Override
    public void setPriceMulti(float priceMulti) {
        this.priceMulti = priceMulti;
    }
}
