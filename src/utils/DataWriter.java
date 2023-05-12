package utils;

import entity.Flight;
import entity.abstraction.Report;
import entity.abstraction.User;
import entity.report_impl.DayReport;
import entity.report_impl.MonthReport;
import entity.report_impl.YearReport;
import services.TicketAndReportService;

import java.io.*;
import java.io.IOException;
import java.util.List;

public class DataWriter {

    public static boolean updateUserList(List<User> users, String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(users);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateFlights(List<Flight> flightList, String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(flightList);
            System.out.println("Update flight list success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTicketList(String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(TicketAndReportService.getTicketList());
            System.out.println("Update success! (Tickets)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateReportList(String targetUrl) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetUrl);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {
            outputStream.writeObject(TicketAndReportService.getReportList());
            System.out.println("Update success! (Reports)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateReportListToCSV(List<Report> reportList, String baseTargetUrl) {
        String targetUrl = baseTargetUrl;
        if (reportList.size() == 0) {
            System.out.println("List empty!");
            return;
        }
        if (reportList.get(0) instanceof DayReport) {
            targetUrl = targetUrl + "_day.csv";
        } else if (reportList.get(0) instanceof MonthReport) {
            targetUrl = targetUrl + "_month.csv";
        } else if (reportList.get(0) instanceof YearReport) {
            targetUrl = targetUrl + "_year.csv";
        }
        try (FileWriter fileWriter = new FileWriter(targetUrl);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Report report : reportList) {
                if (report instanceof DayReport dayReport) {
                    String line = dayReport.getReportID()
                            + "," + dayReport.getDayReport()
                            + "," + dayReport.getReportTime()
                            + "," + dayReport.getTotalRevenue()
                            + "," + dayReport.getTotalTicketSold();
                    bufferedWriter.write(line + "\n");
                }
                if (report instanceof MonthReport monthReport) {
                    String line = monthReport.getReportID()
                            + "," + monthReport.getMonthReport()
                            + "," + monthReport.getReportTime()
                            + "," + monthReport.getTotalRevenue()
                            + "," + monthReport.getTotalTicketSold();
                    bufferedWriter.write(line + "\n");
                }
                if (report instanceof YearReport yearReport) {
                    String line = yearReport.getReportID()
                            + "," + yearReport.getYearReport()
                            + "," + yearReport.getReportTime()
                            + "," + yearReport.getTotalRevenue()
                            + "," + yearReport.getTotalTicketSold();
                    bufferedWriter.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
