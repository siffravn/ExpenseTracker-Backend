package Console;
import DTO.Budget;
import DTO.Expense;
import DTO.User;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BudgetRESTConsoleClient {

    static String domain ="http://localhost:3344"; // TODO change when server is deployed
    static User user;
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        getExpenses(2020,5);
    }

    public void login(Client client) {
        System.out.println("Welcome to ExpenseTracker!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your login information:");
        System.out.println("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();

        LogIn logIn = new LogIn(username,password);

        WebTarget webTarget = client.target(domain);

        WebTarget authTarget = webTarget.path("/login");

        Invocation.Builder invocationBuilder = authTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(logIn, MediaType.APPLICATION_JSON), Response.class);

        User user = response.readEntity(User.class);

        System.out.println("Your username is: " + user.username);
     }

     public Budget getBudget(int year, int month){

        Client client = ClientBuilder.newClient();
        String path = "/budget/";

        String url = path + year + "/" + month;

         return client.target(domain)
                 .path(url)
                 .request(MediaType.APPLICATION_JSON)
                 .get(Budget.class);
     }

    public void updateBudget(Budget budget){

    }

    public static ArrayList<Expense> getExpenses(int year, int month){
        Client client = ClientBuilder.newClient();
        String path="/expenses/";
        String url =  path + year + "/" + month;

            ArrayList<Expense> expenseArrayList = client
                    .target(domain)
                    .path(url)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Response.class)
                    .readEntity(new GenericType<ArrayList<Expense>>(){});
            return expenseArrayList;
    }

     public void updateExpenses(ArrayList<Expense> expenses){
     }

}