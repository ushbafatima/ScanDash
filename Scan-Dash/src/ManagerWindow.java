import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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

public class ManagerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

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
					ManagerWindow frame = new ManagerWindow();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
    	// Add a window listener to handle the window close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    ManagerWindow.this,
                    "Do you want to quit Scan Dash?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

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
        setTitle("Manager Login");
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
        setIconImage(icon.getImage());

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Add the blue panel with the MANAGER label
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

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setBounds(100, 130, 80, 13);
        contentPane.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setBounds(100, 180, 80, 13);
        contentPane.add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 130, 180, 19);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 180, 180, 19);
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
		        Manager foundManager = (Manager) UserManagement.findUser(username);
		        if (foundManager == null) {
		            JOptionPane.showMessageDialog(null, "User Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		            clearFields();
		            return;
		        }
		        Manager enteredManager=new Manager();
		        enteredManager.setUsername(username);
		        enteredManager.setPassword(password);

		        if (UserManagement.authenticateUser(enteredManager, foundManager)) {
		            setVisible(false);
		            new ManagerTasksWindow().setVisible(true);
		        } else {
		            // If the credentials don't match, display an error message
		            JOptionPane.showMessageDialog(null, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
		            passwordField.setText("");
		        }
		    }
		});
        loginButton.setBounds(200, 230, 85, 21);
        loginButton.setBackground(new Color(0,180,0));
        loginButton.setForeground(Color.black);
        contentPane.add(loginButton);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
        		new FirstWindow().setVisible(true);
				
			}
		});
        closeButton.setBounds(300, 230, 85, 21);
        closeButton.setBackground(Color.red);
        closeButton.setForeground(Color.black);
        contentPane.add(closeButton);
        
        setLocation(518, 232);
	}
	public Boolean isEmptyFields() {
		String username=usernameField.getText();
		username=username.replaceAll("\\s", "");
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
