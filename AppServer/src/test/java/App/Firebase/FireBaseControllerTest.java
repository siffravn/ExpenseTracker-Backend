package App.Firebase;

import App.FireBaseController;
import DTO.Expense;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FireBaseControllerTest {

    private String testUserID = "Test1";
    private ArrayList<Expense> expenses = new ArrayList<>();

    @Test
    void AupdateExpenses() throws ExecutionException, InterruptedException {

        FireBaseController dbController = new FireBaseController();
        dbController.initializeForTest();


        dbController.updateExpenses(expenses, testUserID);
    }


    @Test
    void BgetExpenses() throws ExecutionException, InterruptedException {

        FireBaseController dbController = new FireBaseController();


        ArrayList<Expense> recievedExpenses = dbController.getExpenses(testUserID);


        expenses.add(new Expense(100, "Drinking", new Date(2020, Calendar.MARCH,15).toString(),"Cold one with the boys"));
        expenses.add(new Expense(1000, "Games", new Date(2019, Calendar.AUGUST,23).toString(), "overwataaach"));

        assertEquals(expenses.get(0).getCategory(), recievedExpenses.get(0).getCategory());
        assertEquals(expenses.get(1).getAmount(), recievedExpenses.get(1).getAmount());

    }

    @Test
    void CdeleteExpense() throws ExecutionException, InterruptedException {

        FireBaseController dbController = new FireBaseController();


        dbController.deleteExpense(testUserID, new Date(2020, Calendar.MARCH,15).toString());

        ArrayList<Expense> recievedExpenses = dbController.getExpenses(testUserID);

        expenses.add(new Expense(1000, "Games", new Date(2019, Calendar.AUGUST,23).toString(), "overwataaach"));

        assertEquals(1, recievedExpenses.size());
        assertEquals(expenses.get(0).getCategory(), recievedExpenses.get(0).getCategory());

    }

    @Test
    void saveBudget() {
    }
}