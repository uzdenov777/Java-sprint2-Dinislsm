class MonthlyReport {
    String itemName;
    boolean isExpense;
    int quantity;
    double unitPrice;

    MonthlyReport(String itemName, String isExpense, String quantity, String unitPrice) {
        this.itemName = itemName;
        this.isExpense = Boolean.parseBoolean(isExpense);
        this.quantity = Integer.parseInt(quantity);
        this.unitPrice = Double.parseDouble(unitPrice);
    }

    String getItemName() {
        return itemName;
    }

    boolean isExpense() {
        return isExpense;
    }

    int getQuantity() {
        return quantity;
    }

    double getUnitPrice() {
        return unitPrice;
    }
}