package factory;

import builder.director.UserDirector;
import entity.abstraction.User;
import utils.MyDateTime;
import utils.ValidateInput;

public class UserFactory {
    private static final UserFactory instance = new UserFactory();

    private UserFactory() {
    }

    public static UserFactory getInstance() {
        return instance;
    }

    public User getUser(String userType, String fullName, String email, String passWord, long phoneNumber) {
        switch (userType) {
            case "staff" -> {
                System.out.print("Enter age: ");
                int age = ValidateInput.validateInteger();
                String applyDay = MyDateTime.getToday();
                return UserDirector.buildStaff(fullName, email, passWord, phoneNumber, age, applyDay);
            }
            case "customer" -> {
                return UserDirector.buildCustomer(fullName, email, passWord, phoneNumber);
            }
            case "airlines" -> {
                System.out.print("Enter price multi:");
                double priceMulti = ValidateInput.validateDouble();
                return UserDirector.buildAirLines(fullName, email, passWord, phoneNumber, priceMulti);
            }
            default -> {
                return null;
            }
        }
    }
}