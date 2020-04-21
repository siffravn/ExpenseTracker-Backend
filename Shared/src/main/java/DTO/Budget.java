package DTO;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;

public class Budget implements Serializable {

    private int year;
    private int month;
    ArrayList<BudgetPost> posts = new ArrayList<>();

    public Budget() {}

    public Budget(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public Budget(int year, int month, ArrayList<BudgetPost> posts) {
        this.year = year;
        this.month = month;
        this.posts = posts;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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


