//Import required packages
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Enum representing the suits of the cards
enum Suit { 
	HEARTS, 
	SPADES, 
	CLUBS, 
	DIAMONDS;
}
class Card { 
	// The min and max values for a card face value 
	private static final int MIN_FACE_VALUE = 1; 
	private static final int MAX_FACE_VALUE = 13;
	
 	// An array to map a face value to its string value  
	private static String[] faceValues = new String[] {"","Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
	// This instance's suit and face value 
	private Suit suit; 
	private int faceValue;
	
 	// Constructor, constructs card with given face value and suit 
	public Card(int fv, Suit s) {  
		// Set the face value  
		setFaceValue(fv);  
		// Set the suit  
		setSuit(s); 
	}
	
 	// Return the face value of this card 
	public int getFaceValue() {  
		return faceValue; 
	}
 	
	// Set this card's face value 
	public void setFaceValue(int fv){  
		if (fv < MIN_FACE_VALUE || fv > MAX_FACE_VALUE) {   
				throw new RuntimeException("Invalid face value, must be between 1 and 13");  
		}  
		faceValue = fv; 
	}
 	
	// Return the suit of this card 
	public Suit getSuit() {  
		return suit; 
	}
 	
	// Set this card's suit 
	public void setSuit(Suit s) {  
		if (s == null) {   
			throw new RuntimeException("Suit cannot be null");  
		}  
	suit = s; 
	}
 	
	// Override toString method 
	@Override 
	public String toString() {  
		return faceValues[faceValue] + " of " + getSuit().name(); 
	}
 	
	// Return minimum face value 
	public static int getMinFaceValue() {
		return MIN_FACE_VALUE; 
	}
 	
	// Return maximum face value 
	public static int getMaxFaceValue() {  
		return MAX_FACE_VALUE; 
	}
 	
	// Return array of suits 
	public static Suit[] getSuits() {
		return Suit.values(); 
	}
}

class Deck { 
	// The arraylist of cards representing the deck 
	private ArrayList<Card> deck = new ArrayList<Card>();
	
 	// Constructor, constructs a sorted deck of cards 
	public Deck() {  
		Suit[] suits = Card.getSuits();  
		int minFv = Card.getMinFaceValue();  
		int maxFv = Card.getMaxFaceValue();
		
  		// Iterate over all face values for each suit  
		for (Suit suit : suits) {   
			for (int fv = minFv; fv <= maxFv; fv++) {    
				// Add the card to the deck    
				deck.add(new Card(fv, suit));
			}  
		} 
	} 
 	
	// Shuffle the deck  
	public void shuffle() {
		Random rand = new Random();
  		
		// Iterate over the deck  
		for (int i = 0; i < deck.size(); i++) { 
			int j = rand.nextInt(i + 1);
   		
			// Swap the cards at index i and j   
			Collections.swap(deck, i, j);   
		}  
	}
	
	// Removes one card from the deck 
 	public Card deleteOneCard() {  
		// Do not remove if the deck is already empty  
		if (deck.isEmpty()) {   
			throw new IllegalStateException("Cannot remove more cards");  
		}
  
		// Get the first card in the deck  
		Card card = deck.get(0);
		
  		// Remove it from the deck  
		deck.remove(0);
		
  		// Return the removed card  
		return card; 
	}
	
 	// Override toString method 
	@Override 
	public String toString() {  
		String s = "";  
		for (int i = 0; i < deck.size(); i++) {   
			s += deck.get(i).toString() + "\n";  
		}  
		return s; 
	}  
}

public class DeckOfCards {

	// A driver to test the classes Card and Deck 
 	public static void main(String[] args) {
 		System.out.println("Deck of Cards");  
		Deck deck = new Deck();
	
  		// Print sorted Deck  
		System.out.println("The Sorted Deck of Cards:");  
		System.out.println(deck);
	
  		// Print the shuffled Deck  
		System.out.println("The Shuffled Deck of Cards:");  
		deck.shuffle();  
		System.out.println(deck);
	
  		// Remove 3 cards  
		System.out.println("Removing 3 cards...");  
		System.out.println(deck.deleteOneCard());  
		System.out.println(deck.deleteOneCard());  
		System.out.println(deck.deleteOneCard());
	
	  	// Remove 49 more cards  
		System.out.println("Removing 49 more cards...");  
		for (int i = 0; i < 49; i++) {   
			deck.deleteOneCard();  
		}  
		System.out.println();
	
  		// Attempt to remove one more card  
		System.out.println("Removing 53rd card...");  
		try {   
			deck.deleteOneCard();  
		} catch (IllegalStateException e) {
			System.out.println(e);  
		} 
	}
}
