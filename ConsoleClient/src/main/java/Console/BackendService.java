package Console;
import DTO.Budget;
import DTO.Expense;
import DTO.User;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BackendService {

    private String domain ="http://localhost:3344"; // TODO change when server is deployed
    private Client client = ClientBuilder.newClient();

    public User login(LogIn login) {

        return client
                .target(domain)
                .path("/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(login, MediaType.APPLICATION_JSON))
                .readEntity(User.class);
     }

     public Budget getBudget(String id, YearMonth yearMonth){

        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        String path = id + "/budget/" + year + "/" + month;

         return client
                 .target(domain)
                 .path(path)
                 .request(MediaType.APPLICATION_JSON)
                 .get(Budget.class);
     }

    public void updateBudget(Budget budget){

    }

    public ArrayList<Expense> getExpenses(String id, YearMonth yearMonth){

        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        String path = id + "/expenses/" + year + "/" + month;

        ArrayList<Expense> expenseArrayList = client
                .target(domain)
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<ArrayList<Expense>>(){});

        return expenseArrayList;
    }

     public void updateExpenses(ArrayList<Expense> expenses){
     }

}