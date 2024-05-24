import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewPackagedGroceryDescriptionWindow extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField expiryDateDD;
    private JTextField expiryDateMM;
    private JTextField expiryDateYY;
    private JTextField manufactureDateDD;
    private JTextField manufactureDateMM;
    private JTextField manufactureDateYY;
    private JLabel brandlbl;
    private JTextField brandField;
    JButton addDescriptionbtn;

    public static void main(String[] args) {
    try {
           UIManager.setLookAndFeel( new FlatDarkLaf() );
            } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
            }     
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewPackagedGroceryDescriptionWindow frame = new ViewPackagedGroceryDescriptionWindow(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewPackagedGroceryDescriptionWindow(PackagedProduct PackagedGroceryItem) {
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(ViewPackagedGroceryDescriptionWindow.this, "Do you want to quit Scan Dash?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setTitle("View Packaged Grocery Product");
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
        setIconImage(icon.getImage());
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
        topPanel.setBounds(0, 0, 450, 40);
        contentPane.add(topPanel);
        topPanel.setLayout(null);
         
        JLabel managerLabel = new JLabel("Packaged Groceries");
        managerLabel.setForeground(Color.WHITE);
        managerLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); // Bigger and bold font
        managerLabel.setBounds(20, 0, 200, 40); // Position at top left
        topPanel.add(managerLabel);
        
        JLabel expiryDatelbl = new JLabel("Expiry Date:");
        expiryDatelbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
        expiryDatelbl.setBounds(90, 70, 80, 20);
        contentPane.add(expiryDatelbl);

        JLabel manufactureDatelbl = new JLabel("Manufacture Date:");
        manufactureDatelbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
        manufactureDatelbl.setBounds(90, 110, 100, 20);
        contentPane.add(manufactureDatelbl);

        expiryDateDD = new JTextField();
        expiryDateDD.setBounds(200, 70, 30, 19);
        contentPane.add(expiryDateDD);
        expiryDateDD.setColumns(10);
        expiryDateDD.setEditable(false);


        JButton closebtn = new JButton("Close");
        closebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        closebtn.setBounds(205, 180, 85, 21);
        closebtn.setBackground(Color.red);
        closebtn.setForeground(Color.black);
        contentPane.add(closebtn);

        expiryDateMM = new JTextField();
        expiryDateMM.setColumns(10);
        expiryDateMM.setBounds(240, 70, 30, 19);
        contentPane.add(expiryDateMM);
        expiryDateMM.setEditable(false);

        expiryDateYY = new JTextField();
        expiryDateYY.setColumns(10);
        expiryDateYY.setBounds(280, 70, 45, 19);
        contentPane.add(expiryDateYY);
        expiryDateYY.setEditable(false);

        manufactureDateDD = new JTextField();
        manufactureDateDD.setColumns(10);
        manufactureDateDD.setBounds(200, 110, 30, 19);
        contentPane.add(manufactureDateDD);
        manufactureDateDD.setEditable(false);

        manufactureDateMM = new JTextField();
        manufactureDateMM.setColumns(10);
        manufactureDateMM.setBounds(240, 110, 30, 19);
        contentPane.add(manufactureDateMM);
        manufactureDateMM.setEditable(false);

        manufactureDateYY = new JTextField();
        manufactureDateYY.setColumns(10);
        manufactureDateYY.setBounds(280, 110, 45, 19);
        contentPane.add(manufactureDateYY);
        manufactureDateYY.setEditable(false);
        
        brandlbl = new JLabel("Brand");
        brandlbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
        brandlbl.setBounds(90, 150, 100, 20);
        contentPane.add(brandlbl);
        
        brandField = new JTextField();
        brandField.setBounds(200, 150, 96, 19);
        contentPane.add(brandField);
        contentPane.add(brandField);
        brandField.setColumns(10);
        brandField.setEditable(false);
        
        setLocation(518, 232);
        setDescription(PackagedGroceryItem);
    }

    public void setDescription(PackagedProduct product) {
        // Set expiry date text
        String expiryDate = product.getExpiryDate();
        System.out.println(expiryDate);
        System.out.println(product.getProdID());
        String[] expiryDateParts = expiryDate.split("-");
        expiryDateDD.setText(expiryDateParts[0]);
        expiryDateMM.setText(expiryDateParts[1]);
        expiryDateYY.setText(expiryDateParts[2]);

        // Set manufacture date text
        String manufactureDate = product.getManufactureDate();
        String[] manufactureDateParts = manufactureDate.split("-");
        manufactureDateDD.setText(manufactureDateParts[0]);
        manufactureDateMM.setText(manufactureDateParts[1]);
        manufactureDateYY.setText(manufactureDateParts[2]);

        // Set weight text
        brandField.setText(product.getBrand());
    }
}
