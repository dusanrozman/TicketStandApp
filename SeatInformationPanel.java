import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;

public class SeatInformationPanel extends JPanel {
	//Create Labels
	JLabel sectionLabel = new JLabel();
	JLabel rowLabel = new JLabel();
	JLabel numberLabel = new JLabel();
	JLabel priceLabel = new JLabel();
	
	//Create Text Fields
	JTextField sectionField = new JTextField("");
	JTextField rowField = new JTextField("");;
	JTextField numberField = new JTextField("");;
	JTextField priceField = new JTextField("");;
	
	public SeatInformationPanel(){
		setLayout(new GridLayout(4, 2));
		setBorder(BorderFactory.createTitledBorder("SEAT INFORMATION"));
		
		//Create Labels
		JLabel sectionLabel = new JLabel("Section: ");
		JLabel rowLabel = new JLabel("Row: ");
		JLabel numberLabel = new JLabel("Number: ");
		JLabel priceLabel = new JLabel("Price: ");
		
		//add to pane	
		add(sectionLabel);
		add(sectionField);
		add(rowLabel);
		add(rowField);
		add(numberLabel);
		add(numberField);
		add(priceLabel);
		add(priceField);	
	}
	
	public static void main(String args[]) { 
		 JFrame f = new JFrame("Stadium Panel Test"); 
		 
		 f.getContentPane().add(new SeatInformationPanel()); 
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 f.setSize(250, 250); 
		 f.setVisible(true); 
		} 
}