package builder;

import builder.abstraction.UserBuilder;
import entity.user_impl.AirLinesAccount;

public class AirLinesBuilder extends UserBuilder {
    private double priceMulti;

    public AirLinesBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public AirLinesBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public AirLinesBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public AirLinesBuilder setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public AirLinesBuilder setPriceMulti(double priceMulti){
        this.priceMulti = priceMulti;
        return this;
    }
    public AirLinesAccount build(){
        return new AirLinesAccount(fullName,email, passWord, phoneNumber, priceMulti);
    }
}
