package forFinalProject;

import javax.swing.*;

public class CardWithImage extends Card {

	final static String sep = java.io.File.separator;
	//the image on the back of the card is the same for every card
	//in the deck and can be shared by them as a static variable of the class
	private static final ImageIcon cardBackImage = 
			new ImageIcon("images" + sep + "cardBack.gif");

	private ImageIcon myImage;//the image that will appear on the screen for this Card

	public CardWithImage( String theRank, String theSuit, int theValue) {
		super(theRank, theSuit, theValue);//invoke the 3 parameter
		// Card constructor

		//set the image appropriately
		//card images are in a folder named images
		myImage = new ImageIcon("images" + sep + theRank + theSuit + ".gif");
	}//CardWithImage constructor

	public CardWithImage( String theRank, String theSuit) {
		super(theRank, theSuit);//invoke the 2 parameter
		// Card constructor

		//set the image appropriately
		//card images are in a folder named images
		myImage = new ImageIcon("images" + sep + theRank + theSuit + ".gif");
	}//CardWithImage constructor

	public ImageIcon getImage() { //access method, returns image of the Card
		return isFaceUp() ? myImage : cardBackImage;
	}//getImage
}//CardWithImage
