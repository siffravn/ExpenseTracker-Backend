package Console;

import java.time.YearMonth;
import java.util.Scanner;

public class UI {

    public static int menu(){
        StringBuilder menu = new StringBuilder();
        menu.append("Menu\n");
        menu.append("1 ").append("View budget\n");
        menu.append("2 ").append("View expenses\n");
        menu.append("3 ").append("Update budget\n");
        menu.append("4 ").append("Update expense\n");
        menu.append("5 ").append("Exit Expense Tracker\n");

        System.out.println(menu.toString());

        Scanner scanner = new Scanner(System.in);
        int selected = scanner.nextInt();

        return selected;
    }

    public static LogIn logIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your login information:");
        System.out.println("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();

        return new LogIn(username, password);
    }

    public static YearMonth yearMonth(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter desired year and moth:");
        System.out.println("Year: ");
        int year = scanner.nextInt();
        System.out.println("Month: ");
        int month = scanner.nextInt();

        return YearMonth.of(year, month);
    }
}
