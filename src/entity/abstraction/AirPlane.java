package entity.abstraction;

import java.io.Serializable;
import java.util.List;

public interface AirPlane extends Serializable {
    String getName();
    List<Seat> getSeatList();
}
