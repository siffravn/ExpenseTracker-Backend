package MockupData;

import DTO.Budget;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;

public class MockedBudgetData {
    // Array of Budget and Date in Budget
    private ArrayList<Budget> budget;
    private static MockedBudgetData instance = null;
    public static MockedBudgetData getInstance(){
        if (instance== null){
            instance = new MockedBudgetData();
        }
        return instance;
    }

    //mocking the data
    public MockedBudgetData(){
        budget = new ArrayList<Budget>();
        budget.add(new Budget(300, new Date()));
    }

    public ArrayList<Budget> getBudgetList(){
        return budget;

    }

}
