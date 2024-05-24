import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CartManagement {
	private static Connection con = DBConnect.ConnectToCartDB();
	private static PreparedStatement pst;
	private static ResultSet rs;

	public CartManagement() {
		// Initialize the connection, statement, and result set to null
		con = null;
		pst = null;
		rs = null;
	}

	public static Boolean addProductToCartDB(Product product) {
	    String querySelect = "SELECT * FROM cart_items WHERE prod_id = ?";
	    String queryUpdate = "UPDATE cart_items SET quantity = quantity + ? WHERE prod_id = ?";
	    String queryInsert = "INSERT INTO cart_items (prod_id, prod_name, price, quantity, discount) VALUES (?, ?, ?, ?, ?)";

	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        // Check if the product exists in the cart
	        pst = con.prepareStatement(querySelect);
	        pst.setString(1, product.getProdID());
	        rs = pst.executeQuery();

	        if (rs.next()) { // If the product exists in the cart
	            pst.close();
	            pst = con.prepareStatement(queryUpdate);
	            pst.setInt(1, product.getQuantity());
	            pst.setString(2, product.getProdID());
	            pst.executeUpdate();
	        } else { // If the product doesn't exist in the cart
	            pst.close();
	            pst = con.prepareStatement(queryInsert);
	            pst.setString(1, product.getProdID());
	            pst.setString(2, product.getName());
	            pst.setDouble(3, product.getPrice());
	            pst.setInt(4, product.getQuantity());
	            pst.setDouble(5, product.getDiscount());
	            pst.executeUpdate();
	        }

	        return true; // Product added successfully
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Failed to add product
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static Boolean removeProductFromCartDB(Product product) {
		String updateQuery = "UPDATE cart_items SET quantity = quantity - ? WHERE prod_id = ? AND quantity >= ?";
		String deleteQuery = "DELETE FROM cart_items WHERE prod_id = ? AND quantity = 0";

		try {
			con.setAutoCommit(false); // Start transaction

			// Update the quantity
			pst = con.prepareStatement(updateQuery);
			pst.setInt(1, product.getQuantity()); // Specify the quantity to remove
			pst.setString(2, product.getProdID());
			pst.setInt(3, product.getQuantity()); // Ensure the quantity to remove is less than or equal to the existing
													// quantity
			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				// Delete the product if the quantity becomes 0
				pst = con.prepareStatement(deleteQuery);
				pst.setString(1, product.getProdID());
				pst.executeUpdate();

				con.commit(); // Commit the transaction
				return true;
			} else {
				con.rollback(); // Rollback the transaction if the update failed
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback(); // Rollback the transaction in case of error
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			return false;
		} finally {
			closeResources();
		}
	}

	public static void clearCart() {

		try {

			// SQL query to delete all products from the cart_items table
			String query = "DELETE FROM cart_items";
			pst = con.prepareStatement(query);

			// Execute the update
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			closeResources();
		}
	}

	public void displayCart() {

	}

	public static double calculateBill() {
	    double totalBill = 0.0;

	    try {
	        String query = "SELECT price, quantity, discount FROM cart_items";
	        pst = con.prepareStatement(query);
	        ResultSet rs = pst.executeQuery();

	        // Iterate over the ResultSet and calculate the total bill
	        while (rs.next()) {
	            double price = rs.getDouble("price");
	            int quantity = rs.getInt("quantity");
	            double discount = rs.getDouble("discount");

	            // Ensure quantity and price are non-negative
	            if (quantity < 0 || price < 0) {
	                throw new IllegalArgumentException("Quantity and price must be non-negative.");
	            }

	            // Calculate discounted price per item
	            double discountedPrice = price - (((discount)/100)*(price)); // Discounted price = Price * (1 - discount)

	            // Ensure discounted price is non-negative
	            if (discountedPrice < 0) {
	                throw new IllegalArgumentException("Discounted price cannot be negative.");
	            }

	            // Calculate total price for the item (discounted price * quantity)
	            double totalPriceForItem = discountedPrice * quantity;

	            // Add the total price for the item to the total bill
	            totalBill += totalPriceForItem;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources();
	    }

	    return totalBill;
	}


	public static void checkout() {
		String selectQuery = "SELECT prod_id, quantity FROM cart_items";
		String updateQuery = "UPDATE products.products SET quantity = quantity - ? WHERE prod_id = ?";

		try {
			// Retrieve cart items
			pst = con.prepareStatement(selectQuery);
			ResultSet rs = pst.executeQuery();

			// Establish connection to the products database
			Connection productsDBCon = DBConnect.ConnectToProductDB();

			// Update product quantities
			while (rs.next()) {
				String prodID = rs.getString("prod_id");
				int quantity = rs.getInt("quantity");

				// Update product quantity in the products table in the products database
				PreparedStatement updatePst = productsDBCon.prepareStatement(updateQuery);
				updatePst.setInt(1, quantity);
				updatePst.setString(2, prodID);
				updatePst.executeUpdate();
			}

			// Close resources and clear cart after checkout
			rs.close();
			pst.close();
			productsDBCon.close();
			clearCart();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
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

	public static int getCurrentQuantityInCart(Product product) {
		int currentQuantity = 0;

		try {
			// SQL query to retrieve the quantity of the specified product from the
			// cart_items table
			String query = "SELECT quantity FROM cart_items WHERE prod_id = ?";
			pst = con.prepareStatement(query);

			// Set the parameter
			pst.setString(1, product.getProdID());

			// Execute the query
			ResultSet rs = pst.executeQuery();

			// Check if the result set has any rows
			if (rs.next()) {
				currentQuantity = rs.getInt("quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return currentQuantity;
	}

	public static boolean isProductInCart(Product product) {
		boolean isInCart = false;

		try {
			// SQL query to check if the specified product exists in the cart_items table
			String query = "SELECT COUNT(*) AS count FROM cart_items WHERE prod_id = ?";
			pst = con.prepareStatement(query);

			// Set the parameter
			pst.setString(1, product.getProdID());

			// Execute the query
			ResultSet rs = pst.executeQuery();

			// Check if the result set has any rows
			if (rs.next()) {
				int count = rs.getInt("count");
				isInCart = (count > 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return isInCart;
	}

	


}
