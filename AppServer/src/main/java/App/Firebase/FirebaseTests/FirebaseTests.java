package App.Firebase.FirebaseTests;

import App.Firebase.FireBaseController;
import DTO.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirebaseTests {
    //TODO Currently doenst work, a problem with filepath when called from a test class

    private String testUserID = "Test1";
    private ArrayList<Expense> expenses = new ArrayList<>();


    @Test
    void saveExpenses() throws ExecutionException, InterruptedException {

        FireBaseController dbController = new FireBaseController();
        dbController.initialize();

        expenses.add(new Expense(100, "Drinking", new Date().toString(),"Cold one with the boys"));
        expenses.add(new Expense(1000, "Games", new Date(2019, Calendar.AUGUST,23).toString(), "overwataaach"));

        dbController.updateExpenses(expenses, testUserID);
    }

    @Test
    void getExpenses() throws ExecutionException, InterruptedException {
        FireBaseController dbController = new FireBaseController();
        dbController.initialize();

        ArrayList<Expense> recievedExpenses = dbController.getExpenses(testUserID);

        assertEquals(expenses, recievedExpenses);


    }
}
