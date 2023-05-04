package builder.director;

import builder.AirLinesBuilder;
import builder.CustomerBuilder;
import builder.StaffBuilder;
import entity.AirLinesCompany;
import entity.Customer;
import entity.Staff;

public class UserDirector {
    public static Customer buildCustomer(String fullName, String email, String passWord, long phoneNumber) {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        return customerBuilder
                .setFullName(fullName)
                .setEmail(email)
                .setPassWord(passWord)
                .setPhoneNumber(phoneNumber)
                .build();
    }
    public static Staff buildStaff(String fullName, String email, String passWord, long phoneNumber, int age, String applyDay){
        StaffBuilder staffBuilder = new StaffBuilder();
        return staffBuilder
                .setFullName(fullName)
                .setEmail(email)
                .setPassWord(passWord)
                .setPhoneNumber(phoneNumber)
                .setAge(age)
                .setApplyDay(applyDay)
                .build();
    }
    public static AirLinesCompany buildAirLines(String fullName, String email, String passWord, long phoneNumber, double priceMulti){
        AirLinesBuilder staffBuilder = new AirLinesBuilder();
        return staffBuilder
                .setFullName(fullName)
                .setEmail(email)
                .setPassWord(passWord)
                .setPhoneNumber(phoneNumber)
                .setPriceMulti(priceMulti)
                .build();
    }
}
