class YearlyReport {
    int year;
    String month;
    double amount;
    boolean isExpense;

    YearlyReport(String numberYear, String month, String amount, String isExpense) {
        year = Integer.parseInt(numberYear);
        this.amount = Double.parseDouble(amount);
        this.isExpense = Boolean.parseBoolean(isExpense);
        switch (Integer.parseInt(month)) {
            case 1:
                this.month = "January";
                break;
            case 2:
                this.month = "February";
                break;
            case 3:
                this.month = "March";
                break;
        }
    }

    int getYear() {
        return year;
    }

    String getMonth() {
        return month;
    }

    double getAmount() {
        return amount;
    }

    boolean isExpense() {
        return isExpense;
    }
}