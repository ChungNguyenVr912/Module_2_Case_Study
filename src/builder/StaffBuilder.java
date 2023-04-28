package builder;

import builder.abstraction.AbstractUserBuilder;
import entity.Staff;

public class StaffBuilder extends AbstractUserBuilder {
    private int age;
    private String applyDay;

    @Override
    public StaffBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public StaffBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public StaffBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    @Override
    public StaffBuilder setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public StaffBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public StaffBuilder setApplyDay(String applyDay) {
        this.applyDay = applyDay;
        return this;
    }

    public Staff build() {
        return new Staff(fullName, passWord, email, phoneNumber, age, applyDay);
    }
}
