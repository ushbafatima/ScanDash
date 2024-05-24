import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProductDetailsWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    public static void main(String[] args) {
        ProductDetailsWindow frame = new ProductDetailsWindow();
        frame.setVisible(true);
    }

    public ProductDetailsWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel productIDlbl = new JLabel("Product ID");
        productIDlbl.setBounds(186, 59, 70, 13);
        contentPane.add(productIDlbl);
        
        String scannedProduct=ScanProductWindow.getUID();
        textField = new JTextField();
        textField.setBounds(139, 82, 165, 42);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.setText(scannedProduct);
    }

    // Method to set the text of the text field to the provided UID
    public void setUID(String uid) {
        textField.setText(uid);
    }
}
