import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class SeatInfoDialog extends JDialog {
	private JButton 		okButton;
	private JTextField 		userTextField;
	private JPasswordField  passwordField;
	private Stadium stadium; 

	public SeatInfoDialog(Stadium s){
		super((JDialog)null,"Administrator Sales Report",true);
		
		stadium = s;
		// Add the components
        setLayout(new GridLayout(8,3));

   		JLabel aLabel = new JLabel("GAME");
   		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		aLabel = new JLabel("SEATS SOLD");
   		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		aLabel = new JLabel("SALES");
   		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		
		int g1s = 0; int g2s = 0; int g3s = 0; int g4s = 0;
		float g1sa = 0;
		float g2sa = 0;
		float g3sa = 0;
		float g4sa = 0;
		
		//JLabel Tempt
		for(int games = 0; games < 4; games++){
			for(int r = 0; r < Stadium.ROWS; r++){
				for(int c = 0; c < Stadium.COLUMNS; c++){
					if(s.getSeats()[r][c] != null){
						if (s.getSeats()[r][c].isSold(games)){
							switch(games) {
								case 0: g1sa = g1sa + s.getSeats()[r][c].getPrice();
									g1s++;
									break;
								case 1: g2sa = g2sa + s.getSeats()[r][c].getPrice();
									g2s++;
									break;
								case 2: g3sa = g3sa + s.getSeats()[r][c].getPrice();
									g3s++;
									break;
								case 3: g4sa = g4sa + s.getSeats()[r][c].getPrice();
									g4s++;
									break; 
							}
						}
					}
				}
			}
		}
			
		JLabel g1Sold = new JLabel("" + g1s);
		JLabel g2Sold = new JLabel("" + g2s);
		JLabel g3Sold = new JLabel("" +g3s);
		JLabel g4Sold = new JLabel("" +g4s );
		g1Sold.setHorizontalAlignment(JLabel.CENTER);
		g2Sold.setHorizontalAlignment(JLabel.CENTER);
		g3Sold.setHorizontalAlignment(JLabel.CENTER);
		g4Sold.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel totalSeats = new JLabel("" + (g1s + g2s + g3s + g4s));
		JLabel totalMoney = new JLabel("$" + String.format("%.2f", ((g1sa + g2sa + g3sa +g4sa)*1.13)));
		
		JLabel g1Sales = new JLabel("$" + String.format("%.2f", g1sa));
		JLabel g2Sales =  new JLabel("$" + String.format("%.2f", g2sa));
		JLabel g3Sales = new JLabel("$" + String.format("%.2f", g3sa));
		JLabel g4Sales =  new JLabel("$" + String.format("%.2f", g4sa));
		
		g1Sales.setHorizontalAlignment(JLabel.CENTER);
		g2Sales.setHorizontalAlignment(JLabel.CENTER);
		g3Sales.setHorizontalAlignment(JLabel.CENTER);
		g4Sales.setHorizontalAlignment(JLabel.CENTER);
		
		aLabel = new JLabel("1");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		
		add(g1Sold);
		add(g1Sales);
		aLabel = new JLabel("2");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		
		add(g2Sold);
		add(g2Sales);
		aLabel = new JLabel("3");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		
		add(g3Sold);
		add(g3Sales);
		
		aLabel = new JLabel("4");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		
		add(g4Sold);
		add(g4Sales);
		
		aLabel = new JLabel("-------------------");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		aLabel = new JLabel("-------------------");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		aLabel = new JLabel("-------------------");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);

		aLabel = new JLabel("TOTAL");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		add(aLabel);
		aLabel = new JLabel("");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		totalSeats.setHorizontalAlignment(JLabel.CENTER);
		add(totalSeats);
		aLabel = new JLabel("?totalMoney?");
		aLabel.setHorizontalAlignment(JLabel.CENTER);
		totalMoney.setHorizontalAlignment(JLabel.CENTER);
		add(totalMoney);

        aLabel = new JLabel("");	// Leave a blank
		add(aLabel);
		aLabel = new JLabel("");	// Leave a blank
		add(aLabel);
        add(okButton = new JButton("OK"));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
        				dispose();
        		}});

		// Prevent the window from being resized
		setSize(300, 240);
		setResizable(false);
	}
}