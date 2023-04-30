package entity.abstraction;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String fullName;
    private String email;
    private String passWord;
    private long phoneNumber;

    public User(String fullName, String email, String passWord, long phoneNumber) {
        this.fullName = fullName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "FullName='" + fullName + '\'' +
                ", email='" + email + "[" + passWord + "]" +
                ", phoneNumber=" + phoneNumber;
    }
}
