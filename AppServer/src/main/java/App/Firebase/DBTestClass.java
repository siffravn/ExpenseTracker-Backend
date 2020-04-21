package App.Firebase;

import App.Firebase.FireBaseController;
import DTO.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public class DBTestClass {

    public static void main(String[] args) throws ExecutionException, InterruptedException, RemoteException, NotBoundException, MalformedURLException {



        FireBaseController dbController = new FireBaseController();
        dbController.initialize();
    }
}
