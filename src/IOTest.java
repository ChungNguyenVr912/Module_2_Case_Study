import builder.director.UserDirector;
import entity.Admin;
import entity.Customer;
import entity.Staff;
import entity.abstraction.User;
import factory.UserFactory;
import services.AdminService;
import services.abstraction.UserService;
import utils.DataReader;
import utils.DataWriter;
import utils.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IOTest {
    public static void main(String[] args) {
        System.out.println(DateTime.getToday());

//        Staff staff = new Staff("Huy", "1235", "Huystaff@gmail.com", 8412222213L);
//        DataWriter.storeRegisteredUser(staff, "src/data/staffs.csv");
//        List<User> userList = DataReader.getRegisteredUser("src/data/staffs.csv");
//        userList.forEach(user -> System.out.println(user.getFullName() + "\n" + user.getEmail()));
//        AdminService.getInstance().addStaff();
//        AdminService.getInstance().viewStaffList();
//        AdminService.getInstance().checkUser();
//        List<User> userList = DataReader.getRegisteredUser("src/data/admin.csv");
//        System.out.println(userList.get(0));
//        Customer customer = UserDirector.
//                buildCustomer("Chung", "chung219@gmail.com", "Aaa111@1", 8412312383L, "male", 27394729347L);
//        DataWriter.storeRegisteredUser(customer, "src/data/customers.csv");

    }
}

