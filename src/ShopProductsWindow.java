import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class ShopProductsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField productIDField;
	private JTextField productNameField;
	private JTextField productPriceField;
	private JTextField discountField;
	private JTextField categoryField;
	private JTextField quantityField;
	
static Product categoryProduct=new Product();
    static Product scannedProduct = new Product();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        try {
             UIManager.setLookAndFeel( new FlatDarkLaf() );
            } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
            }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopProductsWindow frame = new ShopProductsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopProductsWindow() {
		String prodID = ScanProductWindow.getUID();
		System.out.println(prodID);
		scannedProduct=ProductManagement.findProductinDB(prodID);
		System.out.println(prodID);
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(ShopProductsWindow.this,
						"Do you want to quit Scan Dash?", "Confirm Exit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});setBounds(100, 100, 450, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setTitle("Shop Product");
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
		
		productIDField = new JTextField();
		productIDField.setText((String) null);
		productIDField.setEditable(false);
		productIDField.setColumns(10);
		productIDField.setBounds(160, 90, 200, 19);
		contentPane.add(productIDField);
		
		JLabel productNamelbl = new JLabel("Product Name:");
		productNamelbl.setBounds(60, 130, 100, 13);
		contentPane.add(productNamelbl);
		
		productNameField = new JTextField();
		productNameField.setText("<dynamic>");
		productNameField.setEditable(false);
		productNameField.setColumns(10);
		productNameField.setBounds(160, 130, 200, 19);
		contentPane.add(productNameField);
		
		productPriceField = new JTextField();
		productPriceField.setText("0.0");
		productPriceField.setEditable(false);
		productPriceField.setColumns(10);
		productPriceField.setBounds(160, 170, 200, 19);
		contentPane.add(productPriceField);
		
		JLabel pricelbl = new JLabel("Product Price:");
		pricelbl.setBounds(60, 170, 100, 13);
		contentPane.add(pricelbl);
                
                JLabel Rslbl = new JLabel("Rs");
		Rslbl.setBounds(365, 172, 100, 13);
		contentPane.add(Rslbl);
		
		discountField = new JTextField();
		discountField.setText("0.0");
		discountField.setEditable(false);
		discountField.setColumns(10);
		discountField.setBounds(160, 210, 200, 19);
		contentPane.add(discountField);
		
		JLabel discountlbl = new JLabel("Discount:");
		discountlbl.setBounds(60, 210, 100, 13);
		contentPane.add(discountlbl);
                
                JLabel percentlbl = new JLabel("%");
		percentlbl.setBounds(365, 212, 100, 13);
		contentPane.add(percentlbl);
		
		categoryField = new JTextField();
		categoryField.setText("<dynamic>");
		categoryField.setEditable(false);
		categoryField.setColumns(10);
		categoryField.setBounds(160, 250, 200, 21);
		contentPane.add(categoryField);
		
		JLabel categorylbl = new JLabel("Category:");
		categorylbl.setBounds(60, 250, 100, 20);
		contentPane.add(categorylbl);
		
		JLabel quantitylbl = new JLabel("Quantity:");
		quantitylbl.setBounds(60, 290, 100, 20);
		contentPane.add(quantitylbl);
		
                quantityField = new JTextField();
                quantityField.setBounds(200, 292, 70, 19);
                contentPane.add(quantityField);
                quantityField.setColumns(10);
                
        String name = scannedProduct.getName();
        double price = scannedProduct.getPrice();
        double discount = scannedProduct.getDiscount();
        int quantity = scannedProduct.getQuantity();
        String category = scannedProduct.getCategory();
                
        // Set retrieved details to the corresponding fields
        productIDField.setText(prodID);
        productNameField.setText(name);
        productPriceField.setText(String.valueOf(price)); // Convert price to String
        quantityField.setText("0");
        discountField.setText(String.valueOf(discount)); // Convert discount to String
        categoryField.setText(category);
		
        JButton addProductbtn = new JButton("Add Product");
        addProductbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isValidQuantity()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid quantity.", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isQuantityAvailable()) {
                    JOptionPane.showMessageDialog(null, "Insufficient quantity available.\nQuantity in Store: "+scannedProduct.getQuantity(), "Quantity Unavailable", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the entered quantity
                int quantity = Integer.parseInt(quantityField.getText());
                
                // Create a new product instance with the same details as scannedProduct but with updated quantity
                Product productInCart = new Product();
                productInCart.setProdID(scannedProduct.getProdID());
                productInCart.setName(scannedProduct.getName());
                productInCart.setPrice(scannedProduct.getPrice());
                productInCart.setDiscount(scannedProduct.getDiscount());
                productInCart.setCategory(scannedProduct.getCategory());
                productInCart.setQuantity(quantity);

                // Add the product to the cart
                if (Customer.addProductToCart(productInCart)) {
                    
                	JOptionPane.showMessageDialog(null, "Product added to cart successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                	dispose();
                	new CustomerShoppingWindow().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add product to cart.", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                	new CustomerShoppingWindow().setVisible(true);
                }
            }
        });

		addProductbtn.setBounds(80, 340, 120, 21);
                addProductbtn.setBackground(new Color(0,180,0));
                addProductbtn.setForeground(Color.black);
		contentPane.add(addProductbtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerShoppingWindow().setVisible(true);
			}
		});
		cancelbtn.setBackground(Color.red);
                cancelbtn.setForeground(Color.black);
		cancelbtn.setBounds(270, 370, 85, 21);
		contentPane.add(cancelbtn);
		
		
		JButton minusbtn = new JButton("-");
	        minusbtn.setBounds(150, 290, 40, 21);
                minusbtn.setForeground(Color.white);
                minusbtn.setBackground(new Color(0, 0, 100));
	        contentPane.add(minusbtn);
	        minusbtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	int quantity = Integer.parseInt(quantityField.getText());
	            	if (quantity!=0)
	            	quantity--;
	            	quantityField.setText(Integer.toString(quantity));
	            }
	        });

	        JButton plusbtn = new JButton("+");
	        plusbtn.setBounds(280, 290, 45, 21);
                plusbtn.setForeground(Color.white);
                plusbtn.setBackground(new Color(0, 0, 100));
	        contentPane.add(plusbtn);
	        plusbtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		int quantity = Integer.parseInt(quantityField.getText());
	        		quantity++;
	        		quantityField.setText(Integer.toString(quantity));
	        	}
	        });
	        
	        JButton viewDescriptionbtn = new JButton("View Description");
                viewDescriptionbtn.setBackground(Color.LIGHT_GRAY);
                viewDescriptionbtn.setBounds(220, 340, 180, 21);
                viewDescriptionbtn.setForeground(Color.black);
	        viewDescriptionbtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		
	        		categoryProduct.setProdID(scannedProduct.getProdID());
	        		categoryProduct.setCategory(scannedProduct.getCategory());
	        		categoryProduct.setName(scannedProduct.getName());
	        		categoryProduct.setQuantity(scannedProduct.getQuantity());
	        		categoryProduct.setDiscount(scannedProduct.getDiscount());
	        		categoryProduct.setPrice(scannedProduct.getPrice());
	        		
	        		categoryProduct= ProductManagement.createProductByCategory(categoryProduct);
	        			        		
	        		categoryProduct= ProductManagement.findProductinCategoryDB(categoryProduct);
	        		if (categoryProduct == null) {
	                    JOptionPane.showMessageDialog(null, "Product description not available.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
	                    return;
	                }
	        		
					openViewProductDescriptionWindow(categoryProduct.getCategory());
	        	}
	        });
	        contentPane.add(viewDescriptionbtn);
	        
	        JButton removeProductbtn = new JButton("Remove Product");
                removeProductbtn.setBackground(Color.red);
                removeProductbtn.setForeground(Color.black);
		removeProductbtn.setBounds(80, 370, 140, 21);
	        removeProductbtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		 if (!CartManagement.isProductInCart(scannedProduct)) {
	        		        JOptionPane.showMessageDialog(null, "Product not found in cart.", "Error", JOptionPane.ERROR_MESSAGE);
	        		        return;
	        		    }
	        		    
	        		    // Get the current quantity of the product in the cart
	        		    int currentQuantityInCart = CartManagement.getCurrentQuantityInCart(scannedProduct);
	        		    int quantityToRemove = Integer.parseInt(quantityField.getText());
	        		    // Ensure that the quantity to remove is not greater than the quantity in the cart
	        		    if (quantity==0) {
	        		    	JOptionPane.showMessageDialog(null, "Product Out of Stock!", "Oops", JOptionPane.INFORMATION_MESSAGE);
	        		        return;
	        		    }
	        		    if (quantityToRemove > currentQuantityInCart) {
	        		        JOptionPane.showMessageDialog(null, "Quantity to remove exceeds quantity in cart.\nAvailable quantity in cart: " + currentQuantityInCart, "Error", JOptionPane.ERROR_MESSAGE);
	        		        return;
	        		    }
	        		   
	        		    // Proceed with removing the product from the cart
	        		 // Create a new Product instance representing the product to remove from the cart
	        		    Product productToRemove = new Product();
	        		    productToRemove.setProdID(scannedProduct.getProdID());
	        		    productToRemove.setName(scannedProduct.getName());
	        		    productToRemove.setPrice(scannedProduct.getPrice());
	        		    productToRemove.setDiscount(scannedProduct.getDiscount());
	        		    productToRemove.setCategory(scannedProduct.getCategory());
	        		    productToRemove.setQuantity(quantityToRemove);

	        		    // Attempt to remove the product from the cart
	        		    boolean removedSuccessfully = Customer.removeProductFromCart(productToRemove);
	        		    if (removedSuccessfully) {
	        		        JOptionPane.showMessageDialog(null, "Product removed from cart successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	        		    } else {
	        		        JOptionPane.showMessageDialog(null, "Failed to remove product from cart.", "Error", JOptionPane.ERROR_MESSAGE);
	        		    }
	        	}
	        });
                setLocation(518, 232);
	        contentPane.add(removeProductbtn);
	        
	        setLocation(518, 232);
	    }

	    public Boolean isQuantityAvailable() {
	    	int setQuantity=Integer.parseInt(quantityField.getText());
	    	int availableQuantity=scannedProduct.getQuantity();
	    	
	    	if (setQuantity>availableQuantity)
	    		return false;
	    	return true;
	    }
	    public boolean isValidQuantity() {
	        try {
	            int quantity = Integer.parseInt(quantityField.getText());
	            return quantity > 0;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    
	    private void openViewProductDescriptionWindow(String category) {
			JFrame descriptionWindow;
			switch (category) {
			case "Electronics":
				descriptionWindow = new ViewElectronicsDescriptionWindow((ElectronicsProduct) categoryProduct);
				break;
			case "Grocery":
				descriptionWindow = new ViewGroceryDescriptionWindow((Grocery) categoryProduct);
				break;
			case "Fresh Produce":
				descriptionWindow = new ViewFreshProduceDescriptionWindow((FreshProduce) categoryProduct);
				break;
			case "Bakery Items":
				descriptionWindow = new ViewBakeryItemsDescriptionWindow((BakeryItems) categoryProduct);
				break;
			case "Packaged Product":
				descriptionWindow = new ViewPackagedGroceryDescriptionWindow((PackagedProduct) categoryProduct);
				break;
			case "Cosmetics":
				descriptionWindow = new ViewCosmeticsDescriptionWindow((CosmeticsProduct) categoryProduct);
				break;
			case "Appliances":
				descriptionWindow = new ViewApplianceDescriptionWindow((ApplianceProduct) categoryProduct);
				break;
				
			case "Fresh Grocery":
				descriptionWindow = new ViewFreshGroceryDescriptionWindow((FreshGrocery) categoryProduct);
				break;
			default:
				return; // No additional window for other categories
			}
			descriptionWindow.setVisible(true);
		}

}
