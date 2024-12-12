import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AssistantReport assistantReport = new AssistantReport();
        int passwordExit = 5619;

        while (true) {

            printMenu();

            int userInput = scanner.nextInt();

            if (userInput == 1) {
                assistantReport.readMonthlyReports();
                System.out.println("Все месячные отчеты считаны");
            } else if (userInput == 2) {
                assistantReport.readYearReports();
                System.out.println("Годовой отчет считан");
            } else if (userInput == 3) {
                assistantReport.dataVerification();
            } else if (userInput == 4) {
                assistantReport.printInfoMonthReport();
            } else if (userInput == 5) {
                assistantReport.printInfoYearReport();
            } else if (userInput == passwordExit) {
                System.out.println("Выход!");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("5619 - Выйти из программы.");
    }

}