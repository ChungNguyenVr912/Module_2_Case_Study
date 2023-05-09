package utils.display;

import services.impl.AdminService;
import services.abstraction.UserService;
import utils.ValidateInput;

import java.util.Scanner;

public class AdminView {
    public static void displayAdminView() {
        System.out.print("""
                1. Register new Staff
                2. Register new Airlines Company
                3. View Airlines Company list
                4. View Staff list
                5. View user list
                6. View report
                7. Logout
                """);
        int option = ValidateInput.validateInteger();
        switch (option) {
            case 1 -> AdminService.getInstance().addStaff();
            case 2 -> AdminService.getInstance().addAirlinesCompany();
            case 3 -> AdminService.getInstance().viewAirlinesList();
            case 4 -> AdminService.getInstance().viewStaffList();
            case 5 -> AdminService.getInstance().checkUser();
            case 6 -> viewReport();
            case 7 -> {
                UserService.setCurrentUser(null);
                UserService.setStatus("");
                return;
            }
        }
        displayAdminView();
    }

    private static void viewReport() {
        while (true) {
            System.out.println("""
                    Revenue Report:
                                    
                    1. Day report
                    2. Month report
                    3. Year report
                    4. All report
                    5. Back
                    """);
            int option = ValidateInput.validateInteger();
            switch (option) {
                case 1 -> System.out.println("hehe");
                case 2 -> System.out.println("hehehe");
                case 3 -> System.out.println("hehehehe");
                case 4 -> AdminService.getInstance().viewAllReport();
                case 5 -> {
                    return;
                }
            }

        }
    }
}
