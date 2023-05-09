package factory;

import builder.AirLinesBuilder;
import builder.CustomerBuilder;
import builder.StaffBuilder;
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
                return new StaffBuilder()
                        .setFullName(fullName)
                        .setEmail(email)
                        .setPassWord(passWord)
                        .setPhoneNumber(phoneNumber)
                        .setAge(age)
                        .setApplyDay(applyDay)
                        .build();
            }
            case "customer" -> {
                return new CustomerBuilder()
                        .setFullName(fullName)
                        .setEmail(email)
                        .setPassWord(passWord)
                        .setPhoneNumber(phoneNumber)
                        .build();
            }
            case "airlines" -> {
                System.out.println("Enter price multi:");
                double priceMulti = ValidateInput.validateDouble();
                return new AirLinesBuilder()
                        .setFullName(fullName)
                        .setEmail(email)
                        .setPassWord(passWord)
                        .setPhoneNumber(phoneNumber)
                        .setPriceMulti(priceMulti)
                        .build();
            }
            default -> {
                return null;
            }
        }
    }
}