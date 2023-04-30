package utils.display;

import services.AdminService;
import services.abstraction.UserService;
import utils.ValidateInput;

import java.util.Scanner;

public class AdminView {
    public static void displayAdminView(){
        System.out.println("""
                
                1. Register new Staff
                2. Register new Airlines Company
                3. View Airlines Company list
                4. View Staff list
                5. View user list
                6. View report
                7. Logout
                """);
        int option = ValidateInput.validateInteger();
        switch (option){
            case 1 -> AdminService.getInstance().addStaff();
            case 2 -> AdminService.getInstance().addAirlinesCompany();
            case 3 -> AdminService.getInstance().viewAirlinesList();
            case 4 -> AdminService.getInstance().viewStaffList();
            case 5 -> AdminService.getInstance().checkUser();
            case 6 -> viewReport();
            case 7 -> {UserService.setCurrentUser(null);
                        return;}
        }
        displayAdminView();
    }

    private static void viewReport() {
        while (true){
            System.out.println("""
                Revenue Report:
                
                1. This month
                2. This Quarter
                3. This year
                4. All
                5. Back
                """);
            int option = ValidateInput.validateInteger();
            switch (option){
                case 1 -> System.out.println("hehe");
                case 2 -> System.out.println("hehehe");
                case 3 -> System.out.println("hehehehe");
                case 5 -> {
                    return;
                }
        }

        }
    }
}
