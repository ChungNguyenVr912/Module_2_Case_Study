package services;

import entity.*;
import entity.abstraction.AirPlane;
import entity.abstraction.Seat;
import entity.user_impl.Customer;
import services.abstraction.UserService;
import utils.FlightAirlinesComparator;
import utils.FlightDepartTimeComparator;
import utils.FlightPriceComparator;
import utils.ValidateInput;
import utils.display.CustomerView;

import java.time.LocalDate;
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
        while (true) {
            do {
                String departure = route[0];
                String destination = route[1];
                System.out.println("From " + FlightService.getAirPorts().get(departure)
                        + " to " + FlightService.getAirPorts().get(destination));
                System.out.print("""
                        Option:
                        1. Search flight
                        2. Select other route
                        3. Back
                        """);
                int input = ValidateInput.validateInteger();
                switch (input) {
                    case 1 -> showFlightListByLocation(departure, destination);
                    case 2 -> displayBookingView();
                    case 3 -> {
                        return;
                    }
                }
            } while (selectedFlight == null);
            completeBooking();
            FlightService.updateFlightList();
            selectedFlight = null;
        }
    }

    private static void completeBooking() {
        System.out.println("Current cost: " + totalPrice);
        Seat selectedSeat = seatSelection();
        if (selectedSeat == null) return;
        totalPrice = totalPrice * selectedSeat.getPriceMulti();
        System.out.println("Current cost: " + totalPrice);
        String baggageInfo = buyAdditionalBaggage();
        System.out.println("Current cost: " + totalPrice);
        String passengerName;
        String passengerGender;
        LocalDate dob;
        long phoneNumber;
        String input = "N";
        if (UserService.getCurrentUser() instanceof Customer) {
            System.out.println("Book for your self? (Y/N)");
            input = scanner.nextLine();
        }
        if (input.equalsIgnoreCase("Y")) {
            Customer customer = (Customer) UserService.getCurrentUser();
            while (customer.getGender() == null
                    || customer.getDayOfBirth() == null
                    || customer.getPhoneNumber() == 0) {
                System.out.println("Please update your profile!");
                CustomerView.displayUpdateInformation();
            }
            passengerName = customer.getFullName();
            passengerGender = customer.getGender();
            dob = customer.getDayOfBirth();
            phoneNumber = customer.getPhoneNumber();
        } else {
            System.out.println("Passenger name: ");
            passengerName = scanner.nextLine();
            System.out.println("Gender: ");
            passengerGender = scanner.nextLine();
            System.out.println("Day of birth: ");
            dob = ValidateInput.validateDate();
            System.out.println("Contact phone number: ");
            phoneNumber = ValidateInput.validateLong();
        }

        String ticketClass;
        if (selectedSeat instanceof BusinessSeat) {
            ticketClass = "Business class";
        } else {
            ticketClass = "Economy class";
        }
        Ticket ticket = new Ticket.TicketBuilder()
                .setBookingDay(LocalDateTime.now())
                .setFlightCode(selectedFlight.getFlightCode())
                .setPassengerName(passengerName)
                .setBaggageInfo(baggageInfo)
                .setPrice(totalPrice)
                .setSeatCode(selectedSeat.getSeatCode())
                .setTicketClass(ticketClass)
                .setPassengerDoB(dob)
                .setPassengerGender(passengerGender)
                .setPassengerPhoneNumber(phoneNumber)
                .build();
        if (UserService.getCurrentUser() instanceof Customer) {
            TicketAndReportService.printTicketAndSendToCustomer(ticket);
        } else {
            TicketAndReportService.printTicket(ticket);
        }
    }


    public static String[] selectRoute() {
        HashMap<String, String> allDeparture = new HashMap<>(FlightService.getAirPorts());
        allDeparture.forEach((key, value) -> System.out.println("[" + key + "]" + "\t" + value));
        String departure;
        do {
            System.out.println("Select departure:");
            departure = scanner.nextLine().toUpperCase();
        } while (!allDeparture.containsKey(departure));
        allDeparture.remove(departure);
        allDeparture.forEach((key, value) -> System.out.println("[" + key + "]" + "\t" + value));
        String destination;
        do {
            System.out.println("Select destination:");
            destination = scanner.nextLine().toUpperCase();
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
        filteredFlights.clear();
        flightList.forEach(flight -> {
            if (flight.getDeparture().equals(departure) && flight.getDestination().equals(destination)
                    && flight.getDepartTime().isAfter(LocalDateTime.now().plusMinutes(30))) {
                filteredFlights.add(flight);
            }
        });
        System.out.println("Sorted by price:");
        filteredFlights.sort(FlightDepartTimeComparator.getInstance());
        filteredFlights.sort(FlightPriceComparator.getInstance());
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
                    String choice = scanner.nextLine();
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
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();
        for (Flight flight : filteredFlights) {
            if (flight.getFlightCode().equals(flightCode)) {
                selectedFlight = flight;
                System.out.println(flight);
                System.out.println(flight.getProviderName() + "\tAir plane: " + flight.getAirplane().getName());
                System.out.println("Expected fly time: "
                        + FlightService.getFlightDuration(flight.getDepartTime(), flight.getArrivalTime()));
                System.out.println("Crew: " + flight.getCrewInfo());
                break;
            }
        }
    }

    private static Seat seatSelection() {
        AirPlane airPlane = selectedFlight.getAirplane();
        List<Seat> seatList = airPlane.getSeatList();
        for (int i = 0; i < seatList.size(); i += 4) {
            for (int j = 0; j < 4 && i + j < seatList.size(); j++) {
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
                    * Select seat: (O is exist, X is booked)
                    Enter seat code: (type "cancel" to cancel)
                    """);
            String seatCode = scanner.nextLine();
            if (seatCode.equals("cancel")) return null;
            boolean exist = true;
            for (Seat seat : seatList) {
                if (seat.getSeatCode().equals(seatCode)) {
                    if (seat.isBooked()) {
                        System.out.println("Seat is already booked!");
                        exist = false;
                        break;
                    } else {
                        seat.setBooked(true);
                        return seat;
                    }
                }
            }
            if (exist) {
                System.out.println("Seat not found!");
            }
        } while (true);
    }

    private static String buyAdditionalBaggage() {
        System.out.println("""
                Select baggage pack:
                1. 10kg (150.000VND)
                2. 15kg (200.000VND)
                3. 20kg (250.000VND)
                4. 30kg (350.000VND)
                5. No, thanks.
                """);
        int input = ValidateInput.validateInteger();
        switch (input) {
            case 1 -> {
                totalPrice += 150000;
                return "First bag 9kg and second bag 10kg.";
            }
            case 2 -> {
                totalPrice += 200000;
                return "First bag 9kg and second bag 15kg.";
            }
            case 3 -> {
                totalPrice += 250000;
                return "First bag 9kg and second bag 20kg.";
            }
            case 4 -> {
                totalPrice += 350000;
                return "First bag 9kg and second bag 30kg.";
            }
            default -> {
                return "First bag 9kg";
            }
        }
    }

}
