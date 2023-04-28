package services.abstraction;

import entity.Admin;
import entity.AirLinesCompany;
import entity.Customer;
import entity.Staff;
import entity.abstraction.User;
import utils.DataReader;
import utils.display.AdminView;
import utils.display.CustomerView;
import utils.display.StaffView;
import java.util.ArrayList;
import java.util.List;

public abstract class UserService implements UserServiceInterface {
    protected static final String USER_DATA_URL = "src/data/customers.csv";
    protected static final String STAFF_DATA_URL = "src/data/staffs.csv";
    protected static final String AIRLINES_DATA_URL = "src/data/airlines.csv";
    protected static List<User> customerList;
    protected static List<User> staffList;
    protected static List<User> airLinesCompanyList;
    protected static User currentUser;
    protected static String status;

    static {
        customerList = DataReader.getRegisteredUser(USER_DATA_URL);
        staffList = DataReader.getRegisteredUser(STAFF_DATA_URL);
        airLinesCompanyList = DataReader.getRegisteredUser(AIRLINES_DATA_URL);
    }

    public static void updateUserList() {
        customerList = DataReader.getRegisteredUser(USER_DATA_URL);
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
        List<User> users = new ArrayList<>(customerList);
        users.addAll(staffList);
        users.addAll(DataReader.getRegisteredUser("src/data/admin.csv"));
        status = "Wrong email or password!";
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
                break;
            }
        }
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
        if (customerList == null || staffList == null) {
            return true;
        }
        for (User user : customerList) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        for (User staff : staffList) {
            if (staff.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}
