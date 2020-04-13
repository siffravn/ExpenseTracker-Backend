package DTO;

import brugerautorisation.data.Bruger;

public class User {

    public String username; // studentID
    public String firstName;
    public String lastName;
    public String study;
    public String email;

    public User(Bruger bruger){
        username = bruger.brugernavn;
        firstName = bruger.fornavn;
        lastName = bruger.efternavn;
        study = bruger.studeretning;
        email = bruger.email;
    }
}
