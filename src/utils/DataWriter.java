package utils;

import entity.Flight;
import entity.abstraction.User;
import services.TicketAndReportService;

import java.io.*;
import java.io.IOException;
import java.util.List;

public class DataWriter {

    public static boolean updateUserList(List<User> users, String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(users);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateFlights(List<Flight> flightList, String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(flightList);
            System.out.println("Update flight list success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTicketList(String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(TicketAndReportService.getTicketList());
            System.out.println("Update success! (Tickets)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateReportList(String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(TicketAndReportService.getReportList());
            System.out.println("Update success! (Reports)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
