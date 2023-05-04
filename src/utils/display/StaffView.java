package utils.display;

import services.BookingService;
import services.abstraction.UserService;
import utils.ValidateInput;

public class StaffView {
    public static void displayStaffView() {
        System.out.println("""
                
                1. Representative booking
                2. Revenue report
                3. Logout
                """);
        int option = ValidateInput.validateInteger();
        switch (option){
            case 1 -> BookingService.displayBookingView();
            case 2 -> System.out.println("ehe, con cai nit");
            case 3 -> {UserService.setCurrentUser(null);
                return;}
        }
        displayStaffView();
    }
}
