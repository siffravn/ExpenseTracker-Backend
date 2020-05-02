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

     // TODO database is inconsistent? - debugged and confirmed correct data is sent to server...
    public void updateBudget(String id, Budget budget){

        String path = id + "/budget/" + budget.getYear() +"/" + budget.getMonth();
         client.target(domain).path(path)
                 .request(MediaType.APPLICATION_JSON)
                 .post(Entity.entity(budget, MediaType.APPLICATION_JSON));
    }

    public ArrayList<Expense> getExpenses(String id, YearMonth yearMonth){

        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        String path = id + "/expenses/" + year + "/" + month;

        return client
                .target(domain)
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<ArrayList<Expense>>(){});
    }

    // TODO does not work...
     public void updateExpenses(String id, YearMonth yearMonth, ArrayList<Expense> expenses){
         String path = id + "/expenses/" + yearMonth.getYear() +"/" + yearMonth.getMonth();

         client.target(domain).path(path)
                 .request(MediaType.APPLICATION_JSON)
                 .put(Entity.entity(expenses, MediaType.APPLICATION_JSON));

    }

}