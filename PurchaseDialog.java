import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PurchaseDialog extends JDialog {
	
	JButton acceptButton;
	JButton cancelButton;
	PurchasePanel PPanel;
	Seat[] seatsArray;
	int gameNum;
	Stadium model;
	StadiumApp view;
	
	public PurchaseDialog(String cost, Seat[] seats, int gameNumber, Stadium m, StadiumApp v){
		setTitle("Purchase Information");
		setSize(450,600);
		getContentPane().setLayout(new BorderLayout(2,2)); 
		
		seatsArray = seats;
		gameNum = gameNumber;
		model = m;
		view = v;
		
		PPanel = new PurchasePanel(cost);
		JPanel buttonPanel = new JPanel();
		
		acceptButton = new JButton("Accept");
		acceptButton.setSize(50, 20);
		acceptButton.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) { 
				  acceptButtonPressed();
				 }});
				
		cancelButton = new JButton("Cancel");
		cancelButton.setSize(50, 20);
		cancelButton.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) { 
				  cancelButtonPressed();
			 }});
		
		buttonPanel.add(acceptButton);
		buttonPanel.add(cancelButton);
		
		getContentPane().add(BorderLayout.CENTER,PPanel);
		add(BorderLayout.SOUTH, buttonPanel);
		setVisible(true);
		
	}
	
	public void ActionPerformed(ActionEvent e){
		
	}
	
	public  void acceptButtonPressed(){
		float price = 0f;
		
		System.out.println("Confirmation of Purchase");
		System.out.println("Name: " + PPanel.getNameTextField().getText());
		System.out.println("Address: " + PPanel.getAddressTextField().getText());
		System.out.println("City: " + PPanel.getCityTextField().getText());
		System.out.println("Province: " + PPanel.getProvinceTextField().getText());
		System.out.println("Postal Code: " + PPanel.getPostalCodeTextField().getText());
		
		String credCardType = PPanel.getCreditCardType();
		System.out.println("Credit Card Type: " + credCardType);
		System.out.println("Expiry Date: " + PPanel.getExpiryDateTextField().getText());
		System.out.println("Credit Card Number: " + PPanel.getCreditCardTextField().getText());
		if(gameNum != 5)
			System.out.println("Seat(s) Description for Game Number " + gameNum + ":");
		else
			System.out.println("Seat(s) Description for Game Number 1-4 (all games):");
		for(Seat s: seatsArray){
			price = price + s.getPrice();
			System.out.println("    Section: " + s.getSection() + "   Row: " + s.getRow() + "   Number: " + s.getNumber() + "   Price: $"+ String.format("%.2f", price));
			
		}
		System.out.println("Seat(s) Price: $"+ String.format("%.2f",price) + "   HST: $" + String.format("%.2f",price*0.15) + "  Total: $" +  String.format("%.2f", price*1.15)); 
		
		for(int r = 0; r < Stadium.ROWS; r++){
			 for(int c = 0; c < Stadium.COLUMNS; c++){
				 if(gameNum == 5){
					 if (model.getSeats()[r][c] != null){
						 if (model.getSeats()[r][c].isSelected() == true){
							 model.getSeat(r, c).setSold(0);
							 model.getSeat(r, c).setSold(1);
							 model.getSeat(r, c).setSold(2);
							 model.getSeat(r, c).setSold(3);
						 }
					 }
				 }
				 else{
					 if (model.getSeats()[r][c] != null){
						 if (model.getSeats()[r][c].isSelected() == true){
							 model.getSeat(r, c).setSold(gameNum - 1);
							 }
					 }		 
				 }
										 
			 }
		}
		
		view.update();
		dispose();
	}
	
	public void cancelButtonPressed(){
		int result = JOptionPane.showConfirmDialog(null, 
				 "Are you sure you want to cancel your purchase ?", 
				"Cancelation Confirmation", JOptionPane.YES_NO_OPTION); if (result == 0) dispose(); else setVisible(false); 

	}
}
