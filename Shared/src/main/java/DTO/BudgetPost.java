package DTO;

import java.io.Serializable;

public class BudgetPost implements Serializable {

    private String category;
    private int amount;

    public BudgetPost() {}

    public BudgetPost(String category, int amount){
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
