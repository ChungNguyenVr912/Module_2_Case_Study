package utils;

import entity.abstraction.User;
import java.io.*;
import java.util.List;

public class DataReader {
    public static List<User> getRegisteredUser(String sourceUrl){
        List<User> userList = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(sourceUrl))) {
            userList = (List<User>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Read fail!");
        }
        return userList;
    }
}
