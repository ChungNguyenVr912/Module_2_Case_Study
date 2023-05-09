package entity.user_impl;

import entity.abstraction.User;

public class Admin extends User {
    private static final Admin instance = new Admin("ADMIN", "ADMIN", "admin", 1111);
    private Admin(String fullName, String email, String passWord, long phoneNumber) {
        super(fullName, email, passWord, phoneNumber);
    }

    public static Admin getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Admin\n" + super.toString();
    }
}
