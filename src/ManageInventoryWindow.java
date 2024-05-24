import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ManageInventoryWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Change the theme to Flat Dark
			UIManager.setLookAndFeel(new FlatDarkLaf());
			// Or change to other LaF themes accordingly
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageInventoryWindow frame = new ManageInventoryWindow();
					System.out.println("Hi From Manage Inventory");
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
	public ManageInventoryWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(ManageInventoryWindow.this,
						"Do you want to quit Scan Dash?", "Confirm Exit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Inventory Management");
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

		JButton addProductbtn = new JButton("Add Product");
		addProductbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ScanProductWindow("Add").setVisible(true);
				dispose();
			}
		});
		addProductbtn.setForeground(Color.white);
		addProductbtn.setBounds(145, 100, 180, 40);
		addProductbtn.setBackground(new Color(0, 0, 100));
		addProductbtn.setFocusPainted(false);
		contentPane.add(addProductbtn);

		JButton removeProductbtn = new JButton("Remove Product");
		removeProductbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ScanProductWindow("Remove").setVisible(true);
				dispose();
			}
		});
		removeProductbtn.setForeground(Color.white);
		removeProductbtn.setBackground(new Color(0, 0, 100));
		removeProductbtn.setBounds(145, 170, 180, 40);
		removeProductbtn.setFocusPainted(false);
		contentPane.add(removeProductbtn);

		JButton updateDetailsbtn = new JButton("Update Product Details");
		updateDetailsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ScanProductWindow("Update").setVisible(true);
				dispose();
			}
		});
		updateDetailsbtn.setForeground(Color.white);
		updateDetailsbtn.setBounds(145, 240, 180, 40);
		updateDetailsbtn.setBackground(new Color(0, 0, 100));
		updateDetailsbtn.setFocusPainted(false);
		contentPane.add(updateDetailsbtn);

		JButton closebtn = new JButton("Close");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerTasksWindow frame = new ManagerTasksWindow();
				frame.setVisible(true);
				dispose();
			}
		});
		closebtn.setBounds(190, 300, 85, 21);
		closebtn.setBackground(Color.red); // Bootstrap danger color
		closebtn.setForeground(Color.WHITE);

		contentPane.add(closebtn);
		setLocation(518, 232);
	}

}