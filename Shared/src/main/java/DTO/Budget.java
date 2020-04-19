package DTO;

import java.util.ArrayList;
import java.util.Date;

public class Budget {

    public ArrayList<Integer> budget;
    public Date date;


    public void setBudget(ArrayList<Integer> budgetList) {
        this.budget = budgetList;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Integer> getBudget() {
        return budget;
    }

    public Date getDate() {
        return date;
    }
}


