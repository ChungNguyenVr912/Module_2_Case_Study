package factory;

import entity.BusinessAirPlane;
import entity.BusinessSeat;
import entity.EconomyAirPlane;
import entity.EconomySeat;
import entity.abstraction.AirPlane;
import entity.abstraction.Seat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhakeAirPlaneFactory {
    private static final ArrayList<String> listPlaneName = new ArrayList<>();
    private static final String[] prefixSeatCode = {"A", "B", "C", "D"};
    private static final Random random = new Random();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/airplanes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listPlaneName.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static AirPlane getAirplane(String type){
        switch (type){
            case "economy" -> {
                return buildEconomyAirPlane();
            }
            case "business" -> {
                return buildBusinessAirPlane();
            }
            default -> {
                return null;
            }
        }
    }

    private static AirPlane buildEconomyAirPlane() {
        List<Seat> seatList = new ArrayList<>();
        for (int subfix = 1; subfix < 16; subfix++) {
            for (int prefix = 0; prefix < 4; prefix++) {
                seatList.add(new EconomySeat(prefixSeatCode[prefix] + subfix));
            }
        }
        String airPlaneName = listPlaneName.get(random.nextInt(3));
        return new EconomyAirPlane(airPlaneName, seatList);
    }

    private static AirPlane buildBusinessAirPlane() {
        List<Seat> seatList = new ArrayList<>();
        for (int subfix = 1; subfix < 6; subfix++) {
            for (int prefix = 0; prefix < 4; prefix++) {
                seatList.add(new BusinessSeat(prefixSeatCode[prefix] + subfix));
            }
        }
        for (int subfix = 6; subfix < 11; subfix++) {
            for (int prefix = 0; prefix < 4; prefix++) {
                seatList.add(new EconomySeat(prefixSeatCode[prefix] + subfix));
            }
        }
        String airPlaneName = listPlaneName.get(random.nextInt(3));
        return new BusinessAirPlane(airPlaneName, seatList);
    }
}
