package Console;

import DTO.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

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

    private void expenseMenu() {

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

        int selected = 0;
        boolean h = true;
        while (h){
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
                    break;
                case 5:
                    getBudget(yearMonth);
                    h = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            UI.displayBudget(budget);
        }


    }

    private void deleteBudgetPost() {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

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
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        System.out.println("Amount");
        int amount = scanner.nextInt();

        budget.getPosts().get(i).setAmount(amount);
    }

    private void addBudgetPost() {
        // TODO move to UI
        Scanner scanner = new Scanner(System.in);
        System.out.println("Category");
        String category = scanner.next();
        System.out.println("Amount");
        int amount = scanner.nextInt();

        BudgetPost budgetPost = new BudgetPost(category, amount);

        budget.addPost(budgetPost);
    }

    // TODO
    private void ExpenseMenu(){

        int selected = 0;
        boolean h = true;
        while (h) {
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
                    break;
                case 5:
                    getExpenses(yearMonth);
                    h = false;
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

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        expenses.remove(i);
    }

    private void updateExpense() {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        System.out.println("Amount");
        int amount = scanner.nextInt();
        expenses.get(i).setAmount(amount);

        System.out.println("Category");
        String category = scanner.next();
        expenses.get(i).setCategory(category);

        System.out.println("Date");
        String date = scanner.next();
        expenses.get(i).setCategory(date);

        System.out.println("Note (Optional)");
        String note = scanner.next();
        expenses.get(i).setCategory(note);

    }

    private void addExpense() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Amount");
        double amount = scanner.nextDouble();
        System.out.println("Category");
        String category = scanner.next();
        System.out.println("Date");
        String date = scanner.next();
        System.out.println("Note (Optional)");
        String note = scanner.next();

        Expense expense = new Expense(amount, category, date, note);

        expenses.add(expense);
    }

}
