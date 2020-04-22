package App;

import DTO.Category;
import DTO.Budget;
import DTO.Expense;
import MockupData.MockedBudgetData;
import MockupData.MockedCategoryData;
import MockupData.MockedExpenseData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
public class DBController {

    MockedExpenseData mockedExpenseData = MockedExpenseData.getInstance();
    MockedBudgetData mockedBudgetData = MockedBudgetData.getInstance();
    MockedCategoryData mockedCategoryData = MockedCategoryData.getInstance();

    @GetMapping("/expenses")
    public List<Expense> index() {return mockedExpenseData.fetchExpenses();
    }

    @GetMapping("/category")
    public List<Category> index2() {return mockedCategoryData.fetchCategories();
    }


    @GetMapping("/budget")
    @ResponseBody
    public List<Budget> ListOfBudget(){return mockedBudgetData.getBudgetList();}

    @GetMapping("/budget?year={year}&month={month}")
    @ResponseBody
    public Budget getBudget(
            @PathVariable("year") int year,
            @PathVariable("month")int month)
    {

        return mockedBudgetData.getBudget(year, month);
    }

    @PostMapping("/budget")
    public void creatBudget(@RequestBody Budget budget){
        mockedBudgetData.createBudget(budget);
    }
}
