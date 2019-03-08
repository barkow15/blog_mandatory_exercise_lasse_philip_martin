package tech.kea.assignment.model;

public class User
{
    // User egenskaber
    private String username;
    private String password;

    // Constructor som er tom. Laves så der er en default constructor.
    public User(){}

    // Constructor som sætter egenskaberne for User objektet
    public User(String username, String password){
        // Sæt egenskaberne
        this.username = username;
        this.password = password;
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
    // Getters & setters <-- /slut -->
}