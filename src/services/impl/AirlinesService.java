package services.impl;

import builder.FlightConcreteBuilder;
import entity.user_impl.AirLinesAccount;
import entity.Flight;
import entity.abstraction.AirPlane;
import entity.abstraction.User;
import factory.UserFactory;
import services.FlightService;
import services.abstraction.UserService;
import utils.DataWriter;
import utils.RandomEverything;
import utils.ValidateInput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlinesService extends UserService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)) {
            User newAirlines = UserFactory.getInstance()
                    .getUser("airlines", fullName, email, passWord, phoneNumber);
            airLinesList.add(newAirlines);
            UserService.updateUserList();
        } else {
            status = "Email has been used!";
        }
    }

    public static void addFlight() {
        if (currentUser instanceof AirLinesAccount) {
            AirPlane airPlane = RandomEverything.randomAirPlane();
            System.out.println("Set departure:");
            String departure = scanner.nextLine();
            System.out.println("Set destination:");
            String destination = scanner.nextLine();
            System.out.println("Set depart time (dd/MM/yyyy HH:mm)");
            LocalDateTime departTime = ValidateInput.validateDateTime();
            int flyTime = FlightService.getFlightDuration(departure, destination);
            LocalDateTime arrivalTime = departTime.plusHours(flyTime / 60).plusMinutes(flyTime % 60);
            String crewInfo = RandomEverything.randomCrewInfo();
            System.out.println("Set base price:");
            double basePrice = ValidateInput.validateDouble();
            Flight flight = new FlightConcreteBuilder()
                    .setAirLines(currentUser.getFullName())
                    .setAirplane(airPlane)
                    .setDeparture(departure)
                    .setDestination(destination)
                    .setDepartTime(departTime)
                    .setArrivalTime(arrivalTime)
                    .setCrewInfo(crewInfo)
                    .setBasePrice(basePrice)
                    .build();
            FlightService.getFlightList().add(flight);
            FlightService.updateFlightList();
        } else {
            System.out.println("Something wrong!");
        }
    }

    public static void viewFlightHistory() {
        List<Flight> flightList = getAllFlight();
        System.out.println("Departed flights: ");
        flightList.forEach(flight -> {
            if (flight.getDepartTime().isBefore(LocalDateTime.now())) {
                System.out.println(flight);
            }
        });
        System.out.println("Upcoming flights:");
        flightList.forEach(flight -> {
            if (flight.getDepartTime().isAfter(LocalDateTime.now())) {
                System.out.println(flight);
            }
        });
    }

    private static List<Flight> getAllFlight() {
        List<Flight> flightList = new ArrayList<>();
        for (Flight flight : FlightService.getFlightList()) {
            if (flight.getProviderName().equals(currentUser.getFullName())) {
                flightList.add(flight);
            }
        }
        return flightList;
    }

    public static void updateFlight() {
        System.out.println("Enter flight code:");
        String flightCode = scanner.nextLine();
        List<Flight> flightList = getAllFlight();
        List<Flight> ableToModififlightList = new ArrayList<>();
        for (Flight flight : flightList) {
            if (flight.getDepartTime().isAfter(LocalDateTime.now().plusDays(1))) {
                ableToModififlightList.add(flight);
            }
        }
        Flight expectedFlight = null;
        for (Flight flight : ableToModififlightList) {
            if (flight.getFlightCode().equals(flightCode)) {
                expectedFlight = flight;
                break;
            }
        }
        if (expectedFlight == null) {
            System.out.println("Flight not found!");
            return;
        }
        while (true) {
            System.out.println("""
                    1. Delay flight
                    2. Update crew
                    3. Change base price
                    4. Cancel
                    """);
            int input = ValidateInput.validateInteger();
            switch (input) {
                case 1 -> delayFlight(expectedFlight);
                case 2 -> updateCrew(expectedFlight);
                case 3 -> changeBasePrice(expectedFlight);
                case 4 -> {
                    return;
                }
            }
        }

    }

    private static void changeBasePrice(Flight flight) {
        System.out.println("Input new base price:");
        double basePrice = ValidateInput.validateDouble();
        System.out.println("Confirm? (Y/N)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            flight.setBasePrice(basePrice);
            FlightService.updateFlightList();
        }
    }

    private static void updateCrew(Flight flight) {
        System.out.println("Input crew info:");
        String crewInfo = scanner.nextLine();
        System.out.println("Confirm? (Y/N)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            flight.setCrewInfo(crewInfo);
            FlightService.updateFlightList();
        }
    }

    private static void delayFlight(Flight flight) {
        System.out.println("Enter delay time in minute:");
        int delayTime = ValidateInput.validateInteger();
        System.out.println("Confirm delay? (Y/N) " + delayTime + " minute");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            flight.setArrivalTime(flight.getArrivalTime().plusHours(delayTime / 60).plusMinutes(delayTime % 60));
            FlightService.updateFlightList();
        }
    }
}
