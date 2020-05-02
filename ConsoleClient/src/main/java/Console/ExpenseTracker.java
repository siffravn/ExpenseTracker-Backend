package Console;

import DTO.*;

import java.time.YearMonth;
import java.util.ArrayList;

public class ExpenseTracker {

    User user;
    Budget budget;
    ArrayList<Expense> expenses;
    YearMonth yearMonth = YearMonth.now();
    BackendService backendService = new BackendService();
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

        getBudget(yearMonth);
        getExpenses(yearMonth);
        UI.displayBudget(budget);
        UI.displayExpenses(expenses);

        while (run){
            menu();
        }
        System.out.println("Goodbye " + user.firstName);
    }

    private void menu(){

        int selected = UI.menu();

        switch (selected) {
            case 1:
                yearMonth = YearMonth.now();
                getBudget(yearMonth);
                getExpenses(yearMonth);
                UI.displayBudget(budget);
                UI.displayExpenses(expenses);
                break;

            case 2:
                yearMonth = UI.yearMonth();
                getBudget(yearMonth);
                getExpenses(yearMonth);
                UI.displayBudget(budget);
                UI.displayExpenses(expenses);
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

    private void getBudget(YearMonth yearMonth){

        try{
            budget = backendService.getBudget(user.username, yearMonth);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    private void getExpenses(YearMonth yearMonth){

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
