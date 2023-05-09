package utils.display;

import services.impl.AirlinesService;
import services.impl.CustomerService;
import services.impl.StaffService;
import services.abstraction.UserService;
import utils.ValidateInput;

import java.util.Scanner;

public class AccountDisplay {
    private static final AccountDisplay instance = new AccountDisplay();
    private AccountDisplay(){}
    public static AccountDisplay getInstance(){
        return instance;
    }
    private final Scanner scanner = new Scanner(System.in);
    public void displaySignUp(String userType){
        System.out.println("Enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = ValidateInput.validateEmail();
        System.out.println("Enter your passWord: ");
        String passWord = ValidateInput.validatePassword();
        System.out.println("Enter your phone number: ");
        long phoneNumber = ValidateInput.validateLong();
        switch (userType){
            case "customer" -> CustomerService.createUser(fullName, email, passWord, phoneNumber);
            case "staff" -> StaffService.createUser(fullName, email, passWord, phoneNumber);
            case "airlines" -> AirlinesService.createUser(fullName, email, passWord, phoneNumber);
        }
//        if (userType.equals("customer")){
//        UserService.signIn(email,passWord);}
    }

    public void displaySignIn(){
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        UserService.signIn(email, password);
    }
}
