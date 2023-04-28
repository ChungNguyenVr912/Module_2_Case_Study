package services.abstraction;

public interface UserServiceInterface {

    void createUser(String fullName, String email, String passWord, long phoneNumber);
}
