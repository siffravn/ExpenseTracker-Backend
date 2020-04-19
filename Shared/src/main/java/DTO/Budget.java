package DTO;

import java.util.ArrayList;
import java.util.Date;

public class Budget {

    public int budget;
    public Date date;

    public Budget(int budget, Date date) {
        this.budget = budget;
        this.date = date;
    }
    public void setBudget(int budgetList) {
        this.budget = budgetList;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBudget() {
        return budget;
    }

    public Date getDate() {
        return date;
    }
}


