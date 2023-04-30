package utils;

import entity.AirLinesCompany;
import entity.Customer;
import entity.Flight;
import entity.Staff;
import entity.abstraction.User;
import services.AirlinesCompanyService;
import services.CustomerService;
import services.FlightService;
import services.StaffService;
import services.abstraction.UserService;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {
    public static void storeRegisteredUser(User user, String targetUrl) {
        List<User> userList = null;
        if (user instanceof Customer) {
            userList = UserService.getCustomerList();
        } else if (user instanceof Staff) {
            userList = UserService.getStaffList();
        } else if (user instanceof AirLinesCompany) {
            userList = UserService.getAirLinesCompanyList();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            if (userList == null) {
                userList = new ArrayList<>();
                userList.add(user);
            } else {
                userList.add(user);
            }
            outputStream.writeObject(userList);
            System.out.println("New user registered!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserInfoToUserList(List<User> users, String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(users);
            System.out.println("Update success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void storeFlights(Flight flight, String targetUrl){
        List<Flight> flightList = FlightService.getFlightList();
        flightList.add(flight);
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(flightList);
            System.out.println("Update success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateFlight(String targetUrl){
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(FlightService.getFlightList());
            System.out.println("Update success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
