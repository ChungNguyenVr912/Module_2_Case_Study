package entity.report_impl;

import entity.abstraction.Report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DayReport extends Report {
    private LocalDate dayReport;

    private DayReport(Builder builder) {
        this.reportTime = builder.reportTime;
        this.totalRevenue = builder.totalRevenue;
        this.totalTicketSold = builder.totalTicketSold;
        this.dayReport = builder.dayReport;
        this.reportID = "DRP" + builder.dayReport.getDayOfMonth() + ID;
        ID++;
    }

    public static class Builder {
        private LocalDateTime reportTime;
        private double totalRevenue;
        private int totalTicketSold;
        private LocalDate dayReport;

        public Builder setReportTime(LocalDateTime reportTime) {
            this.reportTime = reportTime;
            return this;
        }

        public Builder setDayReport(LocalDate dayReport) {
            this.dayReport = dayReport;
            return this;
        }

        public Builder setTotalRevenue(double totalRevenue) {
            this.totalRevenue = totalRevenue;
            return this;
        }

        public Builder setTotalTicketSold(int totalTicketSold) {
            this.totalTicketSold = totalTicketSold;
            return this;
        }

        public DayReport build() {
            return new DayReport(this);
        }
    }

    public LocalDate getDayReport() {
        return dayReport;
    }

    public void setDayReport(LocalDate dayReport) {
        this.dayReport = dayReport;
    }

    @Override
    public String toString() {
        return "Daily Report:\n" +
                "Day of report: " + dayReport + "\n"+
                "Report ID: " + reportID + "\n"+
                "Report time: " + reportTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +"\n"+
                "Total Revenue: " + totalRevenue +"\n"+
                "TotalTicketSold: " + totalTicketSold;
    }
}
