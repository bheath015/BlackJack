import java.util.ArrayList;

/**
 * This class represent the dealer as a computer player
 * Their decisions/strategies are fixed
 * The way they interpret their points is simply to optimize their score
 * @author Brian
 *
 */
public class ComputerPlayer {

	private Hand hand;
	private int currentScore;

	/**.
	 * Our constructor takes the computer's current hand from the banker
	 * @param currentHand is the computer's hand
	 */
	public ComputerPlayer(Hand currentHand) {
		hand = currentHand;
	}

	/**.
	 * This method defines the computer's hit strategy
	 * They request a hit when their score is under 17
	 * @return true if computer wants another card, false otherwise
	 */
	public boolean requestHit() {
		return getScore() < 17;
	}
	/**.
	 * This method represents how the computer calculates their score
	 * The see their hand and assign the highest value
	 * to that hand without going over 21
	 * @param cards is the array of their current hand
	 * @return the int of how many points the computer has
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
	 * This method is just a getter for the computer's score
	 * @return how the computer interprets their score
	 */
	public int getScore() {
		computeScore(hand.getKey());
		return currentScore;
	}
}

