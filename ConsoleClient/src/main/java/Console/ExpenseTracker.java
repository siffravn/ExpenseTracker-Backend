package Console;

import DTO.*;

import java.time.YearMonth;
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

        int selected = UI.menu();

        switch (selected) {
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
        LogIn login = UI.logIn();

        try{
            user = backendService.login(login);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    private void getBudget(){

        YearMonth yearMonth = UI.yearMonth();
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        try{
            budget = backendService.getBudget(year, month);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    private void getExpenses(){
        YearMonth yearMonth = UI.yearMonth();
        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

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
