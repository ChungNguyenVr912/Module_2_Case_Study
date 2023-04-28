package builder.abstraction;

public interface UserBuilder {
    UserBuilder setFullName(String fullName);
    UserBuilder setEmail(String email);
    UserBuilder setPassWord(String passWord);
    UserBuilder setPhoneNumber(long phoneNumber);
}
