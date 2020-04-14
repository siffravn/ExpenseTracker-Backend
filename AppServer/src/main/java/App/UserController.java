package App;

import DTO.User;
import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import org.springframework.web.bind.annotation.*;

import java.rmi.Naming;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class UserController {

    @RequestMapping("/uc-test")
    public String start() throws Exception {
        return "Connected";
    }

    @GetMapping("/login")
    public User login() throws Exception {
        Brugeradmin admin = connectUserDB();

        Bruger bruger = admin.hentBruger("s173998", "kk29");

        return new User(bruger);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) throws Exception {

        String username = body.get("username");
        String password = body.get("password");

        Brugeradmin admin = connectUserDB();

        Bruger bruger = admin.hentBruger(username, password);

        return new User(bruger);
    }

    private Brugeradmin connectUserDB() throws Exception{
        return (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
    }
}
