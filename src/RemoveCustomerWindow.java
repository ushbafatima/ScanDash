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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class RemoveCustomerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(RemoveCustomerWindow.this,
						"Do you want to quit Scan Dash?", "Confirm Exit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});

		setBounds(500, 200, 500, 450);
		setTitle("Remove Customer");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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

		JLabel usernamelbl = new JLabel("Username:");
		usernamelbl.setForeground(Color.white);
		usernamelbl.setBounds(120, 130, 80, 13);
		usernamelbl.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(usernamelbl);

		usernameField = new JTextField();
		usernameField.setBounds(200, 130, 140, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JButton removeUserbtn = new JButton("Remove User");
		removeUserbtn.setBounds(340, 14, 130, 25);
		removeUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernameField.getText();
				username=username.replaceAll("\\s", "");

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
				Customer customer = new Customer();
				customer.setUsername(username);
				if (Manager.removeCustomer(customer)) {
					JOptionPane.showMessageDialog(rootPane, "Customer Removed", "Removed",
							JOptionPane.INFORMATION_MESSAGE);
					usernameField.setText("");
					return;
				} else {
					// Error message if the student already exists based in the Student ID
					JOptionPane.showMessageDialog(null, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		removeUserbtn.setBounds(200, 179, 140, 30);
		contentPane.add(removeUserbtn);

		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.setBounds(200, 230, 140, 30);
		cancelbtn.setBackground(Color.red);
		cancelbtn.setForeground(Color.black);
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerTasksWindow frame = new ManagerTasksWindow();
				frame.setVisible(true);
				dispose();

			}
		});
		contentPane.add(cancelbtn);
		setLocation(518, 232);
		

	}
}
