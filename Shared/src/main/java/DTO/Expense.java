package DTO;

public class Expense {
    double amount;
    String category;
    String date;
    String note;


    // empty constructor for Firebase integration -Magnus
    public Expense() {

    }

    public  Expense(double amount, String category, String date, String note) {
        this.setAmount(amount);
        this.setCategory(category);
        this.setDate(date);
        this.setNote(note);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
