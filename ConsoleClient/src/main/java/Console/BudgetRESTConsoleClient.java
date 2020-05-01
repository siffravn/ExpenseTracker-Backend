package Console;
import DTO.User;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
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

        WebTarget webTarget = client.target(domain);

        WebTarget authTarget = webTarget.path("/login");

        Invocation.Builder invocationBuilder = authTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(logIn, MediaType.APPLICATION_JSON));

        User result = response.readEntity(User.class);

        System.out.println(result.firstName);
     }
}