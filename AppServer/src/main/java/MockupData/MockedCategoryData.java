package MockupData;

import DTO.Category;

import java.util.ArrayList;
import java.util.List;

public class MockedCategoryData {

    private List<Category> categories;

    private static MockedCategoryData instance = null;
    public static MockedCategoryData getInstance() {
        if (instance == null) {
            instance = new MockedCategoryData();
        }
        return instance;
    }

    public MockedCategoryData() {
        categories = new ArrayList<>();
        categories.add(new Category("Fææzt"));
    }

    public List<Category> fetchCategories() {return categories;}
}