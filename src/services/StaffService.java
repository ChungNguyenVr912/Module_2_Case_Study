package services;

import entity.abstraction.User;
import factory.UserFactory;
import services.abstraction.UserService;
import utils.DataWriter;

public class StaffService extends UserService{
    private final static StaffService instance = new StaffService();
    public static StaffService getInstance() {
        return instance;
    }
    private StaffService(){}

    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)){
            User newStaff = UserFactory.getInstance()
                    .getUser("staff", fullName, passWord, email, phoneNumber);
            DataWriter.storeRegisteredUser(newStaff, STAFF_DATA_URL);
            UserService.updateUserList();
        }
    }

}
