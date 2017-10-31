/**
 * This class establishes and executes the player strategy
 */
import java.util.ArrayList;

public class HumanPlayerMC {

	private Hand hand;
	private int currentScore = 0;
	
	/**
	 * Our constructor sets the player's view of its hand
	 * @param currentHand is the hand passed from the banker
	 */
	public HumanPlayerMC(Hand currentHand) {
		hand = currentHand;
	}

	/**
	 * This method tells the dealer to pass new cards
	 * until the score is 14 or greater
	 * @return true if the player wants another card
	 */
	public boolean requestHit() {
		boolean response = true;
		if (getScore() < 14) {
			return response;
		} else {
			response = false;
			return response;
		}
	}
	
	/**
	 * This method is the player's computation of its score
	 * @param cards are the cards passed from the player's deck
	 * from the banker
	 * @return the int score the player has
	 */
	private int computeScore(ArrayList<Character> cards) {
		int score = 0;
		cards = hand.getKey();
		for (char card : cards) {
			if (card == 'K' || card == 'Q' || card == 'J' || card == '1') {
				score = score + 10;
			} else if (card != 'A') {
				score = score + Character.valueOf(card) - 48;
			}
		} 
		for (char card : cards) {
			if (card == 'A') {
				if (score < 11) {
					score = score + 11;
				} else {
					score = score + 1;
				}
			}
		}
		currentScore = score;
		return score;
	}
	
	/**
	 * A getter for the player's interpretation 
	 * of their score
	 * @return the int value of the score
	 */
	public int getScore() {
		computeScore(hand.getKey());
		return currentScore;
	}
}


