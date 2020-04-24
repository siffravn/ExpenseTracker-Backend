package App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTracker {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTracker.class, args);
    }

}
//TODO: add server.address=dist.saluton.dk to application.properties