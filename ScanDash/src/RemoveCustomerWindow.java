import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveCustomerWindow extends JFrame {

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
					RemoveCustomerWindow frame = new RemoveCustomerWindow();
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
	public RemoveCustomerWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernamelbl = new JLabel("Username:");
		usernamelbl.setHorizontalAlignment(SwingConstants.LEFT);
		usernamelbl.setBounds(49, 48, 45, 13);
		contentPane.add(usernamelbl);
		
		usernameField = new JTextField();
		usernameField.setBounds(155, 45, 96, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JButton removeUserbtn = new JButton("Remove User");
		removeUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				username = username.replaceAll("\\s", "");

				/*********************** ERROR HANDLING **************************/

				// Checking if all fields have been filled
				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill in All Fields", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// Validate ID (it should be numeric)
				if (!UserManagement.isValidUsername(username)) {
					JOptionPane.showMessageDialog(null, "Invalid Username.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			Customer customer=new Customer();
			customer.setUsername(username);
			if (UserManagement.removeCustomerFromDB(customer)) {
				JOptionPane.showMessageDialog(rootPane, "Customer Removed", "Removed", JOptionPane.INFORMATION_MESSAGE);
				usernameField.setText("");
				return;
			} else {
				// Error message if the student already exists based in the Student ID
				JOptionPane.showMessageDialog(null, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
			}
		});
		removeUserbtn.setBounds(87, 179, 85, 21);
		contentPane.add(removeUserbtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.setBounds(228, 179, 85, 21);
		contentPane.add(cancelbtn);
	}

}
