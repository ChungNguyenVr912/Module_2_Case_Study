package builder;

import builder.abstraction.AbstractUserBuilder;
import entity.Customer;

public class CustomerBuilder extends AbstractUserBuilder {
    private long citizenID;
    private String gender;

    @Override
    public CustomerBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public CustomerBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    @Override
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
