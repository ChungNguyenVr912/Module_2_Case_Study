package utils.display;

import services.abstraction.UserService;
import services.impl.AirlinesService;
import utils.ValidateInput;

public class AirlinesView {
    public static void displayAirlinesView(){
        while (true){
            System.out.println("""
                1. Add flight
                2. Update flight
                3. View flight history
                4. Logout
                """);
            int input = ValidateInput.validateInteger();
            switch (input){
                case 1-> AirlinesService.addFlight();
                case 2-> AirlinesService.updateFlight();
                case 3-> AirlinesService.viewFlightHistory();
                case 4-> {
                    UserService.setCurrentUser(null);
                    UserService.setStatus("");
                    return;
                }
            }
        }

    }
}
