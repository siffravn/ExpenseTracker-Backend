package Console;
import DTO.Budget;
import DTO.Expense;
import DTO.User;
import org.eclipse.persistence.sessions.Login;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Scanner;


public class BudgetRESTConsoleClient {

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

     public Budget getBudget(int year, int month){

        Client client = ClientBuilder.newClient();
        String path = "/budget/";

        String url = domain + path + year + "/" + month;

         return client
                 .target(domain)
                 .path(url)
                 .request(MediaType.APPLICATION_JSON)
                 .get(Budget.class);
     }

    public void updateBudget(Budget budget){

    }

     public ArrayList<Expense> getExpenses(int year, int month){
        return null;
     }

     public void updateExpenses(ArrayList<Expense> expenses){
        

     }

}