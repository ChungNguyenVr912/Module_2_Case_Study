package utils.display;

import services.abstraction.UserService;
import utils.ValidateInput;


public class CustomerView {

    public static void displayCustomerView() {
        System.out.println("""
                1. Start booking
                2. Update information
                3. Logout
                """);
        int option = ValidateInput.validateInteger();
        switch (option){
            case 1 -> BookingView.displayBookingView();
            case 2 -> displayUpdateInformation();
            case 3 -> UserService.setCurrentUser(null);
        }
    }

    private static void displayUpdateInformation() {
        System.out.println("""
                
                
                """);
    }
}

