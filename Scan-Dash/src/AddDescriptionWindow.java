import javax.swing.JButton;

public interface AddDescriptionWindow {
	JButton addDescriptionbtn = new JButton();
	Boolean isEmptyFields();
	Boolean validateDescriptions();
}
