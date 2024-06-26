
public class Customer extends User {
    private String cardNumber; // private field

    public Customer(String username, String carNumber) {
        super(username);
        this.cardNumber = carNumber;
    }
    public Customer() {
    }

    // Getter method for car number
    public String getCardNumber() {
        return cardNumber;
    }

    // Setter method for car number
    public void setCardNumber(String newCarNumber) {
        this.cardNumber = newCarNumber;
    }
    
    
    public static Boolean addProductToCart( Product product) {
    	if (CartManagement.addProductToCartDB(product))
    		return true;
    	return false;
    }
	public static Boolean removeProductFromCart(Product product) {
		if (CartManagement.removeProductFromCartDB(product))
    		return true;
    	return false;
		
	}
}
