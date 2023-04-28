package utils.display;

import services.AirlinesCompanyService;
import services.CustomerService;
import services.StaffService;
import services.abstraction.UserService;
import utils.ValidateInput;

import java.util.Scanner;

public class LoginDisplay {
    private static final LoginDisplay instance = new LoginDisplay();
    private LoginDisplay(){}
    public static LoginDisplay getInstance(){
        return instance;
    }
    private final Scanner scanner = new Scanner(System.in);
    public void displaySignUp(String userType){
        System.out.println("Enter your full name: ");
        String fullName = scanner.next();
        System.out.println("Enter your email: ");
        String email = ValidateInput.validateEmail();
        System.out.println("Enter your passWord: ");
        String passWord = ValidateInput.validatePassword();
        System.out.println("Enter your phone number: ");
        long phoneNumber = ValidateInput.validateLong();
        switch (userType){
            case "customer" -> CustomerService.getInstance().createUser(fullName, email, passWord, phoneNumber);
            case "staff" -> StaffService.getInstance().createUser(fullName, email, passWord, phoneNumber);
            case "airlines" -> AirlinesCompanyService.getInstance().createUser(fullName, email, passWord, phoneNumber);
        }
    }

    public void displaySignIn(){
        System.out.println("Enter your email:");
        String email = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        System.out.println();
        UserService.signIn(email, password);
        System.out.println(UserService.getStatus());
    }
}
