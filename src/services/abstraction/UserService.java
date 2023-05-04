package services.abstraction;

import entity.Admin;
import entity.AirLinesCompany;
import entity.Customer;
import entity.Staff;
import entity.abstraction.User;
import utils.DataReader;
import utils.DataWriter;
import utils.display.AdminView;
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
    protected static List<User> airLinesCompanyList;
    protected static User currentUser;
    protected static String status = "Please login!";

    static {
        customerList = DataReader.getRegisteredUser(CUSTOMER_DATA_URL);
        staffList = DataReader.getRegisteredUser(STAFF_DATA_URL);
        airLinesCompanyList = DataReader.getRegisteredUser(AIRLINES_DATA_URL);
    }

    public static void updateUserList() {
        customerList = DataReader.getRegisteredUser(CUSTOMER_DATA_URL);
        staffList = DataReader.getRegisteredUser(STAFF_DATA_URL);
        airLinesCompanyList = DataReader.getRegisteredUser(AIRLINES_DATA_URL);
    }

    public static List<User> getCustomerList() {
        return customerList;
    }

    public static List<User> getStaffList() {
        return staffList;
    }

    public static List<User> getAirLinesCompanyList() {
        return airLinesCompanyList;
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
        } else if (currentUser instanceof AirLinesCompany) {

        } else if (currentUser instanceof Admin) {
            AdminView.displayAdminView();
        }
    }


    public boolean checkValidEmail(String email) {
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
            users.addAll(airLinesCompanyList);
        }catch (Exception e){
            System.out.println("Read partner list fail!");
            users = new ArrayList<>();
        }
        users.addAll(DataReader.getRegisteredUser("src/data/admin.csv"));
        return users;
    }

    public static void updateCustomerInfoToFile(){
        DataWriter.updateUserInfoToUserList(customerList, CUSTOMER_DATA_URL);
    }
}
