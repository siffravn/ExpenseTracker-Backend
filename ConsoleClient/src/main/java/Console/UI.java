package Console;

import DTO.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public static int menu(){
        StringBuilder menu = new StringBuilder();
        menu.append("Menu\n");
        menu.append("1 ").append("View current Budget and Expenses\n");
        menu.append("2 ").append("View Budget and Expenses of choice\n");
        menu.append("3 ").append("Update displayed budget\n");
        menu.append("4 ").append("Update displayed expense\n");
        menu.append("5 ").append("Exit Expense Tracker\n");

        System.out.println(menu.toString());

        Scanner scanner = new Scanner(System.in);
        int selected = scanner.nextInt();

        return selected;
    }

    public static int editBudgetMenu(){
        StringBuilder editBudgetMenu = new StringBuilder();
        editBudgetMenu.append("Budget Menu\n");
        editBudgetMenu.append("1").append("Add a new Budget Post\n");
        editBudgetMenu.append("2").append("Update a Budget Post\n");
        editBudgetMenu.append("3").append("Delete a Budget Post\n");
        editBudgetMenu.append("4").append("Save all changes\n");
        editBudgetMenu.append("5").append("Cancel all changes\n");

        System.out.println(editBudgetMenu.toString());

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

    public static void displayBudget(Budget budget) {
        StringBuilder sb = new StringBuilder();

        if (budget.getPosts().isEmpty()){
            sb
                    .append("No budget for ")
                    .append(budget.getMonth()).append("-").append(budget.getYear()).append("\n");
        }
        else {
            sb
                    .append("Budget for ")
                    .append(budget.getMonth()).append("-").append(budget.getYear()).append("\n");
            int i = 0;
            for(BudgetPost bp : budget.getPosts()){
                sb
                        .append(i).append(" ")
                        .append(bp.getCategory()).append(": ")
                        .append(bp.getAmount()).append("\n");
                i++;
            }
        }

        System.out.println(sb.toString());
    }

    public static void displayExpenses(ArrayList<Expense> expenses){
        StringBuilder sb = new StringBuilder();

        if(expenses.isEmpty()){
            sb.append("No expenses for selected month!");
        }
        else {
            int i = 0;
            for (Expense e : expenses){
                sb
                        .append(i).append(" ")
                        .append(e.getCategory()).append(": ")
                        .append(e.getDate()).append(" ")
                        .append(e.getAmount()).append(" ")
                        .append(e.getNote()).append("\n");
                i++;
            }
        }
        System.out.println(sb.toString());
    }
}
