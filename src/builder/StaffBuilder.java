package builder;

import builder.abstraction.UserBuilder;
import entity.user_impl.Staff;

public class StaffBuilder extends UserBuilder {
    private int age;
    private String applyDay;

    public StaffBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public StaffBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StaffBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

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
