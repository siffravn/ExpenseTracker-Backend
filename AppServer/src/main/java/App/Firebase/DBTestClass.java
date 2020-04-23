package App.Firebase;

import App.Firebase.FireBaseController;
import DTO.Expense;
import DTO.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class DBTestClass {

    public static void main(String[] args) throws ExecutionException, InterruptedException, RemoteException, NotBoundException, MalformedURLException {



        FireBaseController dbController = new FireBaseController();
        dbController.initialize();

        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(100, "fish", new Date().toString(),""));
        expenses.add(new Expense(1000, "gaes", new Date(12,4,2020).toString(), "overwataaach"));

        //dbController.updateExpenses(expenses, "user100");



        dbController.deleteExpense("user100", expenses.get(1).getDate());

        ArrayList<Expense> expensesFromFB = dbController.getExpenses("user100");

        for (Expense expense : expensesFromFB){
            System.out.println(expense.getCategory());
        }




    }
}
