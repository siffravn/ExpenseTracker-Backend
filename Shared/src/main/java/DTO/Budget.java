package DTO;

import java.time.YearMonth;
import java.util.ArrayList;

public class Budget {

    public int budget;
    private YearMonth date;
    ArrayList<BudgetPost> posts;

    public Budget(int budget, YearMonth date, ArrayList<BudgetPost> posts) {
        this.budget = budget;
        this.date = date;
        this.posts = posts;
    }
    public void setBudget(int budgetList) {
        this.budget = budgetList;
    }

    public void setDate(YearMonth date) {
        this.date = date;
    }

    public int getBudget() {
        return budget;
    }

    public YearMonth getDate() {
        return date;
    }

    public ArrayList<BudgetPost> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<BudgetPost> posts) {
        this.posts = posts;
    }
}


