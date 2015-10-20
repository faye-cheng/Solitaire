package forFinalProject;

import javax.swing.*;

public class Pile {
	protected CardWithImage myCards[];
	protected int numCardsInPile;
	private JButton myButtons[];
	private JLayeredPane myLP;

	public Pile(JLayeredPane theLP, JButton theButtons []) {
		myCards = new CardWithImage[14];
		numCardsInPile = 0;
		myLP = theLP;
		myButtons = theButtons;
	} //Pile constructor

	public void acceptCard(CardWithImage theCard) {
		if(numCardsInPile < 13 ) {
			numCardsInPile++;
			myCards[numCardsInPile] = theCard;
			myButtons[numCardsInPile].setIcon(theCard.getImage());
			myLP.add(myButtons[numCardsInPile],new Integer(numCardsInPile));
			myLP.repaint();
		}//if
	}//acceptCard

	public void removeCard() {
		if(numCardsInPile >= 0) {
			myButtons[numCardsInPile].setIcon( null );
			myLP.remove(myButtons[numCardsInPile]);
			numCardsInPile--;
			myLP.repaint();
		}//if
	}//removeCard

	public boolean isEmpty() {
		return numCardsInPile == 0;
	} //isEmpty

	public boolean isFull() {
		return numCardsInPile == 13;
	} //isFull

}//class Pile
