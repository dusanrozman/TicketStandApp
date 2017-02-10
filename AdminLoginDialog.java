import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class AdminLoginDialog extends JDialog {
	private JButton 		okButton;
	private JTextField 	userTextField;
	private JPasswordField  passwordField;
	private StadiumApp sApp;

	// The client (i.e. caller of this dialog box)
	private DialogClientInterface client;
	
	// A constructor that takes the model and client as parameters
	public AdminLoginDialog(Frame owner, DialogClientInterface cli, StadiumApp c){
		super(owner,"Administrator Login",true);
		client = cli;
		sApp = c;

		// Add the components using a grid layout
		setLayout(new GridLayout(3,3, 5, 5));

		JLabel aLabel = new JLabel("User ID:  ");
		aLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(aLabel);

		userTextField = new JTextField("");
		add(userTextField);

		aLabel = new JLabel("Password:  ");
		aLabel.setHorizontalAlignment(JLabel.RIGHT);
		add(aLabel);

		passwordField = new JPasswordField ("");
		add(passwordField);

		add(new JLabel(""));	// leave a blank
		add(okButton = new JButton("OK"));

		// Listen for ok button click
		okButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent event){
					okButtonClicked();
		}});

		// Listen for window closing: treat like cancel button
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					okButtonClicked();
		}});

		setSize(220, 120);
		setResizable(false);
	}
	
	private void okButtonClicked(){
		String userName = userTextField.getText();
		char[] inputpassword = passwordField.getPassword();
		char[] password = {'s', 'e', 'c', 'r', 'e', 't' };		
		boolean matchingPassword = Arrays.equals (password, inputpassword);

		if(userName.equals("admin") && (matchingPassword)){
			//Do this stuff
			sApp.callSeatAdminDialogue();
			
			dispose();
		}
		else{
			dispose();
			JOptionPane.showMessageDialog(null, "Incorrect User ID or Password!", 
					 "Error", JOptionPane.ERROR_MESSAGE); 
		}
	}
}