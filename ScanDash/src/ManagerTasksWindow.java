import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ManagerTasksWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerTasksWindow frame = new ManagerTasksWindow();
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
	public ManagerTasksWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addUserbtn = new JButton("Add User");
		addUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new addCustomerWindow().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		addUserbtn.setBounds(37, 84, 105, 21);
		contentPane.add(addUserbtn);
		
		JButton manageInventorybtn = new JButton("Manage Inventory");
		manageInventorybtn.setBounds(152, 160, 148, 21);
		contentPane.add(manageInventorybtn);
		
		JButton removeUserbtn = new JButton("Remove User");
		removeUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveCustomerWindow().setVisible(true);
			}
		});
		removeUserbtn.setBounds(152, 84, 115, 21);
		contentPane.add(removeUserbtn);
		
		JButton displaySalesbtn = new JButton("Display Sales");
		displaySalesbtn.setBounds(290, 84, 115, 21);
		contentPane.add(displaySalesbtn);
	}
}
