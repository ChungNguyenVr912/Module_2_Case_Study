package utils.display;

import services.AirlinesCompanyService;
import services.CustomerService;
import services.StaffService;
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
        System.out.print("Enter your full name: ");
        scanner.nextLine();
        String fullName = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = ValidateInput.validateEmail();
        System.out.print("Enter your passWord: ");
        String passWord = ValidateInput.validatePassword();
        System.out.print("Enter your phone number: ");
        long phoneNumber = ValidateInput.validateLong();
        switch (userType){
            case "customer" -> CustomerService.getInstance().createUser(fullName, email, passWord, phoneNumber);
            case "staff" -> StaffService.getInstance().createUser(fullName, email, passWord, phoneNumber);
            case "airlines" -> AirlinesCompanyService.getInstance().createUser(fullName, email, passWord, phoneNumber);
        }
        if (userType.equals("customer")){
        UserService.signIn(email,passWord);}
    }

    public void displaySignIn(){
        System.out.println("Enter your email:");
        String email = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        System.out.println("\r");
        UserService.signIn(email, password);
//        System.out.println(UserService.getStatus());
//        try {
//        System.out.println("Hello " + UserService.getCurrentUser().getFullName());
//        }catch (NullPointerException e){
//            System.err.println("Ối dồi ôi!\n");
//        }
    }
}
