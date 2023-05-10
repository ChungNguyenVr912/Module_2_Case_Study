package utils;

import entity.abstraction.AirPlane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AirplanePool {
    private static final int FLIGHT_MAXIMUM_TIME_GAP_MINUTE = 20;
    private static final int NUMBER_OF_AIRPLANE = 30;
    private final List<AirPlane> availableAirPlanes = Collections.synchronizedList(new ArrayList<>());
    private final List<AirPlane> inUseAirPlanes = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger airPlaneCount = new AtomicInteger(0);
    private final AtomicBoolean waiting = new AtomicBoolean(false);

    public synchronized void release(AirPlane airPlane) {
        inUseAirPlanes.remove(airPlane);
        availableAirPlanes.add(airPlane);
    }

    public synchronized AirPlane getAirPlane() {
        if (!availableAirPlanes.isEmpty()) {
            return availableAirPlanes.get(0);
        }
        if (airPlaneCount.get() == NUMBER_OF_AIRPLANE) {
            waitingForAirPlane();
            return getAirPlane();
        }
        AirPlane airPlane = createAirPlane();
        inUseAirPlanes.add(airPlane);
        return airPlane;
    }

    private AirPlane createAirPlane() {
        AirPlane airPlane = RandomEverything.randomAirPlane();
        airPlaneCount.incrementAndGet();
        System.out.println("Airplane created!");
        return airPlane;
    }

    private void waitingForAirPlane() {
        if (waiting.get()) {
            waiting.set(false);
        }
        waiting.set(true);
        //waiting to next depart time
    }
}
