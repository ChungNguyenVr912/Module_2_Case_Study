package entity.report_impl;

import entity.abstraction.Report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonthReport extends Report {
    private LocalDate monthReport;

    private MonthReport(Builder builder) {
        this.reportTime = builder.reportTime;
        this.totalRevenue = builder.totalRevenue;
        this.totalTicketSold = builder.totalTicketSold;
        this.monthReport = builder.monthReport;
        this.reportID = "MRP" + builder.monthReport.getMonthValue() + ID;
        ID++;
    }

    public static class Builder {
        private LocalDateTime reportTime;
        private double totalRevenue;
        private int totalTicketSold;
        private LocalDate monthReport;

        public Builder setReportTime(LocalDateTime reportTime) {
            this.reportTime = reportTime;
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

        public Builder setMonthReport(LocalDate monthReport) {
            this.monthReport = monthReport;
            return this;
        }

        public MonthReport build() {
            return new MonthReport(this);
        }
    }

    public LocalDate getMonthReport() {
        return monthReport;
    }

    public void setMonthReport(LocalDate monthReport) {
        this.monthReport = monthReport;
    }

    @Override
    public String toString() {
        return "Monthly Report:" + "\n" +
                "Month of report: " + monthReport.format(DateTimeFormatter.ofPattern("MM/yyyy")) + "\n" +
                "Report ID: " + reportID + "\n" +
                "Report time: " + reportTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n" +
                "Total Revenue: " + totalRevenue + "\n" +
                "TotalTicketSold: " + totalTicketSold;
    }
}
