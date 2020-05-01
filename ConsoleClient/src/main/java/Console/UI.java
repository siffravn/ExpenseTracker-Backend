package Console;

import java.time.YearMonth;
import java.util.Scanner;

public class UI {

    public static int menu(){
        StringBuilder menu = new StringBuilder();

        return 0;
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
