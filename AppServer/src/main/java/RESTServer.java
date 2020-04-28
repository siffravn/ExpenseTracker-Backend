import brugerautorisation.transport.soap.Brugeradmin;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.rmi.Naming;

public class RESTServer {

    public static Javalin app;

    public static void main(String[] args) throws Exception {
        app = Javalin.create().start(3344);

        setupResources();

    }

    public static void setupResources() throws Exception {

        app.get("", ctx -> ctx.redirect("/login"));
        app.get("/login", ctx -> login(ctx));

    }

    private static void login(Context ctx) throws Exception {

        String userID = ctx.queryParam("username");
        String password = ctx.queryParam("password");
        Brugeradmin brugeradmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        if (userID == null || password == null) {
            ctx.html("please login");
        } else {
            try {
                brugeradmin.hentBruger(userID, password);
                ctx.html("login successful");
            }catch (Exception e) {
                ctx.html("access denied");
            }
        }
    }
}
