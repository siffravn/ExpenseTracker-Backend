package App.Firebase;

import App.FireBaseController;
import DTO.Budget;
import DTO.BudgetPost;
import DTO.Expense;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test currently only works if you give absolute path to ServiceAccount.json file in initializeForTest() method
 */
class FireBaseControllerTest {

    /*private String testUserID = "TestUser";
    private FireBaseController dbController = new FireBaseController();



    @Test
    void expenseCRUD() throws ExecutionException, InterruptedException {

        dbController.initializeForTest();

        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(100, "Drinking", new Date(2020, Calendar.MARCH,15).toString(),"Cold one with the boys"));
        expenses.add(new Expense(1000, "Games", new Date(2019, Calendar.AUGUST,23).toString(), "overwataaach"));


        dbController.updateExpenses(testUserID, expenses);


        ArrayList<Expense> receivedExpenses = dbController.getExpenses(testUserID);

        assertEquals(expenses.get(0).getCategory(), receivedExpenses.get(0).getCategory());
        assertEquals(expenses.get(1).getAmount(), receivedExpenses.get(1).getAmount());


        //deletes first element from Database.
        dbController.deleteExpense(testUserID, new Date(2020, Calendar.MARCH,15).toString() );

        receivedExpenses = dbController.getExpenses(testUserID);

        assertEquals(1, receivedExpenses.size());
        assertEquals(expenses.get(1).getCategory(), receivedExpenses.get(0).getCategory());
    }
    @Test
    void budgetCRUD() throws ExecutionException, InterruptedException {

        Budget budget = new Budget(2020, 4);
        ArrayList<BudgetPost> posts = new ArrayList<>();
        posts.add(new BudgetPost("Husleje", 4500));
        posts.add(new BudgetPost("Mad", 2300));
        budget.setPosts(posts);

        dbController.updateBudget(testUserID, budget);


        Budget receivedBudget = dbController.getBudget(testUserID, budget.getYear(), budget.getMonth());

        assertEquals(receivedBudget.getPosts().get(0).getAmount(), budget.getPosts().get(0).getAmount());
        assertEquals(receivedBudget.getPosts().size(), budget.getPosts().size());
        assertEquals(receivedBudget.getPosts().get(1).getCategory(), budget.getPosts().get(1).getCategory());

        dbController.deleteBudget(testUserID, budget.getYear(), budget.getMonth());

        Budget receivedBudgetAfterDelete = dbController.getBudget(testUserID, budget.getYear(), budget.getMonth());

        assertEquals(receivedBudgetAfterDelete.getPosts().size(), 0);

    }*/

}