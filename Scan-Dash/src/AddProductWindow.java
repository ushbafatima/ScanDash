import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddProductWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IDField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField quantityField;
	private JTextField discountField;
	private JLabel categorylbl;
	private JComboBox<String> categoryBox;

	static Product scannedProduct = new Product();

	/**
	 * Create the frame.
	 */
	public AddProductWindow() {
		String prodID = ScanProductWindow.getUID();
		scannedProduct.setProdID(prodID);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(AddProductWindow.this, "Do you want to quit Scan Dash?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});
		setBounds(500, 200, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Add Product");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
		topPanel.setBounds(0, 0, 500, 60);
		contentPane.add(topPanel);
		topPanel.setLayout(null);

		JLabel managerLabel = new JLabel("PRODUCT");
		managerLabel.setForeground(Color.WHITE);
		managerLabel.setFont(new Font("Tahoma", Font.BOLD, 24)); // Bigger and bold font
		managerLabel.setBounds(20, 10, 200, 40); // Position at top left
		topPanel.add(managerLabel);

		JLabel productIDlbl = new JLabel("Product ID:");
		productIDlbl.setBounds(60, 90, 100, 13);
		contentPane.add(productIDlbl);

		JLabel productNamelbl = new JLabel("Product Name:");
		productNamelbl.setBounds(60, 130, 100, 13);
		contentPane.add(productNamelbl);

		JLabel pricelbl = new JLabel("Product Price:");
		pricelbl.setBounds(60, 170, 100, 13);
		contentPane.add(pricelbl);

		JLabel Rslbl = new JLabel("Rs");
		Rslbl.setBounds(365, 172, 100, 13);
		contentPane.add(Rslbl);

		JLabel quantitylbl = new JLabel("Quantity:");
		quantitylbl.setBounds(60, 210, 100, 13);
		contentPane.add(quantitylbl);

		JLabel discountlbl = new JLabel("Discount:");
		discountlbl.setBounds(60, 250, 100, 13);
		contentPane.add(discountlbl);

		JLabel percentlbl = new JLabel("%");
		percentlbl.setBounds(365, 252, 100, 13);
		contentPane.add(percentlbl);

		categorylbl = new JLabel("Category:");
		categorylbl.setBounds(60, 290, 100, 13);
		contentPane.add(categorylbl);

		IDField = new JTextField();
		IDField.setBounds(160, 90, 200, 19);
		contentPane.add(IDField);
		IDField.setColumns(10);
		IDField.setText(prodID);
		IDField.setEditable(false);

		nameField = new JTextField();
		nameField.setBounds(160, 130, 200, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);

		priceField = new JTextField();
		priceField.setBounds(160, 170, 200, 19);
		contentPane.add(priceField);
		priceField.setColumns(10);

		quantityField = new JTextField();
		quantityField.setBounds(160, 210, 200, 19);
		contentPane.add(quantityField);
		quantityField.setColumns(10);

		discountField = new JTextField();
		discountField.setBounds(160, 250, 200, 19);
		contentPane.add(discountField);
		discountField.setColumns(10);

		categoryBox = new JComboBox<>();
		categoryBox.setBounds(160, 290, 200, 21);
		contentPane.add(categoryBox);

		// Add categories to the JComboBox
		categoryBox.addItem("Grocery");
		categoryBox.addItem("Electronics");
		categoryBox.addItem("Cosmetics");
		categoryBox.addItem("Appliances");

		// Add grocery subcategories
		categoryBox.addItem("Fresh Grocery");
		categoryBox.addItem("Packaged Product");

		// Add FreshGrocery subcategories
		categoryBox.addItem("Fresh Product");
		categoryBox.addItem("Bakery Items");

		JButton addProductbtn = new JButton("Add Product");
		addProductbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmptyFields()) {
					JOptionPane.showMessageDialog(null, "Please Fill in All Fields", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (!validateDetails()) {
					return;
				}

				// Create a new Product object
				setProductDetails(scannedProduct);

				boolean success = Manager.addProduct(scannedProduct);
				if (success) {
					JOptionPane.showMessageDialog(null, "Product added successfully", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					new ManageInventoryWindow().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to add product. Product may already exist.", "Error",
							JOptionPane.ERROR_MESSAGE);
					new ManageInventoryWindow().setVisible(true);
					dispose();
				}
			}

		});

		addProductbtn.setBounds(120, 340, 120, 21);
		addProductbtn.setBackground(new Color(0, 180, 0));
		addProductbtn.setForeground(Color.black);
		contentPane.add(addProductbtn);

		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageInventoryWindow().setVisible(true);
			}
		});
		cancelbtn.setBackground(Color.red);
		cancelbtn.setForeground(Color.black);
		cancelbtn.setBounds(210, 370, 85, 21);
		contentPane.add(cancelbtn);

		JButton addDescriptionbtn = new JButton("Add Description");
		addDescriptionbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmptyFields()) {
					JOptionPane.showMessageDialog(null, "Please Fill in All Fields", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (!validateDetails()) {
					return;
				}
				// Update the scannedProduct with the current field values before opening the
				// description window
				setProductDetails(scannedProduct);

				// Create the appropriate subclass instance and assign it to scannedProduct
				scannedProduct = ProductManagement.createProductByCategory(scannedProduct);

				openProductDescriptionWindow(scannedProduct.getCategory());
			}
		});
		addDescriptionbtn.setBounds(250, 340, 140, 21);
		addDescriptionbtn.setBackground(new Color(0, 180, 0));
		addDescriptionbtn.setForeground(Color.black);
		contentPane.add(addDescriptionbtn);
		
		setLocation(518, 232);
	}

	private Boolean isEmptyFields() {
		String prodID = IDField.getText();
		String name = nameField.getText();
		String price = priceField.getText();
		String quantity = quantityField.getText();
		String discount = discountField.getText();

		return prodID.isEmpty() || name.isEmpty() || price.isEmpty() || quantity.isEmpty() || discount.isEmpty();
	}

	private void openProductDescriptionWindow(String category) {
		JFrame descriptionWindow;
		switch (category) {
		case "Electronics":
			descriptionWindow = new AddElectronicsDescriptionWindow((ElectronicsProduct) scannedProduct);
			break;
		case "Grocery":
			descriptionWindow = new AddGroceryDescriptionWindow((Grocery) scannedProduct);
			break;
		case "Fresh Produce":
			descriptionWindow = new AddFreshProduceDescriptionWindow((FreshProduce) scannedProduct);
			break;
		case "Bakery Items":
			descriptionWindow = new AddBakeryItemsDescriptionWindow((BakeryItems) scannedProduct);
			break;
		case "Packaged Product":
			descriptionWindow = new AddPackagedGroceryDescriptionWindow((PackagedProduct) scannedProduct);
			break;
		case "Cosmetics":
			descriptionWindow = new AddCosmeticsDescriptionWindow((CosmeticsProduct) scannedProduct);
			break;
		case "Appliances":
			descriptionWindow = new AddApplianceDescriptionWindow((ApplianceProduct) scannedProduct);
			break;

		case "Fresh Grocery":
			descriptionWindow = new AddFreshGroceryDescriptionWindow((FreshGrocery) scannedProduct);
			break;
		default:
			return; // No additional window for other categories
		}
		descriptionWindow.setVisible(true);
	}

	public void setProductDetails(Product product) {
		product.setName(nameField.getText());
		product.setPrice(Double.parseDouble(priceField.getText()));
		product.setQuantity(Integer.parseInt(quantityField.getText()));
		product.setDiscount(Double.parseDouble(discountField.getText()));
		product.setCategory((String) categoryBox.getSelectedItem());
	}

	private boolean validateDetails() {
		try {
			double price = Double.parseDouble(priceField.getText());
			int quantity = Integer.parseInt(quantityField.getText());
			double discount = Double.parseDouble(discountField.getText());

			if (!isValidPrice(price)) {
				JOptionPane.showMessageDialog(null, "Invalid product price", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (!isValidQuantity(quantity)) {
				JOptionPane.showMessageDialog(null, "Invalid product quantity", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (!isValidDiscount(discount)) {
				JOptionPane.showMessageDialog(null, "Invalid product discount", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter valid numeric values for price, quantity, and discount",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	// Method to validate product price
	public static boolean isValidPrice(double price) {
		// Price should be a positive number
		return price > 0;
	}

	// Method to validate product quantity
	public static boolean isValidQuantity(int quantity) {
		// Quantity should be a non-negative integer
		return quantity > 0;
	}

	// Method to validate product discount
	public static boolean isValidDiscount(double discount) {
		// Discount should be between 0 and 100 (percentage)
		return discount >= 0 && discount <= 100;
	}

}
