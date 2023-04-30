package builder;

import builder.abstraction.AbstractUserBuilder;
import entity.AirLinesCompany;

public class AirLinesBuilder extends AbstractUserBuilder {
    @Override
    public AirLinesBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public AirLinesBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public AirLinesBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    @Override
    public AirLinesBuilder setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public AirLinesCompany build(){
        return new AirLinesCompany(fullName,email, passWord, phoneNumber);
    }
}
