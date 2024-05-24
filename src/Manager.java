
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
	
	
	public static Boolean addProduct(Product product) {
		if (ProductManagement.addProductToDB(product))
			return true;
		return false;

	}


	public static Boolean removeProduct(Product product) {
		
		if (ProductManagement.removeProductFromDB(product))
			return true;
		return false;
	}

	public static Boolean editProductDetails(Product product) {
		if (ProductManagement.addProductToDB(product))
			return true;
		return false;

	}

	

	public static Boolean addCustomer(Customer customer) {
		if (UserManagement.addCustomerToDB(customer))
			return true;
		return false;
	}

	public static Boolean removeCustomer(Customer customer) {
		if (UserManagement.removeCustomerFromDB(customer))
			return true;
		return false;

	}

}
