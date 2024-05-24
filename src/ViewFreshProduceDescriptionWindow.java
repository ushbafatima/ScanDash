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

public class ViewFreshProduceDescriptionWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField expiryDateDD;
	private JTextField expiryDateMM;
	private JTextField expiryDateYY;
	private JTextField manufactureDateDD;
	private JTextField manufactureDateMM;
	private JTextField manufactureDateYY;
	private JTextField weightField;
	private JLabel weightlbl;
	private JLabel organiclbl;
	private JTextField sourceField;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFreshProduceDescriptionWindow frame = new ViewFreshProduceDescriptionWindow(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewFreshProduceDescriptionWindow(FreshProduce freshProduceItem) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(ViewFreshProduceDescriptionWindow.this,
						"Do you want to quit Scan Dash?", "Confirm Exit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("View Fresh Grocery Product");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
		setIconImage(icon.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
		topPanel.setBounds(0, 0, 450, 40);
		contentPane.add(topPanel);
		topPanel.setLayout(null);

		JLabel managerLabel = new JLabel("Fresh Produce");
		managerLabel.setForeground(Color.WHITE);
		managerLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); // Bigger and bold font
		managerLabel.setBounds(20, 0, 200, 40); // Position at top left
		topPanel.add(managerLabel);

		JLabel expiryDatelbl = new JLabel("Expiry Date:");
		expiryDatelbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
		expiryDatelbl.setBounds(47, 60, 75, 13);
		contentPane.add(expiryDatelbl);

		JLabel manufactureDatelbl = new JLabel("Manufacture Date:");
		manufactureDatelbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
		manufactureDatelbl.setBounds(47, 95, 100, 13);
		contentPane.add(manufactureDatelbl);

		expiryDateDD = new JTextField();
		expiryDateDD.setBounds(176, 60, 27, 19);
		contentPane.add(expiryDateDD);
		expiryDateDD.setColumns(10);

		expiryDateMM = new JTextField();
		expiryDateMM.setColumns(10);
		expiryDateMM.setBounds(214, 60, 27, 19);
		contentPane.add(expiryDateMM);

		expiryDateYY = new JTextField();
		expiryDateYY.setColumns(10);
		expiryDateYY.setBounds(251, 60, 40, 19);
		contentPane.add(expiryDateYY);

		manufactureDateDD = new JTextField();
		manufactureDateDD.setColumns(10);
		manufactureDateDD.setBounds(176, 95, 27, 19);
		contentPane.add(manufactureDateDD);

		manufactureDateMM = new JTextField();
		manufactureDateMM.setColumns(10);
		manufactureDateMM.setBounds(214, 95, 27, 19);
		contentPane.add(manufactureDateMM);

		manufactureDateYY = new JTextField();
		manufactureDateYY.setColumns(10);
		manufactureDateYY.setBounds(251, 95, 40, 19);
		contentPane.add(manufactureDateYY);

		weightField = new JTextField();
		weightField.setBounds(176, 130, 96, 19);
		contentPane.add(weightField);
		weightField.setColumns(10);

		weightlbl = new JLabel("Weight:");
		weightlbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
		weightlbl.setBounds(47, 130, 45, 13);
		contentPane.add(weightlbl);

		organiclbl = new JLabel("Source:");
		organiclbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
		organiclbl.setBounds(47, 160, 45, 13);
		contentPane.add(organiclbl);

		sourceField = new JTextField();
		sourceField.setBounds(176, 160, 96, 21);
		contentPane.add(sourceField);
		sourceField.setColumns(10);

		JButton closebtn = new JButton("Close");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closebtn.setBounds(180, 190, 85, 21);
		closebtn.setBackground(Color.red);
		closebtn.setForeground(Color.black);
		contentPane.add(closebtn);

		// Set fields as non-editable
		expiryDateDD.setEditable(false);
		expiryDateMM.setEditable(false);
		expiryDateYY.setEditable(false);
		manufactureDateDD.setEditable(false);
		manufactureDateMM.setEditable(false);
		manufactureDateYY.setEditable(false);
		weightField.setEditable(false);
		sourceField.setEditable(false);

		setLocation(518, 232);
		if (freshProduceItem != null) {
			setDescription(freshProduceItem);
		}
	}

	public void setDescription(FreshProduce product) {
        // Set expiry date text
        String expiryDate = product.getExpiryDate();
        System.out.println(expiryDate); // For debugging
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
        weightField.setText(product.getWeight());
        sourceField.setText(product.getOrganicInfo());
    }

}
