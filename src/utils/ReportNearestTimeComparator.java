package utils;

import entity.abstraction.Report;

import java.util.Comparator;

public class ReportNearestTimeComparator implements Comparator<Report> {
    private static final ReportNearestTimeComparator instance = new ReportNearestTimeComparator();
    private ReportNearestTimeComparator(){}

    public static ReportNearestTimeComparator getInstance() {
        return instance;
    }

    @Override
    public int compare(Report report1, Report report2) {
        return -report1.getReportTime().compareTo(report2.getReportTime());
    }
}
