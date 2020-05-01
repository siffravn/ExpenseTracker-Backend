package DTO;

import brugerautorisation.data.Bruger;

public class User {

    public String username; // studentID
    public String email;
    public String study;
    public String firstName;
    public String lastName;
    public String passwordPlaceholder;

    public User(){}

    public User(Bruger bruger){
        username = bruger.brugernavn;
        firstName = bruger.fornavn;
        lastName = bruger.efternavn;
        study = bruger.studeretning;
        email = bruger.email;
        generatePasswordPlaceholder(bruger.adgangskode.length());
    }

    public void generatePasswordPlaceholder(int length) {
        StringBuilder placeholder = new StringBuilder();
        for (int i = 0; i < length; i++){
            placeholder.append("*");
        }
        this.passwordPlaceholder = placeholder.toString();
    }
}
