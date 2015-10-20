package forFinalProject;

import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class Tableau {

	private SolitairePile myPiles[];
	private Deck myDeck;
	private CardWithImage myCard;

	public Tableau(JLayeredPane[] myLP, JButton[][] aButton) {
		myPiles = new SolitairePile[4];
		for (int i=0; i<4; i++){
			myPiles[i] = new SolitairePile(myLP[i], aButton[i]);
		}//for
		myDeck = new Deck();
		myDeck.shuffle();
	}//Tableau

	public boolean okayToDeal4Cards(){
		if(myDeck.cardsLeft()>=4){
			return true;
		}else{
			return false;
		}//okayDeal4Cards
	}//okaytodeal4cards

	public void deal4Cards(){
		if (okayToDeal4Cards()==true) {					
			for (int i=0; i<4; i++){
				myPiles[i].acceptCard(myDeck.dealOne(true));
			}//for
		}//if
	}//deal4Cards

	public boolean okayToRemove(int pileNum){
		CardWithImage clickedCard=myPiles[pileNum].getTopCard();
		if(myPiles[pileNum].cardsInPile()>0) {
			for (int i=0; i<4; i++){
				CardWithImage otherCard = myPiles[i].getTopCard();
				if((otherCard.getSuit()==clickedCard.getSuit()) && otherCard.getValue()>clickedCard.getValue()){
					return true;
				}//if
			}//for
		}//if
		return false;
	}//okayToRemove


	public void removeCardFromPile(int pileNum){
		if(okayToRemove(pileNum)==true){
			myPiles[pileNum].removeCard();
		}//if
	}//removeCardFromPile


	public boolean okayToMove(int sourcePileNum, int destPileNum){
		if ((myPiles[destPileNum].cardsInPile()==0) && (myPiles[sourcePileNum].cardsInPile() > 0)) {
			return true;
		} else {
			return false;
		}//else
	}//okayToMove

	public void moveCard(int sourcePileNum, int destPileNum){
		if(okayToMove(sourcePileNum, destPileNum)==true){
			myCard = myPiles[sourcePileNum].getTopCard();
			myPiles[sourcePileNum].removeCard();
			myPiles[destPileNum].acceptCard(myCard);
		}//if
	}//moveCard

	public boolean userWins(){
		if(myDeck.cardsLeft()==0){
			for (int i=0; i<4; i++){
				if((myPiles[i].getTopCard().getValue()==14)&&(myPiles[i].getTopCard().getRank()=="Ace"&& (myPiles[i].cardsInPile()==1))){
					return true;
				}//if
			}///for
		}//if
		return false;
	}//userWins
}//Tableau

