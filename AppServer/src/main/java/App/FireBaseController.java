package App;

import DTO.Budget;
import DTO.BudgetPost;
import DTO.Expense;
import DTO.User;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class FireBaseController {


    @PostConstruct
    public void initialize() {

        FileInputStream serviceAccount = null;

        try {
            serviceAccount = new FileInputStream("serviceAccount.json");
        } catch (FileNotFoundException e) {
            System.out.println("No service key in project");
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://expense-tracker-dfe53.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }


        FirebaseApp.initializeApp(options);

    }

    @PostConstruct
    public void initializeForTest() {

        FileInputStream serviceAccount = null;

        try {
            serviceAccount = new FileInputStream("C:\\Users\\magnu\\JavaSchool\\BackEndUdvikling\\serviceAccount.json");
        } catch (FileNotFoundException e) {
            System.out.println("No service key in project");
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://expense-tracker-dfe53.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }


        FirebaseApp.initializeApp(options);

    }




    public ArrayList<Expense> getExpenses(String studentID) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();
        ArrayList<Expense> expenses = new ArrayList<>();

        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection("users").document(studentID).collection("expenses").get();

        // future.get() blocks on response

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (DocumentSnapshot document : documents) {
            expenses.add(document.toObject(Expense.class));
            System.out.println(document.getId() + " => " + document.toObject(Expense.class));
        }


        return expenses;
    }

    public void updateExpenses(String studentID, ArrayList<Expense> expenses) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();


        for (Expense expense : expenses){

            Map<String, Object> docData = new HashMap<>();
            docData.put("amount", expense.getAmount());
            docData.put("category", expense.getCategory());
            docData.put("date", expense.getDate());
            docData.put("note", expense.getNote());

            ApiFuture<WriteResult> future = db.collection("users").document(studentID).collection("expenses").document(expense.getDate()).set(docData);
            System.out.println("Update time : " + future.get().getUpdateTime());

        }
    }

    public void deleteExpense(String studentID, String expenseID) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection("users").document(studentID).
                collection("expenses").document(expenseID).delete();
// ...
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }

    public void saveBudget(String studentID, Budget budget) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        for (BudgetPost post : budget.getPosts()){

            Map<String, Object> docData = new HashMap<>();
            docData.put("category", post.getCategory());
            docData.put("amount", post.getAmount());

            ApiFuture<WriteResult> future = db.collection("users").document(studentID).collection("budgets")
                    .document(budget.getYear()+"-" + budget.getMonth()).collection("posts")
                    .document(post.getCategory()).set(docData);

            System.out.println("Update time : " + future.get().getUpdateTime());

        }
    }

}
