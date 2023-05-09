package builder;

import builder.abstraction.UserBuilder;
import entity.user_impl.Customer;

public class CustomerBuilder extends UserBuilder {
    private long citizenID;
    private String gender;

    public CustomerBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public CustomerBuilder setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public CustomerBuilder setCitizenID(long citizenID) {
        this.citizenID = citizenID;
        return this;
    }

    public Customer build() {
        return new Customer(fullName, email, passWord, phoneNumber);
    }
}
