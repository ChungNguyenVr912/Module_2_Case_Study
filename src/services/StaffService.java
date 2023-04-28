package services;

import factory.UserFactory;
import services.abstraction.UserService;
import services.abstraction.UserServiceInterface;
import utils.DataWriter;

public class StaffService extends UserService implements UserServiceInterface {
    private final static StaffService instance = new StaffService();
    public static StaffService getInstance() {
        return instance;
    }
    private StaffService(){}

    @Override
    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)){
            currentUser = UserFactory.getInstance()
                    .getUser("staff", fullName, passWord, email, phoneNumber);
            DataWriter.storeRegisteredUser(currentUser, STAFF_DATA_URL);
            UserService.updateUserList();
        }
    }

}
