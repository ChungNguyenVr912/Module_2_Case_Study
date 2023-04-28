package factory.abstraction;

import entity.abstraction.User;

public interface AbstractFactory {
    User getUser(String userType, String fullName, String passWord, String email, long phoneNumber);
}
