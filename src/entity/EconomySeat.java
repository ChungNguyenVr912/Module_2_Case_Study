package entity;

import entity.abstraction.Seat;

public class EconomySeat extends Seat {
    private float priceMulti = 1;
    public EconomySeat(){
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
