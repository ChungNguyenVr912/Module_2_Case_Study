package entity;

import entity.abstraction.User;

public class Admin extends User {
    private static final Admin instance = new Admin("ADMIN", "aDMiN", "ADMIN", 1111);
    private Admin(String fullName, String passWord, String email, long phoneNumber) {
        super(fullName, passWord, email, phoneNumber);
    }

    public static Admin getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Admin\n" + super.toString();
    }
}
