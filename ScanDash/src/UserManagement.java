import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    static Connection con = DBConnect.ConnectToDB();
    static PreparedStatement pst = null;
    static ResultSet rs = null;

    public UserManagement() {

    }

    /************ FIND USER **************/

    public static User findUser(String primaryKey) {
        User user = null;

        // Get the caller class name from the stack trace
        String callerClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        
        // Check if the caller class is the manager window
        if (callerClassName.equals("ManagerWindow$2")) {
            user = getManagerFromDB(primaryKey);
            return user;
        } 
            // If not the manager window, first check if the primary key matches a manager's username
            user = getCustomerFromDB(primaryKey);

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

    public static boolean authenticateUser(String username, String passKey) {
        User user = findUser(username);

        // Check if the user object is an instance of Manager or Customer
        if (user != null) {
            if (user instanceof Manager) {
                Manager manager = (Manager) user;
                return manager.getPassword().equals(passKey);

            } else if (user instanceof Customer) {
                Customer customer = (Customer) user;
                return customer.getCardNumber().equals(passKey);
            }
        }
        return false;
    }
    /******************* ADD USER ******************/
    public static Boolean addCustomerToDB(Customer customer) {
        try {
            // Prepare SQL statement to check if the username or card number already exists
            pst = con.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE username = ?");
            pst.setString(1, customer.getUsername());
            rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            // If count is greater than 0, it means a record with the same username already exists
            if (count > 0) {
                return false;
            }

            // Prepare SQL statement to insert user details into the database
            pst = con.prepareStatement("INSERT INTO CUSTOMER (username, card_number) VALUES (?, ?)");

            // Set values for the parameters in the SQL statement using user details
            pst.setString(1, customer.getUsername());
            pst.setString(2, customer.getCardNumber());

            // Execute the SQL statement to insert the user into the database
            pst.executeUpdate();

            // Update availability of the card to not available
            pst = con.prepareStatement("UPDATE scandash_cards SET availability = ? WHERE card_number = ?");
            pst.setBoolean(1, false); // Set availability to false
            pst.setString(2, customer.getCardNumber());
            pst.executeUpdate();

            // Return true to indicate successful addition of user to the database
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            // Return false to indicate unsuccessful addition of user to the database
            return false;
        }
    }

    public static Boolean removeCustomerFromDB(Customer customer) {
    	
        try {
            // Check if the user exists in the database
            pst = con.prepareStatement("SELECT * FROM CUSTOMER WHERE username = ?");
            pst.setString(1, customer.getUsername());
            rs = pst.executeQuery();

            // If the user exists, delete the user from the database
            if (rs.next()) {
            	customer=getCustomerFromDB(customer.getUsername());
                // Prepare SQL statement to delete the user from the database
                pst = con.prepareStatement("DELETE FROM CUSTOMER WHERE username = ?");
                pst.setString(1, customer.getUsername());
                pst.executeUpdate();

                // Update availability of the card to available
                pst = con.prepareStatement("UPDATE scandash_cards SET availability = ? WHERE card_number = ?");
                pst.setBoolean(1, true); // Set availability to true
                pst.setString(2, customer.getCardNumber());
                pst.executeUpdate();

                return true; // User removed successfully
            } else {
                // User not found in the database
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Return false to indicate unsuccessful removal of user from the database
            return false;
        } finally {
            // Close resources
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
    }




	/******************* ADD USER ******************/
	
	public static boolean isValidUsername(String username) {
	    	    
	    // Check if username has spaces
	    if (username.contains(" ")) {
	        return false;
	    }
	    
	    // Check if username has the format username.scandash
	    if (!username.endsWith(".scandash")) {
	        return false;
	    }
	    
	    // If all conditions pass, username is valid
	    return true;
	}
	public static boolean isValidCardNumber(String cardNumber) {
	   

	    // Check if card number contains non-digit characters
	    if (!cardNumber.matches("\\d+")) {
	        return false;
	    }

	    // Check if card number has a valid length (between 13 and 16 digits)
	    if (cardNumber.length() < 13 || cardNumber.length() > 16) {
	        return false;
	    }

	    // Check if card number is all zeros
	    if (cardNumber.matches("^0+$")) {
	        return false;
	    }

	    // Perform Luhn algorithm validation
	    int sum = 0;
	    boolean alternate = false;
	    for (int i = cardNumber.length() - 1; i >= 0; i--) {
	        int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
	        if (alternate) {
	            digit *= 2;
	            if (digit > 9) {
	                digit -= 9;
	            }
	        }
	        sum += digit;
	        alternate = !alternate;
	    }
	    return (sum % 10 == 0);
	}
    public static String[] getAvailableCards() throws SQLException{
       
        try {
            String query = "SELECT card_number FROM scandash_cards WHERE availability = ?";
            pst = con.prepareStatement(query);
            pst.setBoolean(1, true);
            rs = pst.executeQuery();

            List<String> availableCardsList = new ArrayList<>();
            while (rs.next()) {
                availableCardsList.add(rs.getString("card_number"));
            }
            return availableCardsList.toArray(new String[0]);
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
        }
    }
	}
