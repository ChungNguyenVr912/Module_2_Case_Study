package services;

import factory.UserFactory;
import services.abstraction.UserService;
import utils.DataWriter;

public class CustomerService extends UserService {
    private static final CustomerService instance = new CustomerService();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        return instance;
    }

    @Override
    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)) {
            currentUser = UserFactory.getInstance()
                    .getUser("customer", fullName, email, passWord, phoneNumber);
            DataWriter.storeRegisteredUser(currentUser, USER_DATA_URL);
            UserService.updateUserList();
        } else {
            status = "Email has been used!";
        }
    }

}
