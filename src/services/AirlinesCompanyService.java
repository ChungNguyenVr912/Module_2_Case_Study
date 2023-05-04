package services;

import entity.abstraction.User;
import factory.UserFactory;
import services.abstraction.UserService;
import utils.DataWriter;

public class AirlinesCompanyService extends UserService {
    private static final AirlinesCompanyService instance = new AirlinesCompanyService();
    private AirlinesCompanyService(){}

    public static AirlinesCompanyService getInstance() {
        return instance;
    }


    public void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)) {
            User newAirlines = UserFactory.getInstance()
                    .getUser("airlines", fullName, email, passWord, phoneNumber);
            DataWriter.storeRegisteredUser(newAirlines, AIRLINES_DATA_URL);
            UserService.updateUserList();
        } else {
            status = "Email has been used!";
        }
    }
}
