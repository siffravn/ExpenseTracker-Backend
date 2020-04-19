package MockupData;

import DTO.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockedExpenseData {

    private List<Expense> expenses;

    private static MockedExpenseData instance = null;
    public  static MockedExpenseData getInstance() {
        if (instance == null) {
            instance = new MockedExpenseData();
        }
        return instance;
    }

    public MockedExpenseData() {
        expenses = new ArrayList<>();
        expenses.add(new Expense(400,"Ballade", new Date().toString(), "Hovsa"));
    }

    public List<Expense> fetchExpenses() {return expenses;}
}
