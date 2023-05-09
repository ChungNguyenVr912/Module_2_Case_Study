package utils.display;

import entity.user_impl.Customer;
import services.BookingService;
import services.abstraction.UserService;
import services.impl.CustomerService;
import utils.ValidateInput;


public class CustomerView {

    public static void displayCustomerView() {
        while (true) {
            System.out.println("""
                    1. Start booking
                    2. View booked ticket
                    3. Update information
                    4. Logout
                    """);
            int option = ValidateInput.validateInteger();
            switch (option) {
                case 1 -> BookingService.displayBookingView();
                case 2 -> CustomerService.manageTicket();
                case 3 -> displayUpdateInformation();
                case 4 -> {
                    UserService.setCurrentUser(null);
                    UserService.setStatus("");
                    return;
                }
            }
        }
    }

    public static void displayUpdateInformation() {
        Customer customer = (Customer) UserService.getCurrentUser();
        while (true) {
            System.out.println("1. Update password");
            System.out.println("2. Update name - " + customer.getFullName());
            System.out.println("3. Update phone number - " + customer.getPhoneNumber());
            System.out.println("4. Update citizen ID - " + customer.getCitizenID());
            System.out.println("5. Update day of birth - " + customer.getDayOfBirth());
            System.out.println("6. Phẫu thuật chuyển giới(Not recommended!) - " + customer.getGender());
            System.out.println("7. Back to reality");
            int option = ValidateInput.validateInteger();
            if (option == 7) {
                return;
            } else {
                CustomerService.updateInfo(option);
            }
        }
    }
}

