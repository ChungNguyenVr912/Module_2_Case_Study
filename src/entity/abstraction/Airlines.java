package entity.abstraction;

import java.io.Serializable;

public interface Airlines extends Serializable {
    String getFullName();
    double getPriceMulti();
    void setPriceMulti(double priceMulti);
}
