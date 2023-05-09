package services;

import entity.user_impl.Customer;
import entity.Ticket;
import entity.abstraction.Report;
import factory.ReportFactory;
import services.abstraction.UserService;
import utils.DataReader;
import utils.DataWriter;
import utils.ValidateInput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketAndReportService {
    private static final String TICKET_LIST_URL = "src/data/reports_and_tickets/tickets.dat";
    private static final String REPORT_LIST_URL = "src/data/reports_and_tickets/reports.dat";
    private static List<Ticket> ticketList;
    private static List<Report> reportList;

    static {
        ticketList = DataReader.getTicketData(TICKET_LIST_URL);
        reportList = DataReader.getReportData(REPORT_LIST_URL);
    }

    public static List<Ticket> getTicketList() {
        return ticketList;
    }

    public static List<Report> getReportList() {
        return reportList;
    }

    public static void printTicketAndSendToCustomer(Ticket ticket) {
        printTicket(ticket);
        ((Customer) UserService.getCurrentUser()).setBookedTicket(ticket.getTicketCode());
        UserService.updateUserList();
    }

    public static void printTicket(Ticket ticket) {
        DataWriter.updateTicketList(TICKET_LIST_URL);
        System.out.println(ticket);
    }

    public static void updateReport() {
        DataWriter.updateReportList(REPORT_LIST_URL);
    }

    public static List<Ticket> getListTicketOfDay(int day) {
        List<Ticket> listTicketOfDay = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if (ticket.getBookingDay().toLocalDate()
                    .isEqual(LocalDateTime.now().withDayOfMonth(day).toLocalDate())) {
                listTicketOfDay.add(ticket);
            }
        }
        return listTicketOfDay;
    }

    public static List<Ticket> getListTicketOfMonth(int month) {
        List<Ticket> listTicketOfMonth = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if (ticket.getBookingDay().getMonthValue() == month
                    && ticket.getBookingDay().getYear() == LocalDateTime.now().getYear()) {
                listTicketOfMonth.add(ticket);
            }
        }
        return listTicketOfMonth;
    }

    public static List<Ticket> getListTicketOfYear() {
        List<Ticket> listTicketOfYear = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if (ticket.getBookingDay().getYear() == LocalDateTime.now().getYear()) {
                listTicketOfYear.add(ticket);
            }
        }
        return listTicketOfYear;
    }

    public static void report() {
        while (true) {
            System.out.println("""
                    Revenue Report:
                    1. Day
                    2. Month
                    3. This year
                    4. Back
                    """);
            int option = ValidateInput.validateInteger();
            switch (option) {
                case 1 -> createReport("day");
                case 2 -> createReport("month");
                case 3 -> createReport("year");
                case 4 -> {
                    return;
                }
            }
        }
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static void createReport(String time) {
        Report report = ReportFactory.getReport(time);
        System.out.println(report);
        System.out.println("Confirm report: (Y/N)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")){
            reportList.add(report);
            updateReport();
        }
    }
    public static Ticket getTicketFromCode(String ticketCode){
        for (Ticket ticket : ticketList) {
            if (ticket.getTicketCode().equals(ticketCode)){
                return ticket;
            }
        }
        return null;
    }
}
