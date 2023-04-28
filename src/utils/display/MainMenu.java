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
            case 1 -> LoginDisplay.getInstance().displaySignIn();
            case 2 -> LoginDisplay.getInstance().displaySignUp("customer");
        }
        UserService.displayViewByUser();
    }
}
