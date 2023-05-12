package utils.display;

import entity.abstraction.Report;
import services.TicketAndReportService;
import services.impl.AdminService;
import services.abstraction.UserService;
import utils.ValidateInput;

import java.util.Scanner;

public class AdminView {
    public static void displayAdminView() {
        System.out.print("""
                1. Register new Staff
                2. Register new Airlines Company
                3. View Airlines Company list
                4. View Staff list
                5. View user list
                6. View report
                7. Logout
                """);
        int option = ValidateInput.validateInteger();
        switch (option) {
            case 1 -> AdminService.getInstance().addStaff();
            case 2 -> AdminService.getInstance().addAirlinesCompany();
            case 3 -> AdminService.getInstance().viewAirlinesList();
            case 4 -> AdminService.getInstance().viewStaffList();
            case 5 -> AdminService.getInstance().checkUser();
            case 6 -> viewReport();
            case 7 -> {
                UserService.setCurrentUser(null);
                UserService.setStatus("");
                return;
            }
        }
        displayAdminView();
    }

    private static void viewReport() {
        while (true) {
            System.out.println("""
                    Revenue Report:
                                    
                    1. Day report
                    2. Month report
                    3. Year report
                    4. All report
                    5. Back
                    """);
            int option = ValidateInput.validateInteger();
            switch (option) {
                case 1 -> showDayReport();
                case 2 -> showMonthReport();
                case 3 -> showYearReport();
                case 4 -> AdminService.getInstance().viewAllReport();
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void showYearReport() {
        System.out.println("Enter year: ");
        int year = ValidateInput.validateInteger();
        Report report = TicketAndReportService.getYearReport(year);
        if (report != null){
            System.out.println(report);
        }else {
            System.out.println("No report");
        }
    }

    private static void showMonthReport() {
        System.out.println("Enter month: (MM/yyyy)");
        String month = scanner.nextLine();
        Report report = TicketAndReportService.getMonthReport(month);
        if (report != null){
            System.out.println(report);
        }else {
            System.out.println("No report");
        }
    }

    private static final Scanner scanner = new Scanner(System.in);
    private static void showDayReport() {
        System.out.println("Enter date: (dd/MM/yyyy)");
        String date = scanner.nextLine();
        Report report = TicketAndReportService.getDayReport(date);
        if (report != null){
            System.out.println(report);
        }else {
            System.out.println("No report");
        }
    }
}
