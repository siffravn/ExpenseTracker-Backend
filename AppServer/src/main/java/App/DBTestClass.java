package App;

import App.FireBaseController;
import DTO.Budget;
import DTO.BudgetPost;
import DTO.Expense;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class DBTestClass {

    static String testUserID = "user100";

    public static void main(String[] args) throws ExecutionException, InterruptedException, RemoteException, NotBoundException, MalformedURLException {



        FireBaseController dbController = new FireBaseController();
        dbController.initialize();

        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(100, "fish", new Date().toString(),""));
        expenses.add(new Expense(1000, "gaes", new Date(12,4,2020).toString(), "overwataaach"));

        Budget budget = new Budget(2020, 4);

        ArrayList<BudgetPost> posts = new ArrayList<>();
        posts.add(new BudgetPost("Husleje", 4500));
        posts.add(new BudgetPost("Mad", 2300));

        budget.setPosts(posts);


        dbController.updateExpenses( "user100", 2023, 5, expenses);
        //dbController.deleteExpense("user100", expenses.get(1).getDate());





        ArrayList<Expense> expensesFromFB = dbController.getExpenses("user100");

        for (Expense expense : expensesFromFB){
            System.out.println(expense.getCategory());
        }




    }
}
