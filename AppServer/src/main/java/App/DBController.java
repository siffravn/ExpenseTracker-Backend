package App;

import DTO.Category;
import DTO.Expense;
import MockupData.MockedCategoryData;
import MockupData.MockedExpenseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DBController {

    MockedExpenseData mockedExpenseData = MockedExpenseData.getInstance();
    MockedCategoryData mockedCategoryData = MockedCategoryData.getInstance();

    @GetMapping("/expenses")
    public List<Expense> index() {return mockedExpenseData.fetchExpenses();
    }

    @GetMapping("/category")
    public List<Category> index2() {return mockedCategoryData.fetchCategories();
    }

}
