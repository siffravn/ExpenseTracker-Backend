package App;

import DTO.Budget;
import DTO.BudgetPost;
import DTO.Expense;
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
    private static FireBaseController instance = null;
    public  static FireBaseController getInstance() {
        if (instance == null) {
            instance = new FireBaseController();
        }
        return instance;
    }


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

    // TODO implement this
    public ArrayList<Expense> getExpenses(String username, int year, int month) {
        return null;
    }

    // TODO implement this
    // TODO implement to delete non-dublicate
    public void updateExpenses(String username, int year, int month, ArrayList<Expense> expenses) {

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

    // TODO implement this to delete for year month
    public void deleteExpense(String studentID, String expenseID) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection("users").document(studentID).
                collection("expenses").document(expenseID).delete();

        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }

    /**
     * Method for updating/saving af budget. If the budget recieves a budget without any Posts it deletes the budget instead.
     * @param studentID the owner of the budget
     * @param year redundant atm
     * @param month --"--
     * @param budget The budget being updated
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void updateBudget(String studentID, int year, int month, Budget budget) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        if (budget.getPosts().size() != 0) {

            //deleteBudget(studentID, budget.getYear(), budget.getMonth());
            CollectionReference postCollection = db.collection("users").document(studentID).collection("budgets")
                    .document(budget.getYear()+"-"+budget.getMonth()).collection("posts");
            deleteCollection(postCollection, 10);



            for (BudgetPost post : budget.getPosts()){

                Map<String, Object> docData = new HashMap<>();
                docData.put("category", post.getCategory());
                docData.put("amount", post.getAmount());

                ApiFuture<WriteResult> future = db.collection("users").document(studentID).collection("budgets")
                        .document(budget.getYear()+"-" + budget.getMonth()).collection("posts")
                        .document(post.getCategory()).set(docData);

                System.out.println("Update time : " + future.get().getUpdateTime());

            }
        } else deleteBudget(studentID, budget.getYear(), budget.getMonth());
    }

    //TODO implement to get ALL budgets for the user
    public ArrayList<Budget> getBudgets(String username) {
        return null;
    }

    public Budget getBudget(String studentID, int year, int month) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        Budget receivedBudget = new Budget(year, month);
        ArrayList<BudgetPost> posts = new ArrayList<>();

        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection("users").document(studentID).collection("budgets")
                .document(year+"-"+month).collection("posts").get();

        // future.get() blocks on response

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (DocumentSnapshot document : documents) {
            posts.add(document.toObject(BudgetPost.class));
            System.out.println(document.getId() + " => " + document.toObject(Expense.class));
        }
        receivedBudget.setPosts(posts);

        return receivedBudget;
    }

    // TODO delete entire budget instance
    public void deleteBudget(String studentID, int year, int month) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();


        CollectionReference postCollection = db.collection("users").document(studentID).collection("budgets")
                .document(year+"-"+month).collection("posts");

        deleteCollection(postCollection, 10);

        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection("users").document(studentID).
                collection("budgets").document(year+"-"+month).delete();

        System.out.println("Update time : " + writeResult.get().getUpdateTime());

    }

    /** Delete a collection in batches to avoid out-of-memory errors.
     * Batch size may be tuned based on document size (atmost 1MB) and application requirements.
     */
    private void deleteCollection(CollectionReference collection, int batchSize) {
        try {
            // retrieve a small batch of documents to avoid out-of-memory errors
            ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
            int deleted = 0;
            // future.get() blocks on document retrieval
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                ++deleted;
            }
            if (deleted >= batchSize) {
                // retrieve and delete another batch
                deleteCollection(collection, batchSize);
            }
        } catch (Exception e) {
            System.err.println("Error deleting collection : " + e.getMessage());
        }
    }

}

