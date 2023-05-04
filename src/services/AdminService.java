package services;

import entity.AirLinesCompany;
import entity.Customer;
import entity.Staff;
import entity.abstraction.User;
import services.abstraction.UserService;
import utils.display.AccountDisplay;

import java.util.List;
import java.util.Scanner;

public class AdminService extends UserService {
    private static final AdminService instance = new AdminService();

    private AdminService() {
    }

    public static AdminService getInstance() {
        return instance;
    }


//    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
//        AccountDisplay.getInstance().displaySignUp("customer");
//    }

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
        List<User> users = UserService.airLinesCompanyList;
        System.out.println("Total partner: " + users.size());
        users.forEach(user -> {
            AirLinesCompany airlines = (AirLinesCompany) user;
            System.out.println(airlines);
        });
    }

    public void checkUser() {
        List<User> users = UserService.customerList;
        System.out.println("Total user: " + users.size());
        System.out.println("Show all user? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.next();
        switch (confirm) {
            case "y", "Y", "yes":
                users.forEach(user -> {
                    Customer customer = (Customer) user;
                    System.out.println(customer);
                });
            default:
        }
    }
}
