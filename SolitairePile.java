package forFinalProject;

import javax.swing.*;

public class SolitairePile extends Pile {
	public SolitairePile(JLayeredPane theLP, JButton[] theButtons) { 
		super (theLP, theButtons);

	}

	public int cardsInPile() {
		return numCardsInPile;
	}

	public CardWithImage getTopCard() {
		if((numCardsInPile >= 1) && (numCardsInPile <= 13)) {
			return myCards[numCardsInPile];

		} else {
			return null;
		}//else
	}//if

	public boolean winningConfig() {
		if ((numCardsInPile == 1) && (getTopCard().getValue() == 14)) {

			return true;

		} else { 
			return false;
		}//else
	}//winningConfig


}//pile
