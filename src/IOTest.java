import builder.FlightConcreteBuilder;
import builder.abstraction.FlightBuilder;
import entity.abstraction.Airlines;
import services.FlightService;
import services.abstraction.UserService;
import utils.MyDateTime;
import utils.RandomEverything;

import java.io.IOException;
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
//        DataWriter.storeRegisteredUser(Admin.getInstance(),"src/data/admin.csv");
//        System.out.println(userList.get(0));
//        Customer customer = UserDirector.
//                buildCustomer("Chung", "chung@gmail.com", "Aaa111@1", 8412312383L);
//        DataWriter.storeRegisteredUser(customer, "src/data/customers.csv");
//        AirLinesCompany airLinesCompany = UserDirector
//                .buildAirLines("ChungPhungPhat AirLines", "cppairlines@gmail.com","cpp", 84900900900L);
//        BookingService.displayBookingView();
//        System.out.println(generateFlightCode());
//        RandomEverything.randomCrew().forEach(System.out::println);
//        FlightService.airPortUnrealDistance.forEach((key,value) -> System.out.println(key + value));
//        String[] route = RandomEverything.randomLocation();
//        System.out.println(route[0]);
//        System.out.println(route[1]);
        FlightService.getFlightList().clear();
        FlightService.getFlightList().addAll(RandomEverything.randomFlights(2000));
        FlightService.updateFlightList();
        System.out.println(MyDateTime.getToday());
    }

    public static String generateFlightCode() {
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher("Chung Phung Phat Airlines");
        LocalDateTime departTime = LocalDateTime.now();
        while (matcher.find()) {
            result.append(matcher.group());
        }
        result.append(MyDateTime.toDayAndMonth(departTime))
                .append("-").append(departTime.getDayOfWeek());
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

    public static void flightTest() {
        FlightBuilder builder = new FlightConcreteBuilder();
        Airlines provider = (Airlines) UserService.getAirLinesList().get(0);
//        builder.setProvider(provider)
//                .setAirplane(new AirPlane())
//                .setDeparture("SGN")
//                .setDestination("HAN")
//                .s
    }
}

