import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    static Connection con = null;

    // Method to establish connection to the database
    public static Connection ConnectToUserDB() {
        try {
            // Establish connection to the MySQL database named "library" on localhost at port 3306
            // with username "root" and password "MySQL@2773778"
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "MySQL@2773778");
        } catch (SQLException ex) {
            // Log any SQLException that occurs during the connection attempt
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con; // Return the established connection (may be null if connection fails)
    }
    
    public static Connection ConnectToProductDB() {
        try {
            // Establish connection to the MySQL database named "library" on localhost at port 3306
            // with username "root" and password "MySQL@2773778"
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "MySQL@2773778");
        } catch (SQLException ex) {
            // Log any SQLException that occurs during the connection attempt
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con; // Return the established connection (may be null if connection fails)
    }
    
    public static Connection ConnectToCartDB() {
        try {
            // Establish connection to the MySQL database named "library" on localhost at port 3306
            // with username "root" and password "MySQL@2773778"
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart", "root", "MySQL@2773778");
        } catch (SQLException ex) {
            // Log any SQLException that occurs during the connection attempt
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con; // Return the established connection (may be null if connection fails)
    }
}
