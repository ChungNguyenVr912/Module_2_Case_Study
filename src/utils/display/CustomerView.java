package utils.display;

import entity.Customer;
import services.BookingService;
import services.abstraction.UserService;
import utils.ValidateInput;

import java.util.Scanner;


public class CustomerView {

    public static void displayCustomerView() {
        while (true) {
            System.out.println("""
                    1. Start booking
                    2. Update information
                    3. Logout
                    """);
            int option = ValidateInput.validateInteger();
            switch (option) {
                case 1 -> BookingService.displayBookingView();
                case 2 -> displayUpdateInformation();
                case 3 -> {
                    UserService.setCurrentUser(null);
                    return;
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    private static void displayUpdateInformation() {
        while (true) {
            System.out.println("""
                    1. Change name
                    2. Change password
                    3. Change phone number
                    4. Change Citizen ID
                    5. Phẫu thuật chuyển giới(Not recommended!)
                    6. Back to reality
                    """);
            int option = ValidateInput.validateInteger();
            if (option == 6) {
                return;
            } else {
                updateInfo(option);
            }
        }
    }

    private static void updateInfo(int option) {
        String name = "";
        String password = "";
        String oldPassword;
        long phoneNumber = 0;
        long citizenID = 0;
        String gender = "";
        switch (option) {
            case 1 -> {
                System.out.println("Enter new name:");
                name = scanner.nextLine();
            }
            case 2 -> {
                System.out.println("Enter new password:");
                password = ValidateInput.validatePassword();
            }
            case 3 -> {
                System.out.println("Enter new phone number:");
                phoneNumber = ValidateInput.validateLong();
            }
            case 4 -> {
                System.out.println("Enter new citizen ID:");
                citizenID = ValidateInput.validateLong();
            }
            case 5 -> {
                System.out.println("Enter gender u want to transgendered:");
                gender = scanner.nextLine();
                System.out.println("Good luck to you!");
            }
        }
        System.out.println("Enter your current password:");
        oldPassword = scanner.nextLine();
        if (oldPassword.equals(UserService.getCurrentUser().getPassWord())){
            switch (option) {
                case 1 -> UserService.getCurrentUser().setFullName(name);
                case 2 -> UserService.getCurrentUser().setPassWord(password);
                case 3 -> UserService.getCurrentUser().setPhoneNumber(phoneNumber);
                case 4 -> ((Customer)UserService.getCurrentUser()).setCitizenID(citizenID);
                case 5 -> ((Customer)UserService.getCurrentUser()).setGender(gender);
            }
        }else {
            System.out.println("Wrong password!");
            return;
        }
        UserService.updateCustomerInfoToFile();
    }
}

