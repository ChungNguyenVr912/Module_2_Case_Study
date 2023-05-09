package entity.abstraction;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Report implements Serializable {
    protected static long ID = 1;
    protected String reportID;
    protected LocalDateTime reportTime;
    protected double totalRevenue;
    protected int totalTicketSold;

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalTicketSold() {
        return totalTicketSold;
    }

    public void setTotalTicketSold(int totalTicketSold) {
        this.totalTicketSold = totalTicketSold;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportID='" + reportID + '\'' +
                ", reportTime=" + reportTime +
                ", totalRevenue=" + totalRevenue +
                ", totalTicketSold=" + totalTicketSold +
                '}';
    }
}
