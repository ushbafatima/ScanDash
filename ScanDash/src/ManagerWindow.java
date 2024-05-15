import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerWindow frame = new ManagerWindow();
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
	public ManagerWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Manager Login");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(71, 70, 45, 13);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(71, 132, 45, 13);
		contentPane.add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(164, 78, 180, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(164, 129, 180, 19);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e) {
		        // Get the entered username and password
		        String username = usernameField.getText();
		        char[] passwordChars = passwordField.getPassword();
		        String password = new String(passwordChars);
		        if (isEmptyFields()) {
		        	 JOptionPane.showMessageDialog(null, "Please Fill All Fields", "Info", JOptionPane.INFORMATION_MESSAGE);
			         return;   
		        }
		        	
		        // Perform authentication or validation here
		        // Check if the entered username and password match any of the predefined credentials
		        User user = UserManagement.findUser(username);
		        if (user == null) {
		            JOptionPane.showMessageDialog(null, "User Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		            clearFields();
		            return;
		        }

		        if (UserManagement.authenticateUser(username, password)) {
		            setVisible(false);
		        } else {
		            // If the credentials don't match, display an error message
		            JOptionPane.showMessageDialog(null, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
		            passwordField.setText("");
		        }
		    }
		});
		loginButton.setBounds(78, 256, 85, 21);
		contentPane.add(loginButton);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeButton.setBounds(289, 256, 85, 21);
		contentPane.add(closeButton);
		
		
	}
	public Boolean isEmptyFields() {
		String username=usernameField.getText();
		char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
		if (username.equals("")||password.equals(""))
			return true;
		return false;
		
	}
	public void clearFields() {
		usernameField.setText("");
		passwordField.setText("");
	}
}
