import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import com.formdev.flatlaf.*;
import javax.swing.UIManager;

public class Progress extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JProgressBar progressBar;

	public Progress() {
    	

		setUndecorated(true);

		setBounds(500, 200, 500, 400);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titlelbl = new JLabel("");
		titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlelbl.setBackground(Color.GREEN);
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/SCAN1_1.gif"));
		titlelbl.setIcon(icon1);
		titlelbl.setBounds(-50, 0, 600, 300);
		contentPane.add(titlelbl);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(102, 153, 204));
		progressBar.setBounds(52, 304, 397, 25);
		contentPane.add(progressBar);
		setLocation(518, 232);

	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		int x;
		Progress frame = new Progress();
		frame.setVisible(true);
		try {
			for (x = 0; x <= 100; x++) {
				Progress.progressBar.setValue(x);
				Thread.sleep(30);
				try {
					if (x == 100) {
						Thread.sleep(1000);
						frame.setVisible(false);
						new FirstWindow().setVisible(true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
