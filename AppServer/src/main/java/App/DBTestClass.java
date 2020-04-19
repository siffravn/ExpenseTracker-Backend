package App;

import DTO.User;
import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public class DBTestClass {

    public static void main(String[] args) throws ExecutionException, InterruptedException, RemoteException, NotBoundException, MalformedURLException {

        Brugeradmin brugeradmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        Bruger bruger = brugeradmin.hentBruger("s185037", "magnusbrok");

        bruger.fornavn = "ad";

        User testUser = new User(bruger);

        FireBaseController dbController = new FireBaseController();
        dbController.initialize();
        dbController.saveUser(testUser);
    }
}
