package entity.abstraction;

public abstract class Seat {
    protected boolean booked = false;

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public abstract float getPriceMulti();

    public abstract void setPriceMulti(float priceMulti);
}
