package App;

import DTO.Category;
import DTO.Budget;
import DTO.Expense;
import MockupData.MockedBudgetData;
import MockupData.MockedCategoryData;
import MockupData.MockedExpenseData;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
public class DBController {

    MockedExpenseData mockedExpenseData = MockedExpenseData.getInstance();
    MockedBudgetData mockedBudgetData = MockedBudgetData.getInstance();
    MockedCategoryData mockedCategoryData = MockedCategoryData.getInstance();
    FireBaseController fireBaseController = FireBaseController.getInstance();

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("******** Initialized firebase controller ********");
        fireBaseController.initialize();
    }

    @GetMapping("/expenses/{username}")
    public List<Expense> index(@PathVariable("username") String usename)
            throws ExecutionException, InterruptedException
    {
        return fireBaseController.getExpenses(usename);
    }

    @GetMapping("/category")
    public List<Category> index2() {return mockedCategoryData.fetchCategories();
    }


    @GetMapping("/budget")
    @ResponseBody
    public List<Budget> ListOfBudget(){return mockedBudgetData.getBudgetList();}

    @GetMapping("/budget/{username}/{year}/{month}")
    @ResponseBody
    public Budget getBudget(
            @PathVariable("username") String username,
            @PathVariable("year") int year,
            @PathVariable("month")int month)
            throws ExecutionException, InterruptedException
    {
        return fireBaseController.getBudget(username, year, month);

    }

    @PostMapping("/{username}/budget")
    public void creatBudget(
            @PathVariable("username") String username,
            @RequestBody Budget budget)
            throws ExecutionException, InterruptedException
    {
        fireBaseController.updateBudget(username, budget);
    }
}
