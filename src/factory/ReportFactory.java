package factory;

import entity.Ticket;
import entity.abstraction.Report;
import entity.report_impl.DayReport;
import entity.report_impl.MonthReport;
import entity.report_impl.YearReport;
import services.TicketAndReportService;
import utils.ValidateInput;

import java.time.LocalDateTime;
import java.util.List;

public class ReportFactory {
    private static LocalDateTime reportTime = LocalDateTime.now();

    public static Report getReport(String type) {
        switch (type) {
            case "day" -> {
                System.out.println("Enter day of this month:");
                int day = ValidateInput.validateInteger();
                List<Ticket> listTicketOfDay = TicketAndReportService.getListTicketOfDay(day);
                double totalRevenue = 0;
                int ticketCount = 0;
                for (Ticket ticket : listTicketOfDay) {
                    totalRevenue += ticket.getPrice();
                    ticketCount++;
                }
                return new DayReport.Builder()
                        .setReportTime(reportTime)
                        .setDayReport(reportTime.withDayOfMonth(day).toLocalDate())
                        .setTotalRevenue(totalRevenue)
                        .setTotalTicketSold(ticketCount)
                        .build();
            }
            case "month" -> {
                System.out.println("Enter month of this year");
                int month = ValidateInput.validateInteger();
                List<Ticket> listTicketOfMonth = TicketAndReportService.getListTicketOfMonth(month);
                double totalRevenue = 0;
                int ticketCount = 0;
                for (Ticket ticket : listTicketOfMonth) {
                    totalRevenue += ticket.getPrice();
                    ticketCount++;
                }
                return new MonthReport.Builder()
                        .setReportTime(reportTime)
                        .setMonthReport(reportTime.withMonth(month).toLocalDate())
                        .setTotalRevenue(totalRevenue)
                        .setTotalTicketSold(ticketCount)
                        .build();
            }
            case "year" ->{
                List<Ticket> listTicketOfYear = TicketAndReportService.getListTicketOfYear();
                double totalRevenue = 0;
                int ticketCount = 0;
                for (Ticket ticket : listTicketOfYear) {
                    totalRevenue += ticket.getPrice();
                    ticketCount++;
                }
                return new YearReport.Builder()
                        .setReportTime(reportTime)
                        .setYearReport(reportTime.getYear())
                        .setTotalRevenue(totalRevenue)
                        .setTotalTicketSold(ticketCount)
                        .build();
            }
            default -> {
                return null;
            }
        }
    }
}
