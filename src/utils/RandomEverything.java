package utils;

import builder.PhakeAirPlaneBuilder;
import builder.director.FlightDirector;
import entity.Flight;
import entity.abstraction.AirPlane;
import entity.abstraction.Airlines;
import entity.abstraction.User;
import services.BookingService;
import services.FlightService;
import services.abstraction.UserService;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class RandomEverything {
    private static final Random random = new Random();

    public static Flight randomFlight() {
        List<User> airlinesList = UserService.getAirLinesCompanyList();
        Airlines provider = (Airlines) airlinesList.get(random.nextInt(airlinesList.size() - 1));
        String[] route = randomLocation();
        String departure = route[0];
        String destination = route[1];
        LocalDateTime departTime = randomDepartTimeInNext7Day();
        int flyTime = getFlyTime(departure, destination);
        LocalDateTime arrivalTime = departTime.plusHours(flyTime / 60).plusMinutes(flyTime % 60);
        double basePrice = BookingService.getBaseCost(departure, destination, departTime);
        return FlightDirector.buildFlight(provider, randomAirPlane()
                , departure, destination, departTime, arrivalTime, randomCrewInfo(), basePrice);
    }

    public static List<Flight> random100Flights() {
        List<User> airlinesList = UserService.getAirLinesCompanyList();
        Set<LocalDateTime> setDepartTime = new HashSet<>();
        while (setDepartTime.size() < 2000) {
            setDepartTime.add(randomDepartTimeInNext7Day());
        }
        List<Flight> flightList = new ArrayList<>();
        for (LocalDateTime departTime : setDepartTime) {
            Airlines provider = (Airlines) airlinesList.get(random.nextInt(airlinesList.size()));
            String[] route = randomLocation();
            String departure = route[0];
            String destination = route[1];
            int flyTime = getFlyTime(departure, destination);
            LocalDateTime arrivalTime = departTime.plusHours(flyTime / 60).plusMinutes(flyTime % 60);
            double basePrice = BookingService.getBaseCost(departure, destination, departTime) * provider.getPriceMulti();
            flightList.add(FlightDirector.buildFlight(provider, randomAirPlane()
                    , departure, destination, departTime, arrivalTime, randomCrewInfo(), basePrice));
        }
        return flightList;
    }

    public static String randomCrewInfo() {
        ArrayList<String> allCrewList = new ArrayList<>();
        try (BufferedReader inputStream = new BufferedReader(new FileReader("src/data/crews.txt"))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                allCrewList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> crews = new ArrayList<>();
        crews.add(allCrewList.remove(random.nextInt(allCrewList.size() - 1)));
        crews.add(allCrewList.remove(random.nextInt(allCrewList.size() - 1)));
        crews.add(allCrewList.remove(random.nextInt(allCrewList.size() - 1)));
        return String.format("Captain %s and two co-pilots %s, %s.", crews.get(0), crews.get(1), crews.get(2));
    }

    public static String[] randomLocation() {
        HashMap<String, Integer> location = new HashMap<>(FlightService.airPortUnrealDistance);
        ArrayList<String> arrLocation = new ArrayList<>(location.keySet());
        String departure = arrLocation.get(random.nextInt(arrLocation.size() - 1));
        int startPos = location.remove(departure);
        ArrayList<String> ignoreLocation = new ArrayList<>();
        location.forEach((key, value) -> {
            if (Math.abs(value - startPos) < 2) {
                ignoreLocation.add(key);
            }
        });
        ignoreLocation.forEach(location::remove);
        arrLocation = new ArrayList<>(location.keySet());
        String destination = arrLocation.get(random.nextInt(arrLocation.size() - 1));
        return new String[]{departure, destination};
    }

    public static LocalDateTime randomDepartTimeInNext7Day() {
        int hour = random.nextInt(24);
        int minute = random.nextInt(6) * 10;
        int day = random.nextInt(7) + 1;
        return LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0).plusDays(day);
    }

    public static int getFlyTime(String departure, String destination) {
        final float timeMulti = 3.85f;
        final int baseFlyTime = 45;// in minute
        int startPos = FlightService.airPortUnrealDistance.get(departure);
        int endPos = FlightService.airPortUnrealDistance.get(destination);
        int distance = Math.abs(startPos - endPos);
        return (int) (distance * timeMulti) - ((int) (distance * timeMulti)) % 5 + baseFlyTime;
    }

    public static AirPlane randomAirPlane() {
        int choice = random.nextInt(2);
        AirPlane airPlane;
        if (choice == 0) {
            airPlane = PhakeAirPlaneBuilder.buildEconomyAirPlane();
        } else {
            airPlane = PhakeAirPlaneBuilder.buildBusinessAirPlane();
        }
        return airPlane;
    }
}
