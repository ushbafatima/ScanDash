public abstract class User {
    private String username;

    // Constructors
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    
    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }
}
