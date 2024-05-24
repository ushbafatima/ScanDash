import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatDarkLaf;

public class PayBillWindow extends JFrame {
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PayBillWindow frame = new PayBillWindow();
                frame.setVisible(true);
            }
        });
    }

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static String cardUID;

    public PayBillWindow() {
    	
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(PayBillWindow.this,
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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Bill Payment");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
		topPanel.setBounds(0, 0, 500, 60);
		contentPane.add(topPanel);
		topPanel.setLayout(null);

		JLabel billLabel = new JLabel("BILL PAYMENT");
		billLabel.setForeground(Color.WHITE);
		billLabel.setFont(new Font("Tahoma", Font.BOLD, 24)); // Bigger and bold font
		billLabel.setBounds(20, 10, 200, 40); // Position at top left
		topPanel.add(billLabel);

        JLabel ScanProductIcon = new JLabel("");
        ScanProductIcon.setBounds(61, 112, 363, 182);
        ScanProductIcon.setHorizontalAlignment(SwingConstants.CENTER);
        ScanProductIcon.setBackground(new Color(255, 255, 255));
        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/CardScan.gif"));
        ScanProductIcon.setIcon(icon1);
        contentPane.add(ScanProductIcon);

        
        
        JLabel lblScanYourCredit = new JLabel("Scan Your Credit Card to Pay the Bill");
        lblScanYourCredit.setBackground(new Color(255, 255, 255));
        lblScanYourCredit.setHorizontalAlignment(SwingConstants.CENTER);
        lblScanYourCredit.setForeground(new Color(0, 0, 64)); // Change text color to blue
        lblScanYourCredit.setBounds(110, 70, 277, 25);
        contentPane.add(lblScanYourCredit);

        centerWindow() ;
        simulateCardScan();
    }

    private void simulateCardScan() {
		try {
			// Change the theme to Flat Dark
			UIManager.setLookAndFeel(new FlatDarkLaf());
			// Or change to other LaF themes accordingly
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		System.out.println("Hi From Simulate Scan");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				scanProduct();
			}
		}, 1000); // Simulate scanning process after 2 seconds
	}

    private void scanProduct() {
				cardUID = Arduino.scanProduct();
		
		
		
		System.out.println("Product scanned: " + cardUID);
		dispose();
		JOptionPane.showMessageDialog(null, "Your bill was paid successfully!", "Payment Success", JOptionPane.INFORMATION_MESSAGE);
		new FirstWindow().setVisible(true);
    }
    public static String getUID() {
        return cardUID;
    }
    
    private void centerWindow() {
	    // Get the size of the screen
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	    // Calculate the position of the window to be centered
	    int x = (screenSize.width - getWidth()) / 2;
	    int y = (screenSize.height - getHeight()) / 2;

	    // Set the window to be positioned at the calculated coordinates
	    setLocation(x, y);
	}
}
