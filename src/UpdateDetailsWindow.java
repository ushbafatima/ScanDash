import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UpdateDetailsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IDField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField quantityField;
	private JTextField discountField;
	private JLabel categorylbl;
	static Product foundProduct = new Product();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (foundProduct == null) {
						JOptionPane.showMessageDialog(null, "Product Not Found", "Info",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						UpdateDetailsWindow frame = new UpdateDetailsWindow();
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateDetailsWindow() {
		String prodID = ScanProductWindow.getUID();
		foundProduct=ProductManagement.findProductinDB(prodID);
		foundProduct.setProdID(prodID);
		if (foundProduct == null) {
			JOptionPane.showMessageDialog(null, "Product Not Found", "Info", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			return;
		}
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(UpdateDetailsWindow.this, "Do you want to quit Scan Dash?",
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
		setTitle("Update Product");
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

		JComboBox<String> categoryBox = new JComboBox<>();
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

		prodID = foundProduct.getProdID();
		String name = foundProduct.getName();
		double price = foundProduct.getPrice();
		double discount = foundProduct.getDiscount();
		int quantity = foundProduct.getQuantity();
		String category = foundProduct.getCategory();
		IDField.setText(prodID);
		nameField.setText(name);
		priceField.setText(String.valueOf(price)); // Convert price to String
		quantityField.setText(String.valueOf(quantity)); // Convert quantity to String
		discountField.setText(String.valueOf(discount)); // Convert discount to String
		categoryBox.setSelectedItem(category);

		JButton updatebtn = new JButton("Update Details");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmptyFields()) {
					JOptionPane.showMessageDialog(null, "Please Fill in All Fields", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// Retrieve field values
				String prodID = IDField.getText();
				String name = nameField.getText();
				String priceText = priceField.getText();
				String quantityText = quantityField.getText();
				String discountText = discountField.getText();
				String category = (String) categoryBox.getSelectedItem();

				double price;
				int quantity;
				double discount;

				try {
					price = Double.parseDouble(priceText);
					System.out.println(price);
					quantity = Integer.parseInt(quantityText);
					discount = Double.parseDouble(discountText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Please enter valid numeric values for price, quantity, and discount", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validate fields

				if (!isValidPrice(price)) {
					JOptionPane.showMessageDialog(null, "Invalid product price", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!isValidQuantity(quantity)) {
					JOptionPane.showMessageDialog(null, "Invalid product quantity", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!isValidDiscount(discount)) {
					JOptionPane.showMessageDialog(null, "Invalid product discount", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Create a new Product object
				Product product = new Product();
				product.setProdID(prodID);
				product.setName(name);
				product.setPrice(price);
				product.setQuantity(quantity);
				product.setDiscount(discount);
				product.setCategory(category);

				// Add product to the database
				boolean success = ProductManagement.updateProductInDB(product);

				if (success) {
					JOptionPane.showMessageDialog(null, "Product details updated successfully", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					new ManageInventoryWindow().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to update details. Product may already exist.", "Error",
							JOptionPane.ERROR_MESSAGE);
					new ManageInventoryWindow().setVisible(true);
					dispose();
				}
			}
		});

		updatebtn.setBounds(190, 340, 120, 21);
		updatebtn.setBackground(new Color(0, 180, 0));
		updatebtn.setForeground(Color.black);
		contentPane.add(updatebtn);

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

		setLocation(518, 232);

	}

	private Boolean isEmptyFields() {

		String price = priceField.getText();
		String quantity = quantityField.getText();
		String discount = discountField.getText();

		return price.isEmpty() || quantity.isEmpty() || discount.isEmpty();
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
