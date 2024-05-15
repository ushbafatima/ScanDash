
public class Manager extends User {
    private String password; // private field

    public Manager(String username, String password) {
        super(username);
        this.password = password;
    }
    public Manager() {
       
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }

    // Setter method for password
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

	
}

