package App.Firebase;

import DTO.Expense;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class FireBaseControllerTest {

    private String testUserID = "Test1";
    private ArrayList<Expense> expenses = new ArrayList<>();

    @Test
    void updateExpenses() throws ExecutionException, InterruptedException {
        FireBaseController dbController = new FireBaseController();
        dbController.initializeForTest();

        dbController.updateExpenses(expenses, testUserID);
    }

    @Test
    void saveUser() {
    }

    @Test
    void getExpenses() throws ExecutionException, InterruptedException {

        FireBaseController dbController = new FireBaseController();

        ArrayList<Expense> recievedExpenses = dbController.getExpenses(testUserID);


        expenses.add(new Expense(100, "Drinking", new Date(2020, Calendar.MARCH,15).toString(),"Cold one with the boys"));
        expenses.add(new Expense(1000, "Games", new Date(2019, Calendar.AUGUST,23).toString(), "overwataaach"));

        assertEquals(expenses.get(0).getCategory(), recievedExpenses.get(0).getCategory());
        assertEquals(expenses.get(1).getAmount(), recievedExpenses.get(1).getAmount());

    }

    @Test
    void deleteExpense() {
    }

    @Test
    void saveBudget() {
    }
}