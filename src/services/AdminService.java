package services;

import entity.Customer;
import entity.Staff;
import entity.abstraction.User;
import services.abstraction.UserService;
import services.abstraction.UserServiceInterface;
import utils.display.LoginDisplay;

import java.util.List;
import java.util.Scanner;

public class AdminService extends UserService implements UserServiceInterface {
    private static final AdminService instance = new AdminService();

    private AdminService() {
    }

    public static AdminService getInstance() {
        return instance;
    }

    @Override
    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
        LoginDisplay.getInstance().displaySignUp("customer");
    }

    public void addStaff() {
        LoginDisplay.getInstance().displaySignUp("staff");
    }
    public void addAirlinesCompany() {
        LoginDisplay.getInstance().displaySignUp("airlines");
    }

    public void viewStaffList() {
        List<User> users = UserService.staffList;
        System.out.println("Total staff: " + users.size());
        users.forEach(user -> {
            Staff staff = (Staff) user;
            System.out.println(staff);
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
