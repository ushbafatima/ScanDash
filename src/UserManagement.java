import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    static Connection con = DBConnect.ConnectToUserDB();
    static PreparedStatement pst = null;
    static ResultSet rs = null;

    public UserManagement() {}

    /************ FIND USER **************/

    public static User findUser(String primaryKey) {
        User user = null;

        // Get the caller class name from the stack trace
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String callerClassName = stackTraceElements[2].getClassName();

       
        if (callerClassName.contains("ManagerWindow")) {
           user = getManagerFromDB(primaryKey);
            if (user != null) {
                user=(Manager) user;
                
            }
        } else {
            user = getCustomerFromDB(primaryKey);
            if (user != null) {
                }
        }
        return user;
    }

    /************ FIND USER **************/

    private static Manager getManagerFromDB(String username) {
        Manager manager = null;

        try {
            pst = con.prepareStatement("SELECT * FROM MANAGER WHERE username=?");
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                manager = new Manager();
                manager.setUsername(rs.getString("username"));
                manager.setPassword(rs.getString("password"));
                 }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return manager;
    }

    private static Customer getCustomerFromDB(String username) {
        Customer customer = null;

        try {
            pst = con.prepareStatement("SELECT * FROM CUSTOMER WHERE username=?");
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setUsername(rs.getString("username"));
                customer.setCardNumber(rs.getString("card_number"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return customer;
    }

    private static void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean authenticateUser(User enteredUser, User userInDB) {
        
        if (enteredUser instanceof Manager && userInDB instanceof Manager) {
            Manager enteredManager = (Manager) enteredUser;
            Manager managerInDB = (Manager) userInDB;
            
            return enteredManager.getPassword().equals(managerInDB.getPassword()); // Compare passwords for Managers
        } else if (enteredUser instanceof Customer && userInDB instanceof Customer) {
            Customer enteredCustomer = (Customer) enteredUser;
            Customer customerInDB = (Customer) userInDB;
            
            return enteredCustomer.getCardNumber().equals(customerInDB.getCardNumber()); // Compare card numbers for Customers
        }
        
        return false;
    }



    /******************* ADD USER ******************/
    public static boolean addCustomerToDB(Customer customer) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Check if the customer already exists by their username
            pst = con.prepareStatement("SELECT * FROM CUSTOMER WHERE username = ?");
            pst.setString(1, customer.getUsername());
            rs = pst.executeQuery();

            if (rs.next()) {
                // Customer with the same username already exists
                return false;
            }

            // Insert customer into the database
            pst = con.prepareStatement("INSERT INTO CUSTOMER (username, card_number) VALUES (?, ?)");
            pst.setString(1, customer.getUsername());
            pst.setString(2, customer.getCardNumber());
            pst.executeUpdate();

            // Update card availability
            pst = con.prepareStatement("UPDATE scandash_cards SET availability = ? WHERE card_number = ?");
            pst.setBoolean(1, false);
            pst.setString(2, customer.getCardNumber());
            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean removeCustomerFromDB(Customer customer) {
        Customer existingCustomer = getCustomerFromDB(customer.getUsername());

        if (existingCustomer == null) {
            return false; // Customer does not exist
        }

        try {
            // Delete customer from database
            pst = con.prepareStatement("DELETE FROM CUSTOMER WHERE username = ?");
            pst.setString(1, customer.getUsername());
            pst.executeUpdate();

            // Update card availability
            pst = con.prepareStatement("UPDATE scandash_cards SET availability = ? WHERE card_number = ?");
            pst.setBoolean(1, true);
            pst.setString(2, existingCustomer.getCardNumber());
            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources();
        }
    }

    /******************* VALIDATION METHODS ******************/
    public static boolean isValidUsername(String username) {
        if (username.contains(" ") || !username.endsWith(".scandash")) {
            return false;
        }
        return true;
    }

    

    public static String[] getAvailableCards() throws SQLException {
        try {
            pst = con.prepareStatement("SELECT card_number FROM scandash_cards WHERE availability = ?");
            pst.setBoolean(1, true);
            rs = pst.executeQuery();

            List<String> availableCardsList = new ArrayList<>();
            while (rs.next()) {
                availableCardsList.add(rs.getString("card_number"));
            }
            return availableCardsList.toArray(new String[0]);
        } finally {
            closeResources();
        }
    }
}
