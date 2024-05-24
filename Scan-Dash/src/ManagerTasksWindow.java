import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ManagerTasksWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
         try {
             UIManager.setLookAndFeel( new FlatDarkLaf() );
             } catch( Exception ex ) {
             System.err.println( "Failed to initialize LaF" );
              }
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerTasksWindow frame = new ManagerTasksWindow();
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
    public ManagerTasksWindow() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent default close operation
    	// Add a window listener to handle the window close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    ManagerTasksWindow.this,
                    "Do you want to quit Scan Dash?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    // Exit the program
                    System.exit(0);
                }
                // If the response is NO_OPTION, do nothing and stay in the current window
            }
        });
        setBounds(500, 200, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 0, 100)); // Dark blue color
        topPanel.setBounds(0, 0, 500, 60);
        contentPane.add(topPanel);
        topPanel.setLayout(null);
        
        setTitle("Manager Tasks");
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Cart Icon.png"));
        setIconImage(icon.getImage());
        
        JLabel managerLabel = new JLabel("MANAGER");
        managerLabel.setForeground(Color.WHITE);
        managerLabel.setFont(new Font("Tahoma", Font.BOLD, 24)); // Bigger and bold font
        managerLabel.setBounds(20, 10, 200, 40); // Position at top left
        topPanel.add(managerLabel);

        // Initial positions off-screen
        int startX = -160;

        JButton addUserbtn = new JButton("Add Customer");
        addUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						new addCustomerWindow().setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				
				
			}
		});
        addUserbtn.setForeground(Color.white);
        addUserbtn.setBackground(new Color(0, 0, 100));
        addUserbtn.setBounds(startX, 110, 150, 40);
        addUserbtn.setFocusPainted(false);
        contentPane.add(addUserbtn);

        JButton manageInventorybtn = new JButton("Manage Inventory");
        manageInventorybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageInventoryWindow().setVisible(true);
				dispose();
			}
		});
        manageInventorybtn.setForeground(Color.white);
        manageInventorybtn.setBounds(startX, 250, 150, 40);
        manageInventorybtn.setBackground(new Color(0, 0, 100));
        manageInventorybtn.setFocusPainted(false);
        contentPane.add(manageInventorybtn);

        JButton removeUserbtn = new JButton("Remove Customer");
        removeUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RemoveCustomerWindow().setVisible(true);
			}
		});
        removeUserbtn.setForeground(Color.white);
        removeUserbtn.setBackground(new Color(0, 0, 100));
        removeUserbtn.setBounds(startX, 180, 150, 40);
        manageInventorybtn.setFocusPainted(false);
        contentPane.add(removeUserbtn);
     // Add rounded edge logout button with text
        JButton logoutButton = new RoundedButton("Logout");
        logoutButton.setBounds(400, 70, 68, 30); // Position below the top panel, top right of content pane
        logoutButton.setBackground(Color.red); // Bootstrap danger color
        logoutButton.setForeground(Color.WHITE);
        contentPane.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle logout action, e.g., close the application or navigate to another screen
                new FirstWindow().setVisible(true);
                dispose();
            }
        });
        setLocation(518, 232);
        // Start the animation
        animateButtons(addUserbtn, removeUserbtn, manageInventorybtn);
    }

    private void animateButtons(JButton... buttons) {
        Timer timer = new Timer(10, null);
        final int targetX = 160;
        final int step = 8;

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean allButtonsInPlace = true;

                for (JButton button : buttons) {
                    int currentX = button.getBounds().x;
                    if (currentX < targetX) {
                        button.setBounds(currentX + step, button.getBounds().y, button.getWidth(), button.getHeight());
                        allButtonsInPlace = false;
                    }
                }

                if (allButtonsInPlace) {
                    timer.stop();
                }
            }
        });

        timer.start();
    }
}
//Custom JButton class to create buttons with rounded edges
class RoundedButton extends JButton {
    private static final long serialVersionUID = 1L;

    public RoundedButton(String label) {
        super(label);
        setPreferredSize(new Dimension(80, 30)); // Adjust size for the text
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(getBackground().darker());
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Rounded edges
        g.setColor(getForeground());
        g.setFont(getFont());
        g.drawString(getText(), getWidth() / 2 - g.getFontMetrics().stringWidth(getText()) / 2,
                     getHeight() / 2 + g.getFontMetrics().getAscent() / 2 - 2); // Center the text
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10); // Rounded edges
    }

    @Override
    public boolean contains(int x, int y) {
        int width = getWidth();
        int height = getHeight();
        return new RoundRectangle2D.Double(0, 0, width, height, 10, 10).contains(x, y);
    }
}


