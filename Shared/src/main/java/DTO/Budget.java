package DTO;

import java.time.YearMonth;
import java.util.ArrayList;

public class Budget {

    private YearMonth date;
    ArrayList<BudgetPost> posts = new ArrayList<>();

    public Budget(YearMonth date) {
        this.date = date;
    }

    public Budget(YearMonth date, ArrayList<BudgetPost> posts) {
        this.date = date;
        this.posts = posts;
    }

    public void setDate(YearMonth date) {
        this.date = date;
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

    public void addPost(BudgetPost budgetPost){
        posts.add(budgetPost);
    }
}


