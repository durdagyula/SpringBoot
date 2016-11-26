package unimiskolc.springboot;

public class User {
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

}
