package forFinalProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Solitaire extends JFrame {

	//Declare instance variables

	private JButton dealB, doneB;
	private JLayeredPane myLP[];
	private JTextField messageTF;
	private JButton aButton[][];
	private Container myCP;
	private int lastPileClickedOn;
	private boolean onSecondClick=false;
	private Tableau myTableau;

	public Solitaire() {
		super("Solitaire");
		setSize(400,700);
		setLayout(null);
		myCP = getContentPane();

		dealB= new JButton("Deal");
		dealB.addActionListener(new DealBHandler());
		dealB.setLocation(275,30);
		dealB.setSize(85,30);
		dealB.setEnabled(true);
		myCP.add(dealB);

		doneB= new JButton("Done");
		doneB.addActionListener(new DoneBHandler());
		doneB.setLocation(250, 550);
		doneB.setSize(75, 35);
		doneB.setEnabled(true);
		myCP.add(doneB);

		messageTF= new JTextField(50);
		messageTF.setLocation(20, 550);
		messageTF.setSize(200, 20);
		myCP.add(messageTF);

		myLP = new JLayeredPane [4];
		for (int i = 0; i<4; i++){
			myLP[i]= new JLayeredPane();
			myLP[i].setLayout(null);
			myLP[i].setLocation(67*i,10);
			myLP[i].setSize( 50, 400);
			myCP.add(myLP[i]);
		}
		
		aButton = new JButton [4][14];//cButton points to an array of 14 JButtons
		for (int pileNum = 0; pileNum<4; pileNum++){

			for (int i = 0; i < 14; i++) {
				aButton[pileNum][i] = new JButton();
				aButton[pileNum][i].setLocation(0, 25 * i);
				aButton[pileNum][i].setSize( 49, 71);
				aButton[pileNum][i].setIcon(null);
				aButton[pileNum][i].addActionListener(new CardBHandler(pileNum));
			}// for 14 buttons
			aButton[pileNum][0].setLocation(0,25);// button for empty pile will be 
			// under button for first card
			myLP[pileNum].add(aButton[pileNum][0], 0); // add empty pile button to layered pane
		}
		setVisible(true);
		
		myTableau = new Tableau(myLP, aButton);
		
	}//end of constructor

	public void errorMessage(){
		messageTF.setText("Invalid Move");
	}//errorMessage

	class CardBHandler implements ActionListener {
		private int myPileNum;

		public CardBHandler(int pileNum) {
			myPileNum = pileNum;
		}

			public void actionPerformed (ActionEvent e) {
				if(!onSecondClick) {
					lastPileClickedOn = myPileNum;
					onSecondClick = true;
				}else{
					onSecondClick = false;
					if(lastPileClickedOn==myPileNum){
						myTableau.removeCardFromPile(lastPileClickedOn);
					} else {
						myTableau.moveCard(lastPileClickedOn, myPileNum);
					}//else
				}//else
			}//ActionPerformed
		}//ActionListener

	public class DealBHandler implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (myTableau.okayToDeal4Cards()) {
					myTableau.deal4Cards();
				}else{
					errorMessage();
				}//else
			} //actionPerformed

		}//DealBHandler

		public class DoneBHandler implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				if (myTableau.userWins()){
					messageTF.setText("You Win!");
				} else{
					messageTF.setText("You Lost :(");
				}//else
				doneB.setEnabled(false);
				dealB.setEnabled(false);
				for (int pileNum=0; pileNum<4; pileNum++){
					for(int i=0; i<14; i++){
						aButton[pileNum][i].setEnabled(false);
					}//for
				}//for
			} //actionPerformed
		}//ShuffleBHandler

		public class aButtonBHandler implements ActionListener {
			public void actionPerformed (ActionEvent e) {
				messageTF.setText("aButton has been pressed.");
			} //actionPerformed

		}//ShuffleBHandler

		public static void main (String args[]) {
			Solitaire myAppF = new Solitaire();
		}	//main
	}//Solitaire
