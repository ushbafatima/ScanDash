import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class CustomerBillWindow extends JFrame {

    private JPanel contentPane;

    public CustomerBillWindow() {
    	
    	double totalBill = CartManagement.calculateBill();
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(CustomerBillWindow.this,
						"Do you want to quit Scan Dash?", "Confirm Exit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});setBounds(500, 200, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setTitle("Customer Bill");
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
        setIconImage(icon.getImage());

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Add the blue panel with the title label
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 0, 100));
        topPanel.setPreferredSize(new Dimension(400, 60));
        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(null);

        JLabel titleLabel = new JLabel("YOUR BILL");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setBounds(20, 10, 200, 40);
        topPanel.add(titleLabel);

        // Create the bill panel
        JPanel billPanel = new JPanel();
        billPanel.setLayout(new BorderLayout());
        contentPane.add(billPanel, BorderLayout.CENTER);

        JTextArea billText = new JTextArea();
        billText.setForeground(new Color(255, 255, 255));
        billText.setFont(new Font("Monospaced", Font.PLAIN, 16));
        billText.setText("Total Bill: PKR " + String.format("%.2f", totalBill));
        billText.setEditable(false);
        billPanel.add(billText, BorderLayout.CENTER);
        
        JButton payBillbtn = new JButton("Pay Bill");
        payBillbtn.setForeground(SystemColor.desktop);
        payBillbtn.setBackground(SystemColor.activeCaption);
        payBillbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (totalBill==0) {
        			JOptionPane.showMessageDialog(null, "Add items to cart before processing the bill.", "Oops",
							JOptionPane.INFORMATION_MESSAGE);
        			return;
        		}
        		
        		CartManagement.checkout();
        		new PayBillWindow().setVisible(true);
        		dispose();
        	}
        });
        payBillbtn.setPreferredSize(new Dimension(100, 21));
        billPanel.add(payBillbtn, BorderLayout.SOUTH);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton btnClose = new JButton("Close");
        btnClose.setForeground(SystemColor.desktop);
        btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ViewCartWindow().setVisible(true);
			}
        });
        
        btnClose.setBackground(new Color(255, 0, 0));
        btnClose.setPreferredSize(new Dimension(100, btnClose.getPreferredSize().height));
        btnClose.addActionListener(e -> dispose());
        buttonPanel.add(btnClose);
        

		setLocation(600, 232);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        EventQueue.invokeLater(() -> {
            try {
                CustomerBillWindow frame = new CustomerBillWindow(); // Example bill amount
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    
}
