package services;

import entity.EconomySeat;
import entity.Flight;
import entity.abstraction.AirPlane;
import entity.abstraction.Seat;
import utils.FlightAirlinesComparator;
import utils.FlightDepartTimeComparator;
import utils.FlightPriceComparator;
import utils.ValidateInput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BookingService {
    private static final double baseCost = 500000;
    private static final double distanceMulti = 15000.0;
    private static List<Flight> filteredFlights = new ArrayList<>();

    private static Flight selectedFlight;
    private static double totalPrice;

    private static final Scanner scanner = new Scanner(System.in);

    public static void displayBookingView() {
        String[] route = selectRoute();
        String departure = route[0];
        String destination = route[1];
        System.out.println("From " + FlightService.getAirPorts().get(departure)
                + " to " + FlightService.getAirPorts().get(destination));
        System.out.print("Enter to search");
        scanner.nextLine();
        String s = scanner.nextLine(); //Make some color.
        FlightService.getFlightList().sort(FlightDepartTimeComparator.getInstance());
        FlightService.getFlightList().sort(FlightPriceComparator.getInstance());
        showFlightListByLocation(departure, destination);
        if (selectedFlight != null) {
            System.out.println("Current cost: " + totalPrice);
            Seat selectedSeat = seatSelection();
            totalPrice = totalPrice * selectedSeat.getPriceMulti();
            System.out.println("Current cost: " + totalPrice);
//            String luggageInfo =
        }
    }

    public static String[] selectRoute() {
        HashMap<String, String> allDeparture = new HashMap<>(FlightService.getAirPorts());
        allDeparture.forEach((key, value) -> System.out.println("[" + key + "]" + "\t" + value));
        String departure;
        do {
            System.out.println("Select departure:");
            departure = scanner.next().toUpperCase();
        } while (!allDeparture.containsKey(departure));
        allDeparture.remove(departure);
        allDeparture.forEach((key, value) -> System.out.println("[" + key + "]" + "\t" + value));
        String destination;
        do {
            System.out.println("Select destination:");
            destination = scanner.next().toUpperCase();
        } while (!allDeparture.containsKey(destination));
        return new String[]{departure, destination};
    }

    public static double getBaseCost(String departure, String destination, LocalDateTime departTime) {
        int distance = Math.abs(FlightService.airPortUnrealDistance.get(departure)
                - FlightService.airPortUnrealDistance.get(destination));
        LocalDateTime start = LocalDateTime.of(departTime.getYear(), departTime.getMonth(), departTime.getDayOfMonth(), 8, 0);
        LocalDateTime end = LocalDateTime.of(departTime.getYear(), departTime.getMonth(), departTime.getDayOfMonth(), 20, 0);
        double priceMultiByTime = 1;
        if (departTime.isAfter(start) && departTime.isBefore(end)) {    //if daytime, increase price.
            priceMultiByTime = 1.15;
        }
        return (distance * distanceMulti + baseCost) * priceMultiByTime;
    }

    private static void showFlightListByLocation(String departure, String destination) {
        List<Flight> flightList = FlightService.getFlightList();
        System.out.println("Sorted by price:");
        filteredFlights.clear();
        flightList.forEach(flight -> {
            if (flight.getDeparture().equals(departure) && flight.getDestination().equals(destination)) {
                filteredFlights.add(flight);
            }
        });
        filteredFlights.forEach(System.out::println);
        while (true) {
            System.out.println("""
                    1. Sort by depart time.
                    2. Sort by airlines.
                    3. Sort by price.
                    4. View flight detail.
                    5. Back.
                    """);
            int input = ValidateInput.validateInteger();
            switch (input) {
                case 1 -> {
                    filteredFlights.sort(FlightDepartTimeComparator.getInstance());
                    filteredFlights.forEach(System.out::println);
                }
                case 2 -> {
                    filteredFlights.sort(FlightAirlinesComparator.getInstance());
                    filteredFlights.forEach(System.out::println);
                }
                case 3 -> {
                    filteredFlights.sort(FlightPriceComparator.getInstance());
                    filteredFlights.forEach(System.out::println);
                }
                case 4 -> {
                    viewFlightDetail();
                    System.out.println("Select this flight? (Y/N)");
                    String choice = scanner.next();
                    if (choice.equalsIgnoreCase("Y")) {
                        totalPrice = selectedFlight.getBasePrice();
                        return;
                    } else {
                        selectedFlight = null;
                    }
                }
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void viewFlightDetail() {
        System.out.print("Enter flight code: ");
        String flightCode = scanner.nextLine();
        for (Flight flight : filteredFlights) {
            if (flight.getFlightCode().equals(flightCode)) {
                selectedFlight = flight;
                System.out.println(flight);
                System.out.println(flight.getProviderName() + "\tAir plane: " + flight.getAirplane().getName());
                System.out.println("Expected fly time: "
                        + FlightService.getFlightDuration(flight.getDepartTime(), flight.getArrivalTime()));
                System.out.println("Crew: " + flight.getCrewInfo());
            }
        }
    }

    private static Seat seatSelection() {
        AirPlane airPlane = selectedFlight.getAirplane();
        List<Seat> seatList = airPlane.getSeatList();
        for (int i = 0; i < seatList.size() - 4; i += 4) {
            for (int j = 0; j < 4; j++) {
                String booked;
                String seatClass;
                if (seatList.get(i + j).isBooked()) {
                    booked = "X";
                } else {
                    booked = "O";
                }
                if (seatList.get(i + j) instanceof EconomySeat) {
                    seatClass = "Eco-class";
                } else {
                    seatClass = "Bus-class";
                }
                System.out.printf("%3s %s %s", seatList.get(i + j).getSeatCode(), seatClass, booked);
                System.out.print("\t");
            }
            System.out.println();
        }
        do {
            System.out.println("""
                    * Select seat: (O is available, X is booked)
                    Enter seat code:
                    """);
            String seatCode = scanner.next();
            for (Seat seat : seatList) {
                if (seat.getSeatCode().equals(seatCode)) {
                    if (seat.isBooked()) {
                        System.out.println("Seat not available!");
                    } else {
                        seat.setBooked(true);
                        return seat;
                    }
                } else {
                    System.out.println("Seat not found!");
                }
            }
        } while (true);
    }
}
