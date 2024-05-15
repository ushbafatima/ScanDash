import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JProgressBar;

public class Progress extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JProgressBar progressBar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int x;
					Progress frame = new Progress();
					frame.setVisible(true);
					try {
					for(x=0;x<=100;x++) {
							Progress.progressBar.setValue(x);
							Thread.sleep(30);
							try {
							
								if (x==100) {
									Thread.sleep(1000);
									frame.setVisible(false);
									
				        	        new FirstWindow().setVisible(true);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} 
						}
					catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				
	}

	/**
	 * Create the frame.
	 */
	public Progress() {
		setUndecorated(true);
		
		setBounds(500, 200, 500, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.GREEN);
		ImageIcon icon=new ImageIcon(this.getClass().getResource("/Shopping cart.gif"));
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(52, 169, 408, 92);
		contentPane.add(lblNewLabel);
		
		JLabel Scan = new JLabel("Scan");
		Scan.setForeground(new Color(0, 0, 160));
		Scan.setFont(new Font("Agency FB", Font.BOLD | Font.ITALIC, 30));
		Scan.setHorizontalAlignment(SwingConstants.CENTER);
		Scan.setBounds(172, 100, 86, 36);
		contentPane.add(Scan);
		
		JLabel Dash = new JLabel("Dash");
		Dash.setForeground(new Color(255, 165, 0));
		Dash.setFont(new Font("Arial Black", Font.BOLD, 25));
		Dash.setHorizontalAlignment(SwingConstants.CENTER);
		Dash.setBounds(241, 102, 86, 36);
		contentPane.add(Dash);
		JLabel Title = new JLabel("Smart Cart System");
		Title.setVerticalAlignment(SwingConstants.TOP);
		Title.setFont(new Font("Adobe Ming Std L", Font.BOLD, 16));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(159, 146, 199, 25);
		contentPane.add(Title);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(102, 153, 204));
		progressBar.setBounds(52, 304, 397, 25);
		contentPane.add(progressBar);
		
	}
}
