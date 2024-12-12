import java.util.ArrayList;

class AllReportsMonthly {
    String nameMonth;
    ArrayList<MonthlyReport> monthlyReport = new ArrayList<>();

    AllReportsMonthly(int month) {
        switch (month) {
            case 1:
                nameMonth = "January";
                break;
            case 2:
                nameMonth = "February";
                break;
            case 3:
                nameMonth = "March";
                break;
        }
    }

    String getNameMonth() {
        return nameMonth;
    }

    ArrayList<MonthlyReport> getMonthlyReport() {
        return monthlyReport;
    }

    void add(String[] arrayStrings) {
        monthlyReport.add(new MonthlyReport(arrayStrings[0], arrayStrings[1], arrayStrings[2], arrayStrings[3]));
    }
}
