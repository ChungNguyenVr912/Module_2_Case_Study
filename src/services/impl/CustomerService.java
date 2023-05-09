package services.impl;

import entity.Ticket;
import entity.abstraction.User;
import entity.user_impl.Customer;
import factory.UserFactory;
import services.TicketAndReportService;
import services.abstraction.UserService;
import utils.DataWriter;
import utils.ValidateInput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService extends UserService {
    private static final Scanner scanner = new Scanner(System.in);
    public static void createUser(String fullName, String email, String passWord, long phoneNumber) {
        if (checkValidEmail(email)) {
            User newCustomer = UserFactory.getInstance()
                    .getUser("customer", fullName, email, passWord, phoneNumber);
            customerList.add(newCustomer);
            UserService.updateUserList();
        } else {
            status = "Email has been used!";
        }
    }
    public static void manageTicket(){
        List<String> bookedTicketCodeList = ((Customer)currentUser).getBookedTicket();
        List<Ticket> bookedTicketList = new ArrayList<>();
        for (String ticketCode : bookedTicketCodeList) {
            Ticket ticket = TicketAndReportService.getTicketFromCode(ticketCode);
            if (ticket != null){
                bookedTicketList.add(ticket);
            }
        }
        if (bookedTicketList.size() == 0){
            System.out.println("You have no Ticket.");
        }else {
            bookedTicketList.forEach(System.out::println);
        }
    }
    public static void updateInfo(int option) {
        String name = "";
        String password = "";
        String oldPassword;
        long phoneNumber = 0;
        long citizenID = 0;
        String gender = "";
        LocalDate dob = LocalDate.now();
        switch (option) {
            case 1 -> {
                System.out.println("Enter new password:");
                password = ValidateInput.validatePassword();

            }
            case 2 -> {
                System.out.println("Enter new name:");
                name = scanner.nextLine();
            }
            case 3 -> {
                System.out.println("Enter new phone number:");
                phoneNumber = ValidateInput.validateLong();
            }
            case 4 -> {
                System.out.println("Enter new citizen ID:");
                citizenID = ValidateInput.validateLong();
            }
            case 5 -> {
                System.out.println("Enter new DoB (dd/MM/yyyy)");
                dob = ValidateInput.validateDate();
            }
            case 6 -> {
                System.out.println("Enter new gender:");
                gender = scanner.nextLine();
                System.out.println("Good luck to you!");
            }
        }
        System.out.println("Enter your current password:");
        oldPassword = scanner.nextLine();
        if (oldPassword.equals(currentUser.getPassWord())){
            switch (option) {
                case 1 -> currentUser.setPassWord(password);
                case 2 -> currentUser.setFullName(name);
                case 3 -> currentUser.setPhoneNumber(phoneNumber);
                case 4 -> ((Customer)currentUser).setCitizenID(citizenID);
                case 5 -> ((Customer)currentUser).setDayOfBirth(dob);
                case 6 -> ((Customer)currentUser).setGender(gender);
            }
        }else {
            System.out.println("Wrong password!");
            return;
        }
        updateUserList();
    }
}