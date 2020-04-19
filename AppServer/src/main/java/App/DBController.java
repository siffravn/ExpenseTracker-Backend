package App;

import DTO.Budget;
import DTO.Expense;
import MockupData.MockedBudgetData;
import MockupData.MockedExpenseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DBController {

    MockedExpenseData mockedExpenseData = MockedExpenseData.getInstance();
    MockedBudgetData mockedBudgetData = MockedBudgetData.getInstance();
    @GetMapping("/expenses")
    public List<Expense> index() {return mockedExpenseData.fetchExpenses();
    }

    @GetMapping("/budget")
    public List<Budget> ListOfBudget(){return mockedBudgetData.getBudgetList();}


}
