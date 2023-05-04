package services;

import entity.abstraction.User;
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

    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)) {
            User newCustomer = UserFactory.getInstance()
                    .getUser("customer", fullName, email, passWord, phoneNumber);
            DataWriter.storeRegisteredUser(newCustomer, CUSTOMER_DATA_URL);
            UserService.updateUserList();
        } else {
            status = "Email has been used!";
        }
    }

}
