package services.abstraction;

import entity.user_impl.Admin;
import entity.user_impl.AirLinesAccount;
import entity.user_impl.Customer;
import entity.user_impl.Staff;
import entity.abstraction.User;
import utils.DataReader;
import utils.DataWriter;
import utils.display.AdminView;
import utils.display.AirlinesView;
import utils.display.CustomerView;
import utils.display.StaffView;
import java.util.ArrayList;
import java.util.List;

public abstract class UserService  {
    protected static final String CUSTOMER_DATA_URL = "src/data/customers.csv";
    protected static final String STAFF_DATA_URL = "src/data/staffs.csv";
    protected static final String AIRLINES_DATA_URL = "src/data/airlines.csv";
    protected static List<User> customerList;
    protected static List<User> staffList;
    protected static List<User> airLinesList;
    protected static User currentUser;
    protected static String status = "Please login!";

    static {
        customerList = DataReader.getRegisteredUser(CUSTOMER_DATA_URL);
        staffList = DataReader.getRegisteredUser(STAFF_DATA_URL);
        airLinesList = DataReader.getRegisteredUser(AIRLINES_DATA_URL);
    }

    public static void updateUserList() {
        boolean success =
        DataWriter.updateUserList(customerList,CUSTOMER_DATA_URL)&&
        DataWriter.updateUserList(staffList,STAFF_DATA_URL)&&
        DataWriter.updateUserList(airLinesList,AIRLINES_DATA_URL);
        String status = success ? "Update success!" : "Update fail!";
        System.out.println(status);
    }

    public static List<User> getCustomerList() {
        return customerList;
    }

    public static List<User> getStaffList() {
        return staffList;
    }

    public static List<User> getAirLinesList() {
        return airLinesList;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserService.currentUser = currentUser;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        UserService.status = status;
    }

    public static void signIn(String email, String passWord) {
        List<User> users = getAllUser();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassWord().equals(passWord)) {
                currentUser = user;
                if (currentUser instanceof Admin) {
                    status = "Login success as ADMIN";
                } else if (currentUser instanceof Staff) {
                    status = "Login success! (Staff)";
                } else {
                    status = "Login success!";
                }
                return;
            }
        }
        status = "Wrong email or password!";
    }

    public static void displayViewByUser() {
        if (currentUser instanceof Customer) {
            CustomerView.displayCustomerView();
        } else if (currentUser instanceof Staff) {
            StaffView.displayStaffView();
        } else if (currentUser instanceof AirLinesAccount) {
            AirlinesView.displayAirlinesView();
        } else if (currentUser instanceof Admin) {
            AdminView.displayAdminView();
        }
    }


    public static boolean checkValidEmail(String email) {
        List<User> users = getAllUser();
        for (User user : users) {
            if (user.getEmail().equals(email))
                return false;
        }
        return true;
    }
    public static List<User> getAllUser(){
        List<User> users;
        try {
            users = new ArrayList<>(customerList);
        }catch (Exception e){
            System.err.println("Read customer list fail!");
            users = new ArrayList<>();
        }
        try {
            users.addAll(staffList);
        }catch (Exception e){
            System.out.println("Read staff list fail!");
            users = new ArrayList<>();
        }
        try {
            users.addAll(airLinesList);
        }catch (Exception e){
            System.out.println("Read partner list fail!");
            users = new ArrayList<>();
        }
        users.addAll(DataReader.getRegisteredUser("src/data/admin.csv"));
        return users;
    }
}
