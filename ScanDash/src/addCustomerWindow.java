
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class addCustomerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	 * @throws SQLException 
	 */
	public addCustomerWindow() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel usernamelbl = new JLabel("Username");
		usernamelbl.setBounds(53, 68, 45, 13);
		contentPane.add(usernamelbl);

		JLabel cardNumberlbl = new JLabel("Card Number");
		cardNumberlbl.setBounds(53, 136, 45, 13);
		contentPane.add(cardNumberlbl);

		usernameField = new JTextField();
		usernameField.setBounds(141, 65, 96, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		// Create a JComboBox

		// Populate the JComboBox with available card numbers
		JComboBox cardNumbersBox = new JComboBox();
		cardNumbersBox.setBounds(141, 132, 106, 21);
		contentPane.add(cardNumbersBox);
		String[] availableCards;
		
			availableCards = UserManagement.getAvailableCards();
			for (String card : availableCards) {
				cardNumbersBox.addItem(card);
			}
		

		JButton addCustomerbtn = new JButton("Add Customer");
		addCustomerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieving the user information from the text fields
				if (availableCards == null || availableCards.length == 0) {
					JOptionPane.showMessageDialog(contentPane, "No cards available", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String username = usernameField.getText();
				String cardNumber = (String) cardNumbersBox.getSelectedItem();

				username = username.replaceAll("\\s", "");

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
				if (UserManagement.addCustomerToDB(customer)) {
					JOptionPane.showMessageDialog(rootPane, "Record Saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
					clearFields();
				} else
					// Error message if the student already exists based in the Student ID
					JOptionPane.showMessageDialog(null, "Record Already Exists", "Error", JOptionPane.ERROR_MESSAGE);

			}

		});
		addCustomerbtn.setBounds(48, 209, 85, 21);
		contentPane.add(addCustomerbtn);

		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelbtn.setBounds(222, 209, 85, 21);
		contentPane.add(cancelbtn);

	}

	public Boolean isEmptyFields() {
		String username = usernameField.getText();
		if (username.equals(""))
			return true;
		return false;

	}

	public void clearFields() {
		usernameField.setText("");
	}
}
