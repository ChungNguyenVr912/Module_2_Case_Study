package utils;

import entity.Flight;
import entity.abstraction.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public static List<User> getRegisteredUser(String sourceUrl){
        List<User> userList;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(sourceUrl))) {
            userList = (List<User>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found!");
            userList = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Read fail!");
            userList = new ArrayList<>();
        }
        return userList;
    }
    public static List<Flight> getFlightHistory(String sourceUrl){
        List<Flight> flightList;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(sourceUrl))){
            flightList = (List<Flight>) objectInputStream.readObject();
        }catch (ClassNotFoundException e){
            System.err.println("Class not found!");
            flightList = new ArrayList<>();
        }catch (IOException e){
            System.err.println("Fail to read flight list!");
            flightList = new ArrayList<>();
        }
        return flightList;
    }
    public static HashMap<String,String> getAirPortsList(String sourceUrl){
        HashMap<String,String> airPortList = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceUrl))){
            String line;
            while ((line = reader.readLine())!= null){
                String[] lineArr = line.split(",");
                airPortList.put(lineArr[1], lineArr[0]);
            }
        } catch (IOException e) {
            System.err.println("Fail to read airport list source file! ");
        }
        return airPortList;
    }
}
