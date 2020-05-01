package Console;

import DTO.*;
import java.util.Scanner;

public class ExpenseTracker {

    User user;
    Budget budget;
    Expense expense;
    BudgetRESTConsoleClient backendService = new BudgetRESTConsoleClient();

    public void run(){
        int count = 0;
        do {
            login();
            if (user != null) break;
            count++;
        }
        while (count < 4);
        if (user == null) return;

        System.out.println("Welcome to ExpenseTracker " + user.firstName);
    }

    private void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your login information:");
        System.out.println("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();

        LogIn login = new LogIn(username, password);

        try{
            user = backendService.login(login);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

}
