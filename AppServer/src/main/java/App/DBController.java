package App;

import DTO.Budget;
import DTO.Expense;
import MockupData.MockedBudgetData;
import MockupData.MockedExpenseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
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

    @GetMapping("/budget/{year}/{month}")
    public Budget getBudget(@PathVariable("year") int year,  @PathVariable("month")int month){

        YearMonth yearMonth = YearMonth.of(year, month);

        return mockedBudgetData.getBudget(yearMonth);
    }
}
