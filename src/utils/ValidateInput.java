package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]{3}[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}$";

    public static int validateInteger() {
        int input;
        while (true){
            try {
                input = scanner.nextInt();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.next();
            }
        }
    }

    public static long validateLong() {
        long input;
        while (true) {
            try {
                input = scanner.nextLong();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.next();
            }
        }

    }

    public static String validateEmail() {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        do {
            String email = scanner.next();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return email;
            } else {
                System.out.println("Invalid email!");
            }
        } while (true);
    }

    public static String validatePassword() {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        do {
            String passWord = scanner.next();
            Matcher matcher = pattern.matcher(passWord);
            if (matcher.matches()) {
                return passWord;
            } else {
                System.out.println("Invalid password! (As least 8 characters; include uppercase, lowercase, number and symbol)");
            }
        } while (true);
    }
}
