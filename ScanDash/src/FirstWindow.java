import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

public class FirstWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		
		setContentPane(contentPane);
		
		setTitle("Scan Dash");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());
		contentPane.setLayout(null);
		JLabel Scan = new JLabel("Scan");
		Scan.setForeground(new Color(0, 0, 160));
		Scan.setFont(new Font("Agency FB", Font.BOLD | Font.ITALIC, 30));
		Scan.setHorizontalAlignment(SwingConstants.CENTER);
		Scan.setBounds(149, 44, 86, 36);
		contentPane.add(Scan);
		
		JLabel Dash = new JLabel("Dash");
		Dash.setForeground(new Color(255, 165, 0));
		Dash.setFont(new Font("Arial Black", Font.BOLD, 25));
		Dash.setHorizontalAlignment(SwingConstants.CENTER);
		Dash.setBounds(221, 46, 86, 36);
		contentPane.add(Dash);
		JLabel Title = new JLabel("Smart Cart System");
		Title.setVerticalAlignment(SwingConstants.TOP);
		Title.setFont(new Font("Adobe Ming Std L", Font.BOLD, 16));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(132, 90, 199, 25);
		contentPane.add(Title);
		
		JButton btnManager = new JButton("Manager");
		btnManager.setBounds(102, 231, 119, 25);
		contentPane.add(btnManager);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setBounds(254, 231, 125, 25);
		contentPane.add(btnCustomer);
		
		JLabel Text = new JLabel("Access Smart Cart as:");
		Text.setHorizontalAlignment(SwingConstants.CENTER);
		Text.setBounds(102, 184, 277, 25);
		contentPane.add(Text);
	}
}
