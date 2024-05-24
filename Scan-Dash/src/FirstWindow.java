import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class FirstWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
					FirstWindow frame = new FirstWindow();
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
	public FirstWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(FirstWindow.this, "Do you want to quit Scan Dash?",
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
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setContentPane(contentPane);

		setTitle("Scan Dash");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());
		contentPane.setLayout(null);

		JLabel titlelbl = new JLabel("");
		titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlelbl.setBackground(Color.GREEN);
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/SCAN2_1.gif"));
		titlelbl.setIcon(icon1);
		titlelbl.setBounds(-50, -100, 600, 300);
		contentPane.add(titlelbl);

		JLabel Title = new JLabel("Smart Cart System");
		Title.setVerticalAlignment(SwingConstants.TOP);
		Title.setFont(new Font("Adobe Ming Std L", Font.BOLD, 16));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(132, 150, 199, 25);
		Title.setForeground(Color.white);
		contentPane.add(Title);

		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManagerWindow().setVisible(true);
			}
		});
		btnManager.setForeground(Color.white);
		btnManager.setBounds(180, 220, 125, 25);
		btnManager.setBackground(new Color(0, 0, 100));
		btnManager.setFocusPainted(false);
		contentPane.add(btnManager);

		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartManagement.clearCart();
				dispose();
				new CustomerWindow().setVisible(true);
			}
		});
		btnCustomer.setForeground(Color.white);
		btnCustomer.setBounds(180, 260, 125, 25);
		btnCustomer.setBackground(new Color(0, 0, 100));
		btnCustomer.setFocusPainted(false);
		contentPane.add(btnCustomer);

		JLabel Text = new JLabel("Access Smart Cart as:");
		Text.setHorizontalAlignment(SwingConstants.CENTER);
		Text.setBounds(102, 184, 277, 25);
		Text.setForeground(Color.white);
		contentPane.add(Text);
		
		setLocation(518, 232);
	}
}