package App;

import DTO.Expense;
import MockupData.MockedExpenseData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
public class DBController {

    MockedExpenseData mockedExpenseData = MockedExpenseData.getInstance();

    @GetMapping("/expenses")
    public List<Expense> index() {return mockedExpenseData.fetchExpenses();
    }
}
