package forFinalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PileClassLab extends JFrame {

	//a standard deck of cards
	private Deck myDeck;

	// simulates a pile of cards
	private SolitairePile myPile;

	//refers to an array of card buttons 
	//to remove cards from pile
	private JButton cButton[]; 

	// button for dealing a card to the pile
	private JButton dealB;

	//button to test the cardsInPile method
	private JButton testCardsInPile;

	//button to test the getTopCard method 
	private JButton testGetTopCard;

	//button to test the winningConfig method
	private JButton testWinningConfig;

	// layered pane that can hold 14 card buttons
	private JLayeredPane myLP; 

	//where messages to the user are displayed
	private JTextField messageTF;

	//reference to content pane of the JFrame
	private Container myCP;   

	public PileClassLab() { 
		super ("Pile Lab");
		setSize (430, 600);
		myDeck = new Deck();
		myDeck.shuffle();

		myCP = getContentPane();
		myCP.setLayout(null);

		// construct JLayeredPane first
		myLP = new JLayeredPane();
		myLP.setLayout(null);
		myLP.setLocation(20,10);
		myLP.setSize( 50, 400);
		myCP.add(myLP);

		// construct array of 14 buttons for the layered pane
		// bottom button used to indicate a pile when the pile is empty
		// top 13 buttons indicate cards in each pile
		cButton= new JButton [14];//cButton points to an array of 14 JButtons
		CardBHandler cBH = new CardBHandler();//one handler all 14 buttons
		for (int i = 0; i < 14; i++) {
			cButton[i] = new JButton();
			cButton[i].setLocation(0, 25 * i);
			cButton[i].setSize( 49, 71);
			cButton[i].setIcon(null);
			cButton[i].addActionListener(cBH);
		}// for 14 buttons
		cButton[0].setLocation(0,25);// button for empty pile will be  
		// under button for first card
		myLP.add(cButton[0], 0); // add empty pile button to layered pane


		dealB = new JButton ("Deal a Card");
		dealB.setLocation(10, 440);
		dealB.setSize(140, 30);
		dealB.addActionListener(new DealBHandler());
		myCP.add(dealB);

		//test the cardsInPile method
		testCardsInPile = new JButton("test Cards In Pile");
		testCardsInPile.setLocation(10, 460);
		testCardsInPile.setSize(140, 30);
		testCardsInPile.addActionListener(new TestCardsInPileBHandler());
		myCP.add(testCardsInPile);

		//test the getTopCard method
		testGetTopCard = new JButton("test Get Top Card");
		testGetTopCard.setLocation(140, 460);
		testGetTopCard.setSize(140, 30);
		testGetTopCard.addActionListener(new TestGetTopCardBHandler());
		myCP.add(testGetTopCard);


		//test the winningConfig method
		testWinningConfig = new JButton("test Winning Config");
		testWinningConfig.setLocation(270, 460);
		testWinningConfig.setSize(150, 30);
		testWinningConfig.addActionListener(new TestWinningConfigBHandler());
		myCP.add(testWinningConfig);


		messageTF = new JTextField();
		messageTF.setLocation(10, 500);
		messageTF.setSize( 400, 50);
		messageTF.setEditable(false);
		myCP.add(messageTF);

		// construct a pile of cards
		// the pile needs a reference to its
		//layered pane and its array of buttons		
		myPile = new SolitairePile(myLP, cButton);

		setVisible(true);		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}//windowClosing
		}); //end of definition of WindowAdapter and semicolon to end the line 
	} //constructor

	public class DealBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (myDeck.cardsLeft() > 0 && !myPile.isFull()){
				CardWithImage c = myDeck.dealOne(true);
				myPile.acceptCard(c);
				messageTF.setText("The " + c.displayCard() + " card has been dealt.");
				if (myPile.isFull()) {
					dealB.setEnabled(false);
					messageTF.setText("The pile is full.");
				}//if you just filled the pile
			} else if (myDeck.cardsLeft() == 0) {
				messageTF.setText("There are no cards left in the deck.");
				dealB.setEnabled(false);
			}//  else
		} //actionPerformed
	} //DealBHandler 

	public class CardBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!myPile.isEmpty()) {
				myPile.removeCard();
				messageTF.setText("A card has been removed");
				if(myDeck.cardsLeft() > 0) {
					dealB.setEnabled(true);
				}//if
			} else {
				messageTF.setText("There are no cards to remove");
			}//else
		} //actionPerformed
	} //CardBHandler 

	public class TestCardsInPileBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			messageTF.setText("cardsInPile returns: " + myPile.cardsInPile());
		} //actionPerformed
	} //TestCardsInPileBHandler

	public class TestGetTopCardBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			messageTF.setText("getTopCard returns: \n " + myPile.getTopCard());
		} //actionPerformed
	} //TestGetTopCardBHandler


	public class TestWinningConfigBHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			messageTF.setText("winningConfig returns: " + myPile.winningConfig());
		} //actionPerformed
	} //TestWinningConfig

	public static void main (String args[]) {
		PileClassLab myAppF = new PileClassLab();
	}//main

} //PileClassLab
