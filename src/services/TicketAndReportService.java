package services;

import entity.report_impl.DayReport;
import entity.report_impl.MonthReport;
import entity.report_impl.YearReport;
import entity.user_impl.Customer;
import entity.Ticket;
import entity.abstraction.Report;
import factory.ReportFactory;
import services.abstraction.UserService;
import utils.DataReader;
import utils.DataWriter;
import utils.ReportNearestTimeComparator;
import utils.ValidateInput;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketAndReportService {
    private static final String TICKET_LIST_URL = "src/data/reports_and_tickets/tickets.dat";
    private static final String REPORT_LIST_URL = "src/data/reports_and_tickets/reports.dat";
    private static final String REPORT_LIST_CSV_URL = "src/data/reports_and_tickets/reports";
    private static List<Ticket> ticketList;
    private static List<Report> reportList;

    static {
        ticketList = DataReader.getTicketData(TICKET_LIST_URL);
        reportList = DataReader.getReportData(REPORT_LIST_URL);
        reportList.sort(ReportNearestTimeComparator.getInstance());
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
        ticketList.add(ticket);
        DataWriter.updateTicketList(TICKET_LIST_URL);
        System.out.println(ticket);
    }

    public static void updateReport() {
        reportList.sort(ReportNearestTimeComparator.getInstance());
        DataWriter.updateReportList(REPORT_LIST_URL);
    }

    public static void updateReportToCsv() {
        DataWriter.updateReportListToCSV(getDayReportList(),REPORT_LIST_CSV_URL);
        DataWriter.updateReportListToCSV(getMonthReportList(),REPORT_LIST_CSV_URL);
        DataWriter.updateReportListToCSV(getYearReportList(),REPORT_LIST_CSV_URL);
    }

    public static List<Report> getDayReportList() {
        List<Report> result = new ArrayList<>();
        for (Report report : reportList) {
            if (report instanceof DayReport) {
                result.add(report);
            }
        }
        return result;
    }

    public static List<Report> getMonthReportList() {
        List<Report> result = new ArrayList<>();
        for (Report report : reportList) {
            if (report instanceof MonthReport) {
                result.add(report);
            }
        }
        return result;
    }

    public static List<Report> getYearReportList() {
        List<Report> result = new ArrayList<>();
        for (Report report : reportList) {
            if (report instanceof YearReport) {
                result.add(report);
            }
        }
        return result;
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
        if (confirm.equalsIgnoreCase("Y")) {
            reportList.add(report);
            updateReport();
            updateReportToCsv();
        }
    }

    public static Ticket getTicketFromCode(String ticketCode) {
        for (Ticket ticket : ticketList) {
            if (ticket.getTicketCode().equals(ticketCode)) {
                return ticket;
            }
        }
        return null;
    }

    public static Report getDayReport(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate targetDate;
        try {
            targetDate = LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            System.out.println("Invalid date!");
            return null;
        }
        for (Report report : reportList) {
            if (report instanceof DayReport) {
                if (((DayReport) report).getDayReport().isEqual(targetDate)) {
                    return report;
                }
            }
        }
        return null;
    }

    public static Report getMonthReport(String month) {
        String date = "15/" + month;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate targetDate;
        try {
            targetDate = LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            System.out.println("Invalid date!");
            return null;
        }
        for (Report report : reportList) {
            if (report instanceof MonthReport) {
                if (((MonthReport) report).getMonthReport().withDayOfMonth(15).isEqual(targetDate)) {
                    return report;
                }
            }
        }
        return null;
    }

    public static Report getYearReport(int year) {
        for (Report report : reportList) {
            if (report instanceof YearReport) {
                if (((YearReport) report).getYearReport() == year) {
                    return report;
                }
            }
        }
        return null;
    }
}
