package model;

import java.util.ArrayList;
import java.util.List;

public class Login {

    private long uid;
    private String Username;
    private String Password;
    private List<Login> allLogins = new ArrayList<>();


    /**
     * constructs a patient from the given params.
     *
     * @param Username
     * @param Password
     */
    public Login(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    /**
     * constructs a patient from the given params.
     * @param uid
     * @param Username
     * @param Password
     */
    public Login(long uid, String Username, String Password) {
        this.uid = uid;
        this.Username = Username;
        this.Password = Password;
    }

    public Login(String username) {
    }


    public long getuid() {
        return uid;
    }

    public String getUsername() {

        return Username;
    }


    public String getPassword() {

        return Password;
    }


    public String toString() {
        return this.Username + this.Password;
    }
}
