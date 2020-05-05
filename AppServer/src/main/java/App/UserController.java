package App;

import DTO.User;
import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.Naming;
import java.util.Map;

@CrossOrigin(origins = {"*","http://localhost:4200"})
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
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) throws Exception {

        String username = body.get("username");
        String password = body.get("password");

        try{
            Brugeradmin admin = connectUserDB();
            Bruger bruger = admin.hentBruger(username, password);

            return ResponseEntity.status(HttpStatus.OK).body(new User(bruger));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password is incorrect. Please try again.");
        }
    }

    @PostMapping("/user/{id}/password")
    public User updatePassword(@PathVariable String id, @RequestBody Map<String, String> body) throws Exception {

        String currentPassword = body.get("currentPassword");
        String newPassword1 = body.get("newPassword");
        String newPassword2 = body.get("newPasswordConfirmation");

        if (newPassword1.equals(newPassword2)){

            Brugeradmin admin = connectUserDB();
            Bruger bruger = admin.ændrAdgangskode(id, currentPassword, newPassword1);

            return new User(bruger);
        }

        return null;
    }

    @GetMapping("/user/{id}/password")
    public String sendPassword(@PathVariable String id) throws Exception {

        try {
            Brugeradmin admin = connectUserDB();
            admin.sendGlemtAdgangskodeEmail(id, "Send from Siff");
            return "Email sent";
        }catch (Exception e){
            return "could not send email";
        }
    }

    private Brugeradmin connectUserDB() throws Exception{
        return (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
    }
}
