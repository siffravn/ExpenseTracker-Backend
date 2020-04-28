package Console;
import DTO.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.Scanner;


public class BudgetRESTConsoleClient {

    static String domain ="http://localhost:3344";
    static String clientID;
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        login(client);

    }

    public static void login(Client client) {
        System.out.println("Welcome to ExpenseTracker!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your login information:");
        System.out.println("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();

        String url = domain + "/login";
        LogIn logIn = new LogIn(username,password);

        Response response = client.target(url).request().post(Entity.json(logIn), Response.class);
        User result = response.readEntity(User.class);

        System.out.println(result);
    }
}
