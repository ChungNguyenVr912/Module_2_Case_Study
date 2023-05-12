package utils;

import builder.FlightConcreteBuilder;
import factory.PhakeAirPlaneFactory;
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
        List<User> airlinesList = UserService.getAirLinesList();
        Airlines airlines = (Airlines) airlinesList.get(random.nextInt(airlinesList.size()));
        String[] route = randomLocation();
        String departure = route[0];
        String destination = route[1];
        String airLinesName = airlines.getFullName();
        double priceMulti = airlines.getPriceMulti();
        LocalDateTime departTime = randomDepartTimeInNextNumberOfDay(7);
        int flyTime = FlightService.getFlightDuration(departure, destination);// in minute
        LocalDateTime arrivalTime = departTime.plusHours(flyTime / 60).plusMinutes(flyTime % 60);
        double basePrice = BookingService.getBaseCost(departure, destination, departTime) * priceMulti;
        return new FlightConcreteBuilder()
                .setAirLines(airLinesName)
                .setAirplane(randomAirPlane())
                .setDeparture(departure)
                .setDestination(destination)
                .setDepartTime(departTime)
                .setArrivalTime(arrivalTime)
                .setCrewInfo(randomCrewInfo())
                .setBasePrice(basePrice)
                .build();
    }

    public static List<Flight> randomFlights(int quantity) {
        List<User> airlinesList = UserService.getAirLinesList();
        Set<LocalDateTime> setOfDepartTime = new HashSet<>();
        while (setOfDepartTime.size() < quantity) {
            setOfDepartTime.add(randomDepartTimeInNextNumberOfDay(7));
        }
        List<Flight> flightList = new ArrayList<>();
        for (LocalDateTime departTime : setOfDepartTime) {
            Airlines airlines = (Airlines) airlinesList.get(random.nextInt(airlinesList.size()));
            String airLinesName = airlines.getFullName();
            double priceMulti = airlines.getPriceMulti();
            String[] route = randomLocation();
            String departure = route[0];
            String destination = route[1];
            int flyTime = FlightService.getFlightDuration(departure, destination);
            LocalDateTime arrivalTime = departTime.plusHours(flyTime / 60).plusMinutes(flyTime % 60);
            double basePrice = BookingService.getBaseCost(departure, destination, departTime) * priceMulti;
            List<AirPlane> airPlaneList = randomPlaneList(30);
            AirPlane airPlane = randomAirPlaneInPlaneList(airPlaneList);
            flightList.add(new FlightConcreteBuilder()
                    .setAirLines(airLinesName)
                    .setAirplane(airPlane)
                    .setDeparture(departure)
                    .setDestination(destination)
                    .setDepartTime(departTime)
                    .setArrivalTime(arrivalTime)
                    .setCrewInfo(randomCrewInfo())
                    .setBasePrice(basePrice)
                    .build());
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

    public static LocalDateTime randomDepartTimeInNextNumberOfDay(int days) {
        int hour = random.nextInt(24);
        int minute = random.nextInt(6) * 10;
        int day = random.nextInt(days) + 1;
        return LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0).plusDays(day);
    }

    public static AirPlane randomAirPlane() {
        int choice = random.nextInt(2);
        if (choice == 0) {
            return PhakeAirPlaneFactory.getAirplane("economy");
        } else {
            return PhakeAirPlaneFactory.getAirplane("business");
        }
    }
    private static List<AirPlane> randomPlaneList(int quantity){
        List<AirPlane> planeList = new ArrayList<>();
        while (planeList.size() < quantity){
            planeList.add(randomAirPlane());
        }
        return planeList;
    }
    private static AirPlane randomAirPlaneInPlaneList(List<AirPlane> airPlaneList){
        return airPlaneList.get(random.nextInt(airPlaneList.size()-1));
    }
}
