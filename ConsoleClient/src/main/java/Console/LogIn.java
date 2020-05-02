package Console;

import java.io.Serializable;

public class LogIn implements Serializable {
    private String username ="s180317";
    private String password ="180317";

    public LogIn() {}

    public LogIn(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
