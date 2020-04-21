package MockupData;

import DTO.Budget;
import DTO.BudgetPost;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;

public class MockedBudgetData {
    // Array of Budget and Date in Budget
    private ArrayList<Budget> budgets = new ArrayList<Budget>();
    private static MockedBudgetData instance = null;

    public static MockedBudgetData getInstance(){
        if (instance== null){
            instance = new MockedBudgetData();
        }
        return instance;
    }

    //mocking the data
    public MockedBudgetData(){
        Budget b = new Budget(YearMonth.of(2020, 4));
        b.addPost(new BudgetPost("Groceries", 1500));
        b.addPost(new BudgetPost("Bills", 5000));
        b.addPost(new BudgetPost("Entertainment", 500));
        budgets.add(b);

        Budget c = new Budget(YearMonth.of(2020, 5));
        c.addPost(new BudgetPost("Groceries", 1500));
        c.addPost(new BudgetPost("Bills", 200));
        c.addPost(new BudgetPost("Vacation", 5000));
        budgets.add(c);
    }

    public ArrayList<Budget> getBudgetList(){
        return budgets;
    }

    public Budget getBudget(YearMonth yearMonth){
        for (Budget budget : budgets){
            if (budget.getDate().equals(yearMonth)) return budget;
        }
        return null;
    }

    public void createBudget(Budget budget){
        budgets.add(budget);
    }

}
