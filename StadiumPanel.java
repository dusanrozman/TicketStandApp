import java.awt.*;
import java.awt.event.*; // Needed for ActionListener and ActionEvent 
import javax.swing.*;

public class StadiumPanel extends BoardPanel implements MouseListener {
	JButton[][] buttonArray = new JButton[Stadium.ROWS][Stadium.COLUMNS];
	Stadium stadium = new Stadium();
	
	public StadiumPanel(Stadium s){
		stadium = s;
		setLayout(new GridLayout(Stadium.ROWS, Stadium.COLUMNS, 0,0));
		setBackground(Color.WHITE);

		for(int r = 0; r < Stadium.ROWS; r++){
			for(int c = 0; c < Stadium.COLUMNS; c++){
				if(s.getSeats()[r][c] != null){
					buttonArray[r][c] = new JButton();
					buttonArray[r][c].addMouseListener(this);
					switch(s.getSeats()[r][c].getSection())	{
						case 1:	buttonArray[r][c].setBackground(Color.red);
								break;
						case 2: buttonArray[r][c].setBackground(Color.green);
								break;
						case 3: buttonArray[r][c].setBackground(Color.blue);
								break;
						case 4: buttonArray[r][c].setBackground(Color.yellow);
								break;		
					}
					
					add(buttonArray[r][c]);
				}
				else{
					add(new JLabel());
				}
			}
		}
		
	}
	
	 public static void main(String args[]) { 
		 JFrame f = new JFrame("Stadium Panel Test"); 
		 
		 f.getContentPane().add(new StadiumPanel(new Stadium())); 
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 f.setSize(649, 500); 
		 f.setVisible(true); 
	} 
	 
	 //Methods that needed to be implemented but can be left blank
	 public void mouseEntered(MouseEvent e) {
		 for(int r = 0; r < Stadium.ROWS; r++){
			for(int c = 0; c < Stadium.COLUMNS; c++){
				if(e.getSource() == buttonArray[r][c]){
				//	System.out.println("Seat Number: " + stadium.getSeats()[r][c].getSection());
				//	System.out.println("Seat Section: ");
					//System.out.println("Seat Price: ");
				}
			}
		 }		
	 }
	 public void mouseClicked(MouseEvent e) {} 
	 public void mouseExited(MouseEvent e) {}
	 public void mousePressed(MouseEvent e) {}
	 public void mouseReleased(MouseEvent e) {}

}
