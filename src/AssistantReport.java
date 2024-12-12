import java.util.ArrayList;

class AssistantReport {

    FileReader fileReader = new FileReader();

    ArrayList<YearlyReport> reportYearList = new ArrayList<>();
    ArrayList<AllReportsMonthly> reportMonthList = new ArrayList<>();

    void readMonthlyReports() {
        ArrayList<String> demoMonthList = null;
        for (int i = 1; i < 4; i++) {
            demoMonthList = fileReader.readFileContents("m.20210" + i + ".csv");

            AllReportsMonthly allReportsMonthly = new AllReportsMonthly(i);
            for (int j = 1; j < demoMonthList.size(); j++) {
                String[] arrayString = demoMonthList.get(j).split(",");

                allReportsMonthly.add(arrayString);
            }
            reportMonthList.add(allReportsMonthly);
        }
    }

    void readYearReports() {
        String fileName = "y.2021.csv";

        ArrayList<String> demoYearList = null;
        demoYearList = fileReader.readFileContents(fileName);


        for (int i = 1; i < demoYearList.size(); i++) {
            String[] year = fileName.split("\\.");
            String oneElementYear = demoYearList.get(i);
            String[] arrayString = oneElementYear.split(",");

            reportYearList.add(new YearlyReport(year[1], arrayString[0], arrayString[1], arrayString[2]));

        }
    }

    void printInfoMonthReport() {

        if (reportMonthList.isEmpty()) {
            System.out.println("Месячные отчеты не загружены! Выполните команду №1!");
        }

        for (AllReportsMonthly reportsMonthly : reportMonthList) {
            String nameMonth = reportsMonthly.getNameMonth();
            String nameMaxProfitableProductMonth = null;
            double maxProfitableProductMonth = 0;
            String nameMaxExpenseMonth = null;
            double maxExpenseMonth = 0;

            for (MonthlyReport monthlyReport : reportsMonthly.getMonthlyReport()) {

                if (monthlyReport.isExpense && maxExpenseMonth <= (monthlyReport.getQuantity() * monthlyReport.getUnitPrice())) {
                    maxExpenseMonth = (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                    nameMaxExpenseMonth = monthlyReport.itemName;
                } else if (!(monthlyReport.isExpense) && maxProfitableProductMonth <= (monthlyReport.getQuantity() * monthlyReport.getUnitPrice())) {
                    maxProfitableProductMonth = (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                    nameMaxProfitableProductMonth = monthlyReport.itemName;
                }
            }
            System.out.println(nameMonth + ":");
            System.out.println("Cамый прибыльный товар- " + nameMaxProfitableProductMonth + " продано на сумму: " + maxProfitableProductMonth);
            System.out.println("Самая большая трата- " + nameMaxExpenseMonth + " закупленно на сумму: " + maxExpenseMonth);
        }
    }

    void printInfoYearReport() {
        if (reportYearList.isEmpty()) {
            System.out.println("Годовой отчет не загружен! Выполните команду №2!");
            return;
        }

        int year = reportYearList.get(0).getYear();
        Double expenseYear = 0.0;
        int countExpenseYear = 0;
        Double profitYear = 0.0;
        int countProfitYear = 0;

        ArrayList<Double> yearList = new ArrayList<>();
        double profitMonthSum = 0.0;
        int countProfitMonthSum = 0;

        for (AllReportsMonthly allReportsMonthly : reportMonthList) {//этот цикл распределяет каждую операцию
            // на доходы и расходы
            for (MonthlyReport monthlyReport : allReportsMonthly.getMonthlyReport()) {
                if (monthlyReport.isExpense) {
                    expenseYear += (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                    countExpenseYear++;
                } else {
                    profitYear += (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                    countProfitYear++;
                }
            }
        }

        for (AllReportsMonthly allReportsMonthly : reportMonthList) {
            for (MonthlyReport monthlyReport : allReportsMonthly.getMonthlyReport()) {
                if (monthlyReport.isExpense) {
                    profitMonthSum -= (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                    countProfitMonthSum++;
                } else {
                    profitMonthSum += (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                    countProfitMonthSum++;
                }
            }
            yearList.add(profitMonthSum);
            profitMonthSum = 0.0;
        }

        System.out.println("Рассматриваемый год: " + year);
        System.out.println("прибыль по каждому месяцу:");
        for (int i = 0; i < yearList.size(); i++) {
            System.out.println("0"+(i+1)+"."+year+": "+yearList.get(i));
        }
        System.out.println("Средний расход за все имеющиеся операции в году: " + expenseYear / countExpenseYear);
        System.out.println("Средний доход за все имеющиеся операции в году: " + profitYear / countProfitYear);
    }


    void dataVerification() {
        if (reportMonthList.isEmpty() || reportYearList.isEmpty()) {
            System.out.println("Oтчеты не загружены! Выполните команды №1-2!");
            return;
        }
        System.out.println("Началась сверка отчетов.");
        boolean perfictCount = false;
        for (AllReportsMonthly allReportsMonthly : reportMonthList) {
            double expenseMonth = 0.0;
            double profitMonth = 0.0;

            for (MonthlyReport monthlyReport : allReportsMonthly.getMonthlyReport()) {
                if (monthlyReport.isExpense) {
                    expenseMonth += (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                } else {
                    profitMonth += (monthlyReport.getQuantity() * monthlyReport.getUnitPrice());
                }
            }
            double expenseYear = 0;
            double profitYear = 0;
            for (YearlyReport yearlyReport : reportYearList) {
                if (yearlyReport.isExpense && yearlyReport.getMonth().equals(allReportsMonthly.getNameMonth())) {
                    expenseYear += yearlyReport.getAmount();
                } else if (!(yearlyReport.isExpense) && yearlyReport.getMonth().equals(allReportsMonthly.getNameMonth())) {
                    profitYear += yearlyReport.getAmount();
                }

            }
            if (!(expenseMonth == expenseYear && profitMonth == profitYear)) {
                System.out.println("Обнаружено несоответствие в: " + allReportsMonthly.getNameMonth());
                perfictCount = false;
            } else {
                perfictCount = true;
            }
        }
        if (perfictCount) {
            System.out.println("Несоответствий не обнаружено!");
        }
        System.out.println("Закончилась сверка отчетов.");
    }
}

