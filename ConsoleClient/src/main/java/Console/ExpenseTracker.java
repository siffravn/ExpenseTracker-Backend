package Console;

import DTO.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    User user;
    Budget budget;
    ArrayList<Expense> expenses;
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

    private void menu(){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                getBudget();
                break;

            case 2:
                getExpenses();
                break;

            case 3:
                updateBudget();
                break;

            case 4:
                updateExpense();
                break;

            case 5:
                System.exit(0);
                break;

            default:
                System.out.println("Invalid input");
                break;
        }
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

    private void getBudget(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter desired year and moth:");
        System.out.println("Year: ");
        int year = scanner.nextInt();
        System.out.println("Month: ");
        int month = scanner.nextInt();

        try{
            budget = backendService.getBudget(year, month);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    private void getExpenses(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter desired year and moth:");
        System.out.println("Year: ");
        int year = scanner.nextInt();
        System.out.println("Month: ");
        int month = scanner.nextInt();

        try{
            expenses = backendService.getExpenses(year, month);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    // TODO
    private void updateBudget(){
        System.out.println("To be implemented");
    }

    // TODO
    private void updateExpense(){
        System.out.println("To be implemented");
    }

}
