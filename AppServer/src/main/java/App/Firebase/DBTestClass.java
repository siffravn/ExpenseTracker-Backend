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
        expenses.add(new Expense(1000, "games", new Date().toString(), "overwataaach"));

        dbController.updateExpenses(expenses, "user100");
    }
}
