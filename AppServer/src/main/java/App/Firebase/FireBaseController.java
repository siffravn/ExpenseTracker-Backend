package App.Firebase;

import DTO.Expense;
import DTO.User;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class FireBaseController {



    @PostConstruct
    public void initialize() {

        FileInputStream serviceAccount = null;

        try {
            serviceAccount = new FileInputStream("./serviceAccount.json");
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

    public String saveUser(User user) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> docData = new HashMap<>();
        docData.put("category", Arrays.asList("west_coast", "social"));

        ApiFuture<WriteResult> future = db.collection("users").document(user.toString()).collection("categories").document("1").set(docData);



        ApiFuture<WriteResult> collectionsApiFuture =
                db.collection("users").document(user.toString()).set(user);

        //db.collection("users").document(user.username).collection("newcollection");


        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public String updateExpenses(ArrayList<Expense> expenses, String studentID) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();



        for (Expense expense : expenses){

            ApiFuture<WriteResult> collectionsApiFuture = db.collection("users").document("s173998").collection("categoriessss").document().set(expense);

        }

        ApiFuture<WriteResult> collectionsApiFuture = db.collection("users").document("s173998").set(expenses.get(1));
        //ApiFuture<WriteResult> collectionsApiFuture = db.collection("users").document(studentID).update("expenses", expenses);

        Map<String, Object> docData = new HashMap<>();
        docData.put("category", Arrays.asList("west_coast", "social"));

        ApiFuture<WriteResult> future = db.collection("users").document("s173998test").collection("categories").document("1").set(docData);


        return "hejk";
    }




}

