import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StadiumApp extends JFrame implements MouseListener, ActionListener, DialogClientInterface{
	StadiumPanel sPanel  = new StadiumPanel(new Stadium());
	SeatInformationPanel sInfoPanel;
	SeatPricingPanel pricingPanel;
	JButton purchaseButton;
	JButton adminButton;
	JMenuBar menuBar; 
	int gameSelected = 1;
	
	// Store menu items and popup menu for access from event handlers
	JMenuItem   game1, game2, game3, game4, gameAll;
	JPopupMenu popupMenu;
	
	public StadiumApp(){
		super("Seat Pricing System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(840, 505); 
		setResizable(false);
				
		sPanel = new StadiumPanel(new Stadium());
		sInfoPanel = new SeatInformationPanel();
		pricingPanel = new SeatPricingPanel();
		purchaseButton = new JButton("Purchase");
		adminButton = new JButton("Administrator");
		
		GridBagLayout layout = new GridBagLayout(); 
		getContentPane().setLayout(layout);

		 
		GridBagConstraints constraints = new GridBagConstraints();
		 
		//adding StandiumPanel
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1; 
		constraints.gridheight = 4; 
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.ipadx = 0; 
		constraints.ipady = 0; 
		constraints.insets = new Insets(0, 0, 0, 0); 
		constraints.weightx = 0; 
		constraints.weighty = 0; 
		layout.setConstraints(sPanel, constraints); 
		getContentPane().add(sPanel);
		 
		//adding SeatInfo Panel
		constraints.gridx = 1;
		constraints.gridy = 0;		 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.fill = GridBagConstraints.BOTH;
		constraints.ipadx = 0; 
		constraints.ipady = 0; 
		constraints.insets = new Insets(0, 0, 0, 0); 
		constraints.weightx = 15; 
		constraints.weighty = 10; 
		for(int r = 0; r < Stadium.ROWS; r++){
			for(int c = 0; c < Stadium.COLUMNS; c++){
				if (sPanel.buttonArray[r][c] != null){
					sPanel.buttonArray[r][c].addMouseListener(this);
					sPanel.buttonArray[r][c].addActionListener(this);
				}				
			}
		}
		
		layout.setConstraints(sInfoPanel, constraints); 
		getContentPane().add(sInfoPanel); 
		 
		//adding SeatPricing Panel
		constraints.gridx = 1;
		constraints.gridy = 1;		
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.fill = GridBagConstraints.BOTH;
		constraints.ipadx = 0; 
		constraints.ipady = 0;
		constraints.insets = new Insets(0, 10, 0, 0); 
		constraints.weightx = 15; 
		constraints.weighty = 10; 
		layout.setConstraints(pricingPanel, constraints); 
		getContentPane().add(pricingPanel); 
		
		//adding Purchase Button		 
		constraints.gridx = 1;
		constraints.gridy = 2;	
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.ipadx = 0; 
		constraints.ipady = 0;
		constraints.insets = new Insets(10, 0, 0, 0); 
		constraints.weightx = 10; 
		constraints.weighty = 10; 
		layout.setConstraints(purchaseButton, constraints); 
		getContentPane().add(purchaseButton);
		purchaseButton.addActionListener(this);;
		 
		//adding Administrator Button		 	 
		constraints.gridx = 1;
		constraints.gridy = 3;	
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.ipadx = 0; 
		constraints.ipady = 0;
		constraints.insets = new Insets(-50, 0, 0, 0); 
		constraints.weightx = 10; 
		constraints.weighty = 10; 
		layout.setConstraints(adminButton, constraints); 
		getContentPane().add(adminButton); 
		adminButton.addActionListener(this);
		 
		
		//Create Menu Bar
		// Create the menu bar
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);   // call on JFrame, not on getContentPane()

		// Create and Add the File menu to the Menu Bar
		JMenu  gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');
		gameMenu.add(game1 = new JRadioButtonMenuItem("Game 1")); 
		gameMenu.add(game2 = new JRadioButtonMenuItem("Game 2")); 
		gameMenu.add(game3 = new JRadioButtonMenuItem("Game 3"));
		gameMenu.add(game4 = new JRadioButtonMenuItem("Game 4"));
		gameMenu.add(gameAll = new JRadioButtonMenuItem("All 4 Games"));
		menuBar.add(gameMenu); // Don't forget to do this
		
		//addAction Listener
		game1.addActionListener(this);
		game2.addActionListener(this);
		game3.addActionListener(this);
		game4.addActionListener(this);
		gameAll.addActionListener(this);
		
		// Ensure that only one radio button is on at a time 
		ButtonGroup gameButtons = new ButtonGroup(); 
		gameButtons.add(game1); 
		game1.setSelected(true);
		gameButtons.add(game2); 
		gameButtons.add(game3);
		gameButtons.add(game4);
		gameButtons.add(gameAll);

		update();
		setVisible(true); 	
	}
	
	public static void main(String args[]) { 
		StadiumApp controller = new StadiumApp();
		JFrame f = new JFrame("Seat Purchasing System"); 
		f.setJMenuBar(controller.menuBar);
	}	 
	
	//When Mouse enters a seat button
	public void mouseEntered(MouseEvent e) {
		 for(int r = 0; r < Stadium.ROWS; r++){
			for(int c = 0; c < Stadium.COLUMNS; c++){
				if(e.getSource() == sPanel.buttonArray[r][c]){
					sInfoPanel.sectionField.setText(""+ sPanel.stadium.getSeats()[r][c].getSection());
					sInfoPanel.numberField.setText(""+ sPanel.stadium.getSeats()[r][c].getNumber());
					sInfoPanel.rowField.setText(""+ sPanel.stadium.getSeats()[r][c].getRow());
					sInfoPanel.priceField.setText("$"+ sPanel.stadium.getSeats()[r][c].getPrice() + ".00");
				}
			}
		 }
					
	 }
	 
	//When Mouse leaves a seat button
	public void mouseExited(MouseEvent e) {
		 for(int r = 0; r < Stadium.ROWS; r++){
			for(int c = 0; c < Stadium.COLUMNS; c++){
				if(e.getSource() == sPanel.buttonArray[r][c]){
					sInfoPanel.sectionField.setText("");
					sInfoPanel.numberField.setText("");
					sInfoPanel.rowField.setText("");
					sInfoPanel.priceField.setText("");
				}
			}
		 }		
	 }
	 
	// This is the single event handler for when a button is pushed
	public void actionPerformed(ActionEvent e) {
		
		//Radio button is clicked, switch the games that are being represented
		if(game1.isSelected() == true){
			gameSelected = 1;
		}
		else if(game2.isSelected() == true){
			gameSelected = 2;
		}
		else if(game3.isSelected() == true){
			gameSelected = 3;
		}
		else if(game4.isSelected() == true){
				gameSelected = 4;
		}
		else{
			gameSelected = 5; //All 4 games
		}
		
		//If a radio button is pressed, reset the select on every button to false
		if((e.getSource() == game1) || (e.getSource() == game2) || (e.getSource() == game3) || (e.getSource() == game4) || (e.getSource() == gameAll)){
			for(int r = 0; r < Stadium.ROWS; r++){
				for(int c = 0; c < Stadium.COLUMNS; c++){
					if(sPanel.stadium.getSeats()[r][c] != null){
						sPanel.stadium.getSeats()[r][c].setSelected(false);
					}
				}
			}
		}
		
		//If admin button pressed
		if (e.getSource() == adminButton){
			bringUpAdmin();
		}
			
		//If purchase button pressed
		if (e.getSource() == purchaseButton){
			bringUpPurchase();	
		}
		
		//Changes the color of button to grey when selected
		 for(int r = 0; r < Stadium.ROWS; r++){
			 for(int c = 0; c < Stadium.COLUMNS; c++){
				 if(e.getSource() == sPanel.buttonArray[r][c]){
					 if (sPanel.stadium.getSeats()[r][c].isSelected() == false)
						 sPanel.stadium.getSeats()[r][c].setSelected(true);
					 else{
						 sPanel.stadium.getSeats()[r][c].setSelected(false);
					 }
				 }
			 }
		 }
		 update();
	 } 
	 
	//Update Method
	public void update(){
		 float totalPrice = 0.00f;
		 
		 if(gameSelected != 5){		 
			 for(int r = 0; r < Stadium.ROWS; r++){
				 for(int c = 0; c < Stadium.COLUMNS; c++){
					 if(sPanel.stadium.getSeats()[r][c] != null){
						 sPanel.buttonArray[r][c].setEnabled(true);
						 if(sPanel.stadium.getSeat(r,c).isSold(gameSelected-1) == true){
							 sPanel.buttonArray[r][c].setBackground(Color.white);
						 		sPanel.stadium.getSeats()[r][c].setSelected(false);
						 }
						 else if(sPanel.stadium.getSeats()[r][c].isSelected() == true){
							 sPanel.buttonArray[r][c].setBackground(Color.GRAY);
							 totalPrice = totalPrice + (float)(sPanel.stadium.getSeats()[r][c].getPrice());
						 }
						 else{
								switch(sPanel.stadium.getSeats()[r][c].getSection())	{
									case 1:	sPanel.buttonArray[r][c].setBackground(Color.red);
											break;
									case 2: sPanel.buttonArray[r][c].setBackground(Color.green);
											break;
									case 3: sPanel.buttonArray[r][c].setBackground(Color.blue);
											break;
									case 4: sPanel.buttonArray[r][c].setBackground(Color.yellow);
											break;		
								}
						 }
					 }
						
				 }
			 }
		 }
		 else{	//4 games are selected
			 for(int r = 0; r < Stadium.ROWS; r++){
				 for(int c = 0; c < Stadium.COLUMNS; c++){
					 if(sPanel.stadium.getSeats()[r][c] != null){
						 //If one or more games are sold disable button
						 if((sPanel.stadium.getSeat(r,c).isSold(0) == true) || (sPanel.stadium.getSeat(r,c).isSold(1) == true) || (sPanel.stadium.getSeat(r,c).isSold(2) == true)
								 || (sPanel.stadium.getSeat(r,c).isSold(3) == true)){
							 sPanel.buttonArray[r][c].setEnabled(false);
							 sPanel.buttonArray[r][c].setBackground(Color.white);
						 	 sPanel.stadium.getSeats()[r][c].setSelected(false);
						 }
						 else if(sPanel.stadium.getSeats()[r][c].isSelected() == true){
							 sPanel.buttonArray[r][c].setBackground(Color.GRAY);
							 totalPrice = totalPrice + (float)(sPanel.stadium.getSeats()[r][c].getPrice()*4*0.90);
						 }
						 else{
								switch(sPanel.stadium.getSeats()[r][c].getSection())	{
									case 1:	sPanel.buttonArray[r][c].setBackground(Color.red);
											break;
									case 2: sPanel.buttonArray[r][c].setBackground(Color.green);
											break;
									case 3: sPanel.buttonArray[r][c].setBackground(Color.blue);
											break;
									case 4: sPanel.buttonArray[r][c].setBackground(Color.yellow);
											break;		
								}
						 }
					 }
				 }
			 }
			 			 
		 }
		 
		 //Check to see if Seats are selected
		 boolean aSeatisSelected = false;
		 for(int r = 0; r < Stadium.ROWS; r++){
			 for(int c = 0; c < Stadium.COLUMNS; c++){
			 	if(sPanel.stadium.getSeats()[r][c] != null){
			 		if(sPanel.stadium.getSeats()[r][c].isSelected() == true)
			 			aSeatisSelected = true;
			 	}
			 }
		 }
		 if(aSeatisSelected == false){
			 purchaseButton.setEnabled(false);
		 }
		 else{
			 purchaseButton.setEnabled(true);
		 }
		 	 
		 pricingPanel.priceField.setText("$" + String.format("%.2f", totalPrice));
		 pricingPanel.hstField.setText("$"  + String.format("%.2f", totalPrice*0.15));
		 pricingPanel.costField.setText("$" + String.format("%.2f", totalPrice*1.15));
	 }
	
	public void bringUpAdmin(){
		AdminLoginDialog adminWindow = new AdminLoginDialog(this, this, this);
		adminWindow.setVisible(true);
	}
	
	public void bringUpPurchase(){
		 int counter = 0;
		 float cost = 0;
		 for(int r = 0; r < Stadium.ROWS; r++){
			 for(int c = 0; c < Stadium.COLUMNS; c++){
			 	if(sPanel.stadium.getSeats()[r][c] != null){
			 		if(sPanel.stadium.getSeats()[r][c].isSelected() == true){
			 			counter++;
			 			cost = cost + sPanel.stadium.getSeats()[r][c].getPrice();
			 		}
			 	}
			 }
		 }
		
		Seat[] seats = new Seat[counter]; 
		int counter2 = 0;
		 for(int r = 0; r < Stadium.ROWS; r++){
			 for(int c = 0; c < Stadium.COLUMNS; c++){
			 	if(sPanel.stadium.getSeats()[r][c] != null){
			 		if(sPanel.stadium.getSeats()[r][c].isSelected() == true){
			 			seats[counter2] = sPanel.stadium.getSeats()[r][c];
			 			counter2++;
			 		}
			 	}
			 }
		 }
		
		PurchaseDialog pDialog = new PurchaseDialog(""+cost, seats ,gameSelected, sPanel.stadium, this);		
		pDialog.setVisible(true);
		update();		
	}
	
	public void callSeatAdminDialogue(){
		SeatInfoDialog sinfoDia = new SeatInfoDialog(sPanel.stadium);
		sinfoDia.setVisible(true);
		
	}
	
    public void mouseClicked(MouseEvent e) {} 
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void dialogFinished(){}
    public void dialogCancelled(){}
	 
}
