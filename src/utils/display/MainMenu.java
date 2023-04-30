package utils.display;

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
            case 3 -> System.out.println("Coming soon!");
        }
        System.out.println(UserService.getStatus());
        try {
            System.out.println("Hello " + UserService.getCurrentUser().getFullName());
        }catch (NullPointerException e){
            System.err.println("Ối dồi ôi!\n");
        }
        UserService.displayViewByUser();
    }
}
