import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class ScanProductWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String productUID;
	private String action;

	public ScanProductWindow(String action) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(ScanProductWindow.this,
						"Do you want to quit Scan Dash?", "Confirm Exit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});
		
		this.action = action;
		System.out.println("Scanning Product...");
		setBounds(500, 200, 500, 450);
		setTitle("Scan Product");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
		topPanel.setBounds(0, 0, 500, 60);
		contentPane.add(topPanel);
		topPanel.setLayout(null);

		JLabel scanLabel = new JLabel("SCAN PRODUCT");
		scanLabel.setForeground(Color.WHITE);
		scanLabel.setFont(new Font("Tahoma", Font.BOLD, 24)); // Bigger and bold font
		scanLabel.setBounds(20, 10, 200, 40); // Position at top left
		topPanel.add(scanLabel);

		JLabel ScanProductIcon = new JLabel("");
		ScanProductIcon.setHorizontalAlignment(SwingConstants.CENTER);
		ScanProductIcon.setBackground(Color.GREEN);
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/ScanProduct.gif"));
		ScanProductIcon.setIcon(icon1);
		ScanProductIcon.setBounds(116, 116, 268, 200);
		contentPane.add(ScanProductIcon);
		
		JButton closebtn = new JButton("Close");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (action=="Add"||action=="Update"||action=="Remove")
					new ManageInventoryWindow().setVisible(true);
				else
					new CustomerShoppingWindow().setVisible(true);
				dispose();
				
			}
		});
		closebtn.setForeground(Color.WHITE);
		closebtn.setBackground(Color.RED);
		closebtn.setBounds(205, 338, 85, 21);
		contentPane.add(closebtn);

		simulateProductScan();

	}

	/**
	 * Simulate the product scanning process.
	 */
	private void simulateProductScan() {
		try {
			// Change the theme to Flat Dark
			UIManager.setLookAndFeel(new FlatDarkLaf());
			// Or change to other LaF themes accordingly
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		System.out.println("Hi From Simulate Scan");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				scanProduct();
			}
		}, 1000); // Simulate scanning process after 2 seconds
	}

	/**
	 * Method to handle the scanning process. This should be replaced with actual
	 * scanning logic.
	 */
	private void scanProduct() {
		while (true) {
		productUID = Arduino.scanProduct();
		if (!isValidUID(productUID)) {
			JOptionPane.showMessageDialog(null, "Product not Scanned Properly", "Scan Again", JOptionPane.INFORMATION_MESSAGE);
            
		}else {
			break;
		}
		}
		
		System.out.println("Product scanned: " + productUID);
		dispose(); // Close the ScanProductWindow after scanning
		handleAction();

	}

	public static String getUID() {
		return productUID;
	}

	private void handleAction() {
		// Based on the action, open the respective window
		String prodID = ScanProductWindow.getUID();
		Product scannedProduct = ProductManagement.findProductinDB(prodID);
		dispose();
		switch (action) {
		case "Add":
			if (scannedProduct != null) {
				JOptionPane.showMessageDialog(null, "Product Already Exists", "Info", JOptionPane.INFORMATION_MESSAGE);
				new ManageInventoryWindow().setVisible(true);
				return;
			}
			new AddProductWindow().setVisible(true);
			break;
		case "Remove":
			if (scannedProduct == null) {
				JOptionPane.showMessageDialog(null, "Product Does not Exist", "Info", JOptionPane.INFORMATION_MESSAGE);
				new ManageInventoryWindow().setVisible(true);
				return;
			}
			new RemoveProductWindow().setVisible(true);
			break;
		case "Update":
			if (scannedProduct == null) {
				JOptionPane.showMessageDialog(null, "Product Does not Exist", "Info", JOptionPane.INFORMATION_MESSAGE);
				new ManageInventoryWindow().setVisible(true);
				return;
			}
			new UpdateDetailsWindow().setVisible(true);
			break;
		case "Customer":
			if (scannedProduct == null) {
				JOptionPane.showMessageDialog(null, "Product Does not Exist", "Info", JOptionPane.INFORMATION_MESSAGE);
				new CustomerShoppingWindow().setVisible(true);
				return;
			}
			new ShopProductsWindow().setVisible(true);
			
		
		}
	}

	public Boolean isValidUID(String UID) {
		if (UID.length() < 11) {
			return false;
		}
		return true;
	}
}
