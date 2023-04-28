package factory;

import builder.director.UserDirector;
import entity.abstraction.User;
import factory.abstraction.AbstractFactory;
import utils.DateTime;
import utils.ValidateInput;

import java.util.Scanner;

public class UserFactory implements AbstractFactory {
    private static final UserFactory instance = new UserFactory();

    private UserFactory() {
    }

    public static UserFactory getInstance() {
        return instance;
    }

    private final Scanner scanner = new Scanner(System.in);

    public User getUser(String userType, String fullName, String email, String passWord, long phoneNumber) {
        switch (userType) {
            case "staff" -> {
                System.out.println("Enter age: ");
                int age = ValidateInput.validateInteger();
                String applyDay = DateTime.getToday();
                return UserDirector.buildStaff(fullName, email, passWord, phoneNumber, age, applyDay);
            }
            case "customer" -> {
                return UserDirector.buildCustomer(fullName, email, passWord, phoneNumber);
            }
            case "airlines" -> {
                return UserDirector.buildAirLines(fullName, email, passWord, phoneNumber);
            }
            default -> {
                return null;
            }
        }
    }
}