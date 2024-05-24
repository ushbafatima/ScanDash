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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddPackagedGroceryDescriptionWindow extends JFrame implements AddDescriptionWindow{

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
	                	AddPackagedGroceryDescriptionWindow frame = new AddPackagedGroceryDescriptionWindow( null );
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
    public AddPackagedGroceryDescriptionWindow( PackagedProduct PackagedGroceryItem ) {
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
		// Add a window listener to handle the window close event
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int response = JOptionPane.showConfirmDialog(AddPackagedGroceryDescriptionWindow.this,
						"Do you want to quit Scan Dash?", "Confirm Exit", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					// Exit the program
					System.exit(0);
				}
				// If the response is NO_OPTION, do nothing and stay in the current window
			}
		});setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setTitle("Add Packaged Grocery Product");
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

                 setDescription(PackagedGroceryItem);
                if (ProductManagement.addProductToCategoryDB(PackagedGroceryItem)) {
                    JOptionPane.showMessageDialog(null, "Product description added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add product description.", "Error", JOptionPane.ERROR_MESSAGE);
                } 

                dispose();
            }
        });
        addDescriptionbtn.setBounds(100,210, 140, 21);
        addDescriptionbtn.setBackground(new Color(0,180,0));
        addDescriptionbtn.setForeground(Color.black);
        contentPane.add(addDescriptionbtn);

        JButton closebtn = new JButton("Close");
        closebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        closebtn.setBounds(250, 210, 85, 21);
        closebtn.setBackground(Color.red);
        closebtn.setForeground(Color.black);
        contentPane.add(closebtn);

        expiryDateMM = new JTextField();
        expiryDateMM.setColumns(10);
        expiryDateMM.setBounds(240, 70, 30, 19);
        contentPane.add(expiryDateMM);

        expiryDateYY = new JTextField();
        expiryDateYY.setColumns(10);
        expiryDateYY.setBounds(280, 70, 45, 19);
        contentPane.add(expiryDateYY);

        manufactureDateDD = new JTextField();
        manufactureDateDD.setColumns(10);
        manufactureDateDD.setBounds(200, 110, 30, 19);
        contentPane.add(manufactureDateDD);

        manufactureDateMM = new JTextField();
        manufactureDateMM.setColumns(10);
        manufactureDateMM.setBounds(240, 110, 30, 19);
        contentPane.add(manufactureDateMM);

        manufactureDateYY = new JTextField();
        manufactureDateYY.setColumns(10);
        manufactureDateYY.setBounds(280, 110, 45, 19);
        contentPane.add(manufactureDateYY);
        
        brandlbl = new JLabel("Brand");
        brandlbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
        brandlbl.setBounds(90, 150, 100, 20);
        contentPane.add(brandlbl);
        
        brandField = new JTextField();
        brandField.setBounds(200, 150, 96, 19);
        contentPane.add(brandField);
        brandField.setColumns(10);
        
        setLocation(518, 232);
    }
    @Override
	public Boolean isEmptyFields() {
        return expiryDateDD.getText().isEmpty() || expiryDateMM.getText().isEmpty() || expiryDateYY.getText().isEmpty()
                || manufactureDateDD.getText().isEmpty() || manufactureDateMM.getText().isEmpty()
                || manufactureDateYY.getText().isEmpty()||brandField.getText().isEmpty();
    }
    
    @Override
    public Boolean validateDescriptions() {
        LocalDate expiryDate = parseDate(expiryDateDD.getText(), expiryDateMM.getText(), expiryDateYY.getText());
        LocalDate manufactureDate = parseDate(manufactureDateDD.getText(), manufactureDateMM.getText(), manufactureDateYY.getText());
        String brand=brandField.getText();

        if (expiryDate == null || manufactureDate == null) {
            JOptionPane.showMessageDialog(null, "Please enter valid date formats", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (!isValidManufactureDate(manufactureDate)) {
            JOptionPane.showMessageDialog(null, "Please enter a correct manufacture date", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (expiryDate.isEqual(manufactureDate)) {
            JOptionPane.showMessageDialog(null, "Expiry date cannot be the same as manufacture date", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (expiryDate.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Expiry date cannot be before the current date", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if (expiryDate.isBefore(manufactureDate)) {
            JOptionPane.showMessageDialog(null, "Expiry date cannot be before manufacture date", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!isValidBrand(brand)) {
        	JOptionPane.showMessageDialog(null, "Enter Correct Brand", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    private LocalDate parseDate(String day, String month, String year) {
        try {
            int dayValue = Integer.parseInt(day);
            int monthValue = Integer.parseInt(month);
            int yearValue = Integer.parseInt(year);

            if (dayValue < 1 || dayValue > 31) {
                return null;
            }

            if (monthValue < 1 || monthValue > 12) {
                return null;
            }

            if (year.length() != 4) {
                return null;
            }

            return LocalDate.of(yearValue, monthValue, dayValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }

     public void setDescription(PackagedProduct product) {
        LocalDate expiryDate = parseDate(expiryDateDD.getText(), expiryDateMM.getText(), expiryDateYY.getText());
        LocalDate manufactureDate = parseDate(manufactureDateDD.getText(), manufactureDateMM.getText(), manufactureDateYY.getText());
        String brand=brandField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (expiryDate != null && manufactureDate != null) {
            product.setExpiryDate(expiryDate.format(formatter));
            product.setManufactureDate(manufactureDate.format(formatter));
        }
        product.setBrand(brand);
        
    } 

    private boolean isValidManufactureDate(LocalDate manufactureDate) {
        try {
            if (manufactureDate.isAfter(LocalDate.now())) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidBrand(String brand) {
        // Check if brand is not null or empty
        if (brand == null || brand.trim().isEmpty()) {
            return false;
        }

        // Check if brand contains only digits or is all spaces
        if (brand.matches("\\d+") || brand.trim().isEmpty()) {
            return false;
        }

        // If all validations passed, return true
        return true;
    }

    
    
}
