package Console;

import DTO.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ExpenseTracker {

    User user;
    Budget budget;
    ArrayList<Expense> expenses;
    BudgetRESTConsoleClient backendService = new BudgetRESTConsoleClient();
    boolean run = true;

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

        getCurrentBudget();

        while (run){
            menu();
        }
        System.out.println("Goodbye " + user.firstName);
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
                run = false;
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

    private void getCurrentBudget(){

        YearMonth yearMonth = YearMonth.now();

        try{
            budget = backendService.getBudget(user.username, yearMonth);

        }catch (Exception e){
            System.out.println("Error");
            return;
        }

        UI.displayBudget(budget);
    }

    private void getBudget(){

        YearMonth yearMonth = UI.yearMonth();

        try{
            budget = backendService.getBudget(user.username, yearMonth);

        }catch (Exception e){
            System.out.println("Error");
            return;
        }

        UI.displayBudget(budget);
    }

    private void getExpenses(){
        YearMonth yearMonth = UI.yearMonth();

        try{
            expenses = backendService.getExpenses(user.username, yearMonth);

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
