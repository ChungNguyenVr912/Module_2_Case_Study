package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]{3}[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}$";

    public static int validateInteger() {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input!\uD83E\uDD23");
            }
        }
    }

    public static long validateLong() {
        long input;
        while (true) {
            try {
                input = Long.parseLong(scanner.nextLine());
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public static double validateDouble() {
        double input;
        while (true) {
            try {
                input = Double.parseDouble(scanner.nextLine());
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input! ");
            }
        }
    }

    public static String validateEmail() {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        do {
            String email = scanner.nextLine();
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
            String passWord = scanner.nextLine();
            Matcher matcher = pattern.matcher(passWord);
            if (matcher.matches()) {
                return passWord;
            } else {
                System.out.println("Invalid password! (As least 8 characters; include uppercase, lowercase, number and symbol)");
            }
        } while (true);
    }

    public static LocalDate validateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("Invalid date!");
            }
        } while (true);
    }
    public static LocalDateTime validateDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        do {
            String input = scanner.nextLine();
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        } while (true);
    }
}
