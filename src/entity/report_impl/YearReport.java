package entity.report_impl;

import entity.abstraction.Report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class YearReport extends Report {
    private int yearReport;

    private YearReport(Builder builder) {
        this.reportTime = builder.reportTime;
        this.totalRevenue = builder.totalRevenue;
        this.totalTicketSold = builder.totalTicketSold;
        this.yearReport = builder.yearReport;
        this.reportID = "YRP" + yearReport + ID;
        ID++;
    }

    public static class Builder {
        private LocalDateTime reportTime;
        private double totalRevenue;
        private int totalTicketSold;
        private int yearReport;

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

        public Builder setYearReport(int yearReport) {
            this.yearReport = yearReport;
            return this;
        }

        public YearReport build() {
            return new YearReport(this);
        }
    }

    public int getYearReport() {
        return yearReport;
    }

    public void setYearReport(int yearReport) {
        this.yearReport = yearReport;
    }

    @Override
    public String toString() {
        return "Yearly Report:" +
                "Year report:" + yearReport + "\n" +
                "Report ID: " + reportID + "\n" +
                "Report time: " + reportTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n" +
                "Total Revenue: " + totalRevenue + "\n" +
                "TotalTicketSold: " + totalTicketSold;
    }
}
