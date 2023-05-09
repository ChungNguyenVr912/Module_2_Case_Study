package services.impl;

import entity.abstraction.User;
import factory.UserFactory;
import services.abstraction.UserService;

public class StaffService extends UserService{

    public static void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)){
            User newStaff = UserFactory.getInstance()
                    .getUser("staff", fullName, passWord, email, phoneNumber);
            staffList.add(newStaff);
            UserService.updateUserList();
        }
    }

}
