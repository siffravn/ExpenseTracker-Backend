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

        System.out.println("Welcome to ExpenseTracker " + user.firstName + "\n");

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
                budgetMenu();
                break;

            case 4:
                expenseMenu();
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

    private void budgetMenu(){
        boolean editing = true;
        while (editing){
            int selected = UI.editMenu();
            switch (selected){
                case 1:
                    addBudgetPost();
                    break;
                case 2:
                    updateBudgetPost();
                    break;
                case 3:
                    deleteBudgetPost();
                    break;
                case 4:
                    updateBudget();
                    editing = false;
                    break;
                case 5:
                    getBudget(yearMonth);
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            UI.displayBudget(budget);
        }
    }

    private void deleteBudgetPost() {
        int i = UI.getIndex();

        budget.getPosts().remove(i);
    }

    private void updateBudget() {
        try {
            backendService.updateBudget(user.username, budget);
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }

    private void updateBudgetPost() {

        int i = UI.getIndex();
        int amount = UI.getAmount();

        budget.getPosts().get(i).setAmount(amount);
    }

    private void addBudgetPost() {
        BudgetPost budgetPost = UI.getBudgetPost();

        budget.addPost(budgetPost);
    }

    private void expenseMenu(){
        boolean editing = true;
        while (editing) {
            int selected = UI.editExpenseMenu();
            switch (selected) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    updateExpense();
                    break;
                case 3:
                    deleteExpense();
                    break;
                case 4:
                    updateExpenseList();
                    editing = false;
                    break;
                case 5:
                    getExpenses(yearMonth);
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }

            UI.displayExpenses(expenses);

        }
    }

    private void updateExpenseList() {
        try{
            backendService.updateExpenses(user.username, yearMonth, expenses);
        }
        catch (Exception e) {
           System.out.println( "Error");
        }
    }

    private void deleteExpense() {
        int i = UI.getIndex();

        expenses.remove(i);
    }

    private void updateExpense() {
        int i = UI.getIndex();
        Expense expense = UI.getExpense();

        expenses.set(i, expense);

    }

    private void addExpense() {

        Expense expense = UI.getExpense();

        expenses.add(expense);
    }

}
