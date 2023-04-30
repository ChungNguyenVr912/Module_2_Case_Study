package utils.display;

import services.FlightService;

import java.util.HashMap;
import java.util.Scanner;

public class BookingView {

    public static void displayBookingView() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> allDeparture = new HashMap<>(FlightService.getAirPorts());
        allDeparture.forEach((key, value) -> System.out.println("[" + key + "]" + "\t" + value));
        String departure;
        do {
            System.out.println("Select departure:");
            departure = scanner.next();
        } while (!allDeparture.containsKey(departure));
        allDeparture.remove(departure);
        allDeparture.forEach((key, value) -> System.out.println("[" + key + "]" + "\t" + value));
        String destination;
        do {
            System.out.println("Select destination:");
            destination = scanner.next();
        } while (!allDeparture.containsKey(destination));
    }
}
