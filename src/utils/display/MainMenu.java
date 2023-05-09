package utils.display;

import services.BookingService;
import services.abstraction.UserService;
import utils.ValidateInput;

public class MainMenu {
    private static final MainMenu instance = new MainMenu();
    private MainMenu(){}

    public static MainMenu getInstance() {
        return instance;
    }
    public void displayMainMenu(){
        System.out.println("""
                1. Sign in
                2. Sign up
                3. Start booking
                """);
        int input = ValidateInput.validateInteger();
        switch (input){
            case 1 -> AccountDisplay.getInstance().displaySignIn();
            case 2 -> AccountDisplay.getInstance().displaySignUp("customer");
            case 3 -> BookingService.displayBookingView();
        }
        System.out.println(UserService.getStatus());
        if (UserService.getCurrentUser() != null){
            System.out.print("Hello " + UserService.getCurrentUser().getFullName());
            System.out.println("\uD83D\uDE00");
        }
        UserService.displayViewByUser();
    }
}
