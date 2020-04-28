package App;

import DTO.Category;
import DTO.Budget;
import DTO.Expense;
import MockupData.MockedBudgetData;
import MockupData.MockedCategoryData;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = {"https://expense-tracker-dfe53.web.app/", "http://localhost:4200"})
@RestController
public class DBController {
    MockedBudgetData mockedBudgetData = MockedBudgetData.getInstance();
    MockedCategoryData mockedCategoryData = MockedCategoryData.getInstance();
    FireBaseController fireBaseController = FireBaseController.getInstance();

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("******** Initialized firebase controller ********");
        fireBaseController.initialize();
    }

    @GetMapping("{username}/expenses")
    public ResponseEntity<?> getAllExpenses(@PathVariable("username") String username)
            throws ExecutionException, InterruptedException
    {
        try {
            List<Expense> expenses = fireBaseController.getExpenses(username);
            return ResponseEntity.ok().body(expenses);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}/expenses/{year}/{month}")
    public ResponseEntity<?> getExpenses(
            @PathVariable("username") String username,
            @PathVariable("year") int year,
            @PathVariable("month")int month)
            throws ExecutionException, InterruptedException
    {
        try {
            List<Expense> expenses = fireBaseController.getExpenses(username, year, month);
            return ResponseEntity.ok().body(expenses);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{username}/expenses/{year}/{month}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExpenses(
            @PathVariable("username") String username,
            @PathVariable("year") int year,
            @PathVariable("month")int month,
            @RequestBody ArrayList<Expense> expenses)
            throws ExecutionException, InterruptedException
    {
        fireBaseController.updateExpenses(username, year, month, expenses);
    }

    // TODO in use?
    @GetMapping("/category")
    public List<Category> index2() {return mockedCategoryData.fetchCategories();
    }


    @GetMapping("{username}/budget")
    @ResponseBody
    public ResponseEntity<?> getAllBudgets(
            @PathVariable("username") String username)
            throws ExecutionException, InterruptedException
    {
        try {
            List<Budget> budgets = fireBaseController.getBudgets(username);
            return ResponseEntity.ok().body(budgets);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{username}/budget/{year}/{month}")
    @ResponseBody
    public ResponseEntity<?> getBudget(
            @PathVariable("username") String username,
            @PathVariable("year") int year,
            @PathVariable("month")int month)
            throws ExecutionException, InterruptedException
    {
        try {
            Budget budget = fireBaseController.getBudget(username, year, month);
            return ResponseEntity.ok().body(budget);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/budget/{year}/{month}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBudget(
            @PathVariable("username") String username,
            @PathVariable("year") int year,
            @PathVariable("month")int month,
            @RequestBody Budget budget)
            throws ExecutionException, InterruptedException
    {
        fireBaseController.updateBudget(username, year, month, budget);
    }
}
