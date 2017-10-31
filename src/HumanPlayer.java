import java.util.ArrayList;
import java.util.Scanner;

/**.
 * This class represents a human player
 * They have to follow the rules of the game per banker
 * but they can establish their own strategy for gameplay
 * @author Brian
 *
 */
public class HumanPlayer {

	private Hand hand;
	private int currentScore = 0;

	/**.
	 * We receive the hand dealt to us by the banker
	 * @param currentHand is the hand given by the banker
	 */
	public HumanPlayer(Hand currentHand) {
		hand = currentHand;
	}

	/**.
	 * The human does not have a fixed strategy
	 * This is a boolean of whether or not the player
	 * wants to hit, and is changed by typing hit
	 * @return true if player wants another card, false otherwise
	 */
	public boolean requestHit() {
		boolean response = true;
		Scanner in = new Scanner(System.in);
		if (in.nextLine().equalsIgnoreCase("Hit")) {
			return response;
		} else {
			response = false;
			return response;
		}
	}

	/**.
	 * We compute a player's score based on their strategy
	 * @param cards we receive cards from our hand
	 * @return the int value that we assign to those cards per the rules
	 */
	private int computeScore(ArrayList<Character> cards) {
		int score = 0;
		cards = hand.getKey();
		for (char card : cards) {
			if (card == 'K' || card == 'Q'
					|| card == 'J' || card == '1') {
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

	/**.
	 * A getter for a human's score based on the hand
	 * @return the int value of that score
	 */
	public int getScore() {
		computeScore(hand.getKey());
		return currentScore;
	}
}


