import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;

public class SeatPricingPanel extends JPanel {
		
	//Create Textfields
	JTextField priceField = new JTextField();
	JTextField hstField = new JTextField();
	JTextField costField = new JTextField();
	
	public  SeatPricingPanel(){
		setLayout(new GridLayout(3, 2));
		setBorder(BorderFactory.createTitledBorder("SELECTED SEAT PRICING"));
		
		//Create Labels
		JLabel priceLabel = new JLabel("Seat(s)  Price: ");
		JLabel hstLabel = new JLabel("HST: ");
		JLabel costLabel = new JLabel("Total Cost: ");

		//add to pane	
		add(priceLabel);
		add(priceField);
		add(hstLabel);
		add(hstField);
		add(costLabel);
		add(costField);	
	}
	
	public static void main(String args[]) { 
		 JFrame f = new JFrame("Stadium Panel Test"); 
		 
		 f.getContentPane().add(new SeatPricingPanel()); 
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 f.setSize(250, 250); 
		 f.setVisible(true); 
		} 
}