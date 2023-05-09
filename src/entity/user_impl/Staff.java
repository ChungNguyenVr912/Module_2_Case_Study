package entity.user_impl;

import entity.abstraction.User;

public class Staff extends User {
    private int age;
    private String applyDay;
    public Staff(String fullName, String passWord, String email, long phoneNumber, int age, String applyDay) {
        super(fullName, passWord, email, phoneNumber);
        this.age = age;
        this.applyDay = applyDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(String applyDay) {
        this.applyDay = applyDay;
    }

    @Override
    public String toString() {
        return super.toString() + ", age=" + age + ", job apply from " + applyDay;
    }
}
