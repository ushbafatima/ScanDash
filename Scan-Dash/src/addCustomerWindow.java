
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class addCustomerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	JComboBox<String> cardNumbersBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCustomerWindow frame = new addCustomerWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public addCustomerWindow() throws SQLException {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(addCustomerWindow.this, "Do you want to quit Scan Dash?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});
		setBounds(500, 200, 500, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Add Customer");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
		topPanel.setBounds(0, 0, 500, 60);
		contentPane.add(topPanel);
		topPanel.setLayout(null);

		JLabel managerLabel = new JLabel("MANAGER");
		managerLabel.setForeground(Color.WHITE);
		managerLabel.setFont(new Font("Tahoma", Font.BOLD, 24)); // Bigger and bold font
		managerLabel.setBounds(20, 10, 200, 40); // Position at top left
		topPanel.add(managerLabel);

		JLabel usernamelbl = new JLabel("Username");
		usernamelbl.setForeground(Color.white);
		usernamelbl.setBounds(100, 130, 80, 13);
		contentPane.add(usernamelbl);

		JLabel cardNumberlbl = new JLabel("Card Number");
		cardNumberlbl.setForeground(Color.white);
		cardNumberlbl.setBounds(100, 180, 80, 13);
		contentPane.add(cardNumberlbl);

		usernameField = new JTextField();
		usernameField.setBounds(200, 130, 180, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		// Create a JComboBox
		cardNumbersBox = new JComboBox<>();
		cardNumbersBox.setBounds(200, 180, 180, 19);
		contentPane.add(cardNumbersBox);
		String[] availableCards = {}; // Initialize with empty array or fetch available cards here

		updateCardNumbersBox();

		JButton addCustomerbtn = new JButton("Add Customer");
		addCustomerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieving the user information from the text fields
				String username = usernameField.getText();
				String cardNumber = (String) cardNumbersBox.getSelectedItem();

				/*********************** ERROR HANDLING **************************/

				// Checking if all fields have been filled
				if (isEmptyFields()) {
					JOptionPane.showMessageDialog(null, "Please Fill in All Fields", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// Validate ID (it should be numeric)
				if (!UserManagement.isValidUsername(username)) {
					JOptionPane.showMessageDialog(null, "Invalid Username.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				/*********************** ERROR HANDLING **************************/

				// Creating an Object of the User as per the provided details
				Customer customer = new Customer(username, cardNumber);

				// Adding user to the database
				if (Manager.addCustomer(customer)) {
					JOptionPane.showMessageDialog(rootPane, "Record Saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
					try {
						updateCardNumbersBox();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clearFields();
				} else
					// Error message if the student already exists based in the Student ID
					JOptionPane.showMessageDialog(null, "Username not available", "Error", JOptionPane.ERROR_MESSAGE);
				clearFields();
			}

		});
		addCustomerbtn.setBounds(180, 220, 120, 21);
		addCustomerbtn.setBackground(new Color(0, 180, 0));
		addCustomerbtn.setForeground(Color.BLACK);
		contentPane.add(addCustomerbtn);

		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ManagerTasksWindow frame = new ManagerTasksWindow();
                 frame.setVisible(true);
				dispose();
			}
		});
		cancelbtn.setBounds(320, 220, 85, 21);
		cancelbtn.setBackground(Color.red);
		cancelbtn.setForeground(Color.black);
		contentPane.add(cancelbtn);
		
		setLocation(518, 232);

	}

	private Boolean isEmptyFields() {
		String username = usernameField.getText();
		username=username.replaceAll("\\s", "");
		if (username.equals(""))
			return true;
		return false;

	}

	private void clearFields() {
		usernameField.setText("");
	}
	
	private void updateCardNumbersBox() throws SQLException {
	    cardNumbersBox.removeAllItems(); // Clear existing items
	    String[] availableCards = UserManagement.getAvailableCards();
	    for (String card : availableCards) {
	        cardNumbersBox.addItem(card);
	    }
	    
	    // Show message if no cards are available
	    if (availableCards == null || availableCards.length == 0) {
	        JOptionPane.showMessageDialog(contentPane, "No cards available", "Info", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

}
