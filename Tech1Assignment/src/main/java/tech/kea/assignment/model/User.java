package tech.kea.assignment.model;

public class User
{
    // User egenskaber
    private String username;
    private String password;
    private int userID;

    // Constructor som er tom. Laves så der er en default constructor.
    public User(){}

    public User(String username){
        this(0, username, null);
    }

    public User(String username, String password){
        this(0, username, password);
    }

    public User(int id, String username){
        this(id, username, null);
    }

    // Constructor som sætter egenskaberne for User objektet
    public User(int id, String username, String password){
        // Sæt egenskaberne
        this.username = username;
        this.password = password;
        this.userID = id;
    }

    // Getters & setters <-- start -->
    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    // Getters & setters <-- /slut -->
}