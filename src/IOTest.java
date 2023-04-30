import services.FlightService;
import utils.DataReader;
import utils.DateTime;
import utils.display.BookingView;

import java.io.IOException;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOTest {
    public static void main(String[] args) throws IOException {

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
//                buildCustomer("Chung", "chung@gmail.com", "Aaa111@1", 8412312383L);
//        DataWriter.storeRegisteredUser(customer, "src/data/customers.csv");
//        AirLinesCompany airLinesCompany = UserDirector
//                .buildAirLines("ChungPhungPhat AirLines", "cppairlines@gmail.com","cpp", 84900900900L);
        System.out.println(generateFlightCode());
        System.out.println(DateTime.getToday());
        BookingView.displayBookingView();
    }
    public static String generateFlightCode() {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher("Chung Phung Phat Airlines");
        LocalDateTime departTime = LocalDateTime.now();
        while (matcher.find()) {
            result.append(matcher.group());
        }
        result.append(departTime.getDayOfMonth()).append(departTime.getMonthValue())
                .append("_").append(departTime.getDayOfWeek());
        result.append("_");
        matcher = pattern.matcher("HaNoi");
        while (matcher.find()) {
            result.append(matcher.group());
        }
        result.append("-");
        matcher = pattern.matcher("HoChiMinh");
        while (matcher.find()) {
            result.append(matcher.group());
        }
        return result.toString();
    }

}

