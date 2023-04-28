package services;

import factory.UserFactory;
import services.abstraction.UserService;
import utils.DataWriter;

public class AirlinesCompanyService extends UserService {
    private static final AirlinesCompanyService instance = new AirlinesCompanyService();
    private AirlinesCompanyService(){}

    public static AirlinesCompanyService getInstance() {
        return instance;
    }

    @Override
    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)) {
            currentUser = UserFactory.getInstance()
                    .getUser("airlines", fullName, email, passWord, phoneNumber);
            DataWriter.storeRegisteredUser(currentUser, AIRLINES_DATA_URL);
            UserService.updateUserList();
        } else {
            status = "Email has been used!";
        }
    }
}
