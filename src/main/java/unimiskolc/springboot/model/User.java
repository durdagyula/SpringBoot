package unimiskolc.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String userName;
    private int userCredit;
    private boolean isAdmin;

    public User(){}

    public User(String userName, int userCredit, boolean isAdmin) {
        this.userName = userName;
        this.userCredit = userCredit;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserCredit() {
        return userCredit;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public long getId() {
        return id;
    }

}
