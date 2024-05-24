import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class AddApplianceDescriptionWindow extends JFrame implements AddDescriptionWindow {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField capacityField;
	private JTextField efficiencyField;
	JButton addDescriptionbtn;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
         try {
           UIManager.setLookAndFeel( new FlatDarkLaf() );
            } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
            }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddApplianceDescriptionWindow frame = new AddApplianceDescriptionWindow(null);
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
	public AddApplianceDescriptionWindow(ApplianceProduct applianceProduct) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(AddApplianceDescriptionWindow.this,
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
                setTitle("Add Appliance Product");
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
                setIconImage(icon.getImage());
		setContentPane(contentPane);
		
                JPanel topPanel = new JPanel();
                topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
                topPanel.setBounds(0, 0, 450, 40);
                contentPane.add(topPanel);
                topPanel.setLayout(null);
         
                JLabel managerLabel = new JLabel("Appliances");
                managerLabel.setForeground(Color.WHITE);
                managerLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); // Bigger and bold font
                managerLabel.setBounds(20, 0, 200, 40); // Position at top left
                topPanel.add(managerLabel);
		
		
		addDescriptionbtn = new JButton("Add Description");
                addDescriptionbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isEmptyFields()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if (!validateDescriptions()) {
                    return;
                }

                setDescription(applianceProduct);
                if (ProductManagement.addProductToCategoryDB(applianceProduct)) {
                    JOptionPane.showMessageDialog(null, "Product description added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add product description.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                dispose();
            }
        });
        contentPane.setLayout(null);
        addDescriptionbtn.setBounds(100,170, 140, 21);
        addDescriptionbtn.setBackground(new Color(0,180,0));
        addDescriptionbtn.setForeground(Color.black);
        contentPane.add(addDescriptionbtn);
        
        JButton closebtn = new JButton("Close");
        closebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        closebtn.setBounds(250, 170, 85, 21);
        closebtn.setBackground(Color.red);
        closebtn.setForeground(Color.black);
        contentPane.add(closebtn);
        
        capacityField = new JTextField();
        capacityField.setBounds(200, 75, 96, 19);
        contentPane.add(capacityField);
        capacityField.setColumns(10);
        
        efficiencyField = new JTextField();
        efficiencyField.setBounds(200, 120, 96, 19);
        contentPane.add(efficiencyField);
        efficiencyField.setColumns(10);
        
        JLabel capacitylbl = new JLabel("Capacity:");
        capacitylbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
        capacitylbl.setBounds(100, 75, 80, 20);
        contentPane.add(capacitylbl);
        
        JLabel efficiencylbl = new JLabel("Efficiency Rate:");
        efficiencylbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
        efficiencylbl.setBounds(100, 120, 100, 20);
        contentPane.add(efficiencylbl);
        
        setLocation(518, 232);
        
	}
	@Override
	public Boolean isEmptyFields() {
		
	        return capacityField.getText().isEmpty() || efficiencyField.getText().isEmpty();
	    
	}
	@Override
	public Boolean validateDescriptions() {
	    try {
	        double capacity = Double.parseDouble(capacityField.getText());
	        if (capacity < 0) {
	            JOptionPane.showMessageDialog(null, "Capacity must be a positive number", "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid capacity value", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    try {
	        double efficiencyRate = Double.parseDouble(efficiencyField.getText());
	        if (efficiencyRate < 0 || efficiencyRate > 100) {
	            JOptionPane.showMessageDialog(null, "Efficiency rate must be a number between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid efficiency rate value", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    return true;
	}

	private void setDescription(ApplianceProduct applianceProduct) {
	    double capacity = Double.parseDouble(capacityField.getText());
	    double efficiencyRate = Double.parseDouble(efficiencyField.getText());

	    applianceProduct.setCapacity(capacity);
	    applianceProduct.setEfficiencyRate(efficiencyRate);
	}

}