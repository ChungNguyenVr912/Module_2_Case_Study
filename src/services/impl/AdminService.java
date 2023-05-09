package services.impl;

import entity.user_impl.AirLinesAccount;
import entity.user_impl.Customer;
import entity.user_impl.Staff;
import entity.abstraction.User;
import services.TicketAndReportService;
import services.abstraction.UserService;
import utils.display.AccountDisplay;

import java.util.List;
import java.util.Scanner;

public class AdminService extends UserService {
    private static final AdminService instance = new AdminService();
    private AdminService() {}
    public static AdminService getInstance() {
        return instance;
    }
    public void addStaff() {
        AccountDisplay.getInstance().displaySignUp("staff");
    }
    public void addAirlinesCompany() {
        AccountDisplay.getInstance().displaySignUp("airlines");
    }

    public void viewStaffList() {
        List<User> users = UserService.staffList;
        System.out.println("Total staff: " + users.size());
        users.forEach(user -> {
            Staff staff = (Staff) user;
            System.out.println(staff);
        });
    }
    public void viewAirlinesList() {
        List<User> users = UserService.airLinesList;
        System.out.println("Total partner: " + users.size());
        users.forEach(user -> {
            AirLinesAccount airlines = (AirLinesAccount) user;
            System.out.println(airlines);
        });
    }

    public void checkUser() {
        List<User> users = UserService.customerList;
        System.out.println("Total user: " + users.size());
        System.out.println("Show all user? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine();
        switch (confirm) {
            case "y", "Y", "yes":
                users.forEach(user -> {
                    Customer customer = (Customer) user;
                    System.out.println(customer);
                });
            default:
        }
    }
    public void viewAllReport(){
        TicketAndReportService.getReportList().forEach(System.out::println);
    }
}
