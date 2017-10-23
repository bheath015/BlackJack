import java.util.ArrayList;
/**.
 * The hand class maintains a hand for any player
 * It checks to make sure players follow the rules of the game
 * But it does not make decisions for players, define
 * a strategy, or decide how to interpret the cards in the hand
 * We have one array list to track the cards
 * and one array list to track the first digit of a card
 * which is used to count points
 * @author Brian
 *
 */
public class Hand {

	private ArrayList<Character> scoringKey = new ArrayList<Character>();
	private ArrayList<String> playerHand = new ArrayList<String>();
	private int minScore;
	private int maxScore;

	/**.
	 * When we construct our hand
	 * we reset a hand's minScore and maxScore for each hand
	 */
	public Hand() {
		minScore = 0;
		maxScore = 0;
	}

	/**.
	 * We have a method that represents the first draw
	 * Since it is the only time we take two cards
	 * We add these cards to the scoring key
	 * @param card1 is the first card we take
	 * @param card2 is the second card we take
	 */
	public void initalDraw(String card1, String card2) {
		scoringKey.add(0, takingCard(card1));
		scoringKey.add(1, takingCard(card2));
	}

	/**.
	 * This method takes in cards that are dealt
	 * It adds the card to one's hand
	 * and it retuns the key value of the card
	 * @param card is the card drawn
	 * @return the key value for that card as a char
	 */
	private Character takingCard(String card) {
		char rank = card.charAt(0);
		playerHand.add(card);
		return rank;
	}

	/**.
	 * This is a getter for the entire scoring key
	 * @return the scoring key
	 */
	public ArrayList<Character> getKey() {
		return scoringKey;
	}

	/**.
	 * This is a getter for the first card if
	 * the rules dictate we can only show one card
	 * @return the first card dealt
	 */
	public String getFirstCard() {
		return playerHand.get(0);
	}

	/**.
	 * This is a getter for the entire hand written out
	 * @return a player's hand
	 */
	public ArrayList<String> getHand() {
		return playerHand;
	}

	/**.
	 * This method represents a subsequent draw and does the
	 * same work as the initial draw method, but with one card
	 * @param card is the card received
	 */
	public void anotherDraw(String card) {
		scoringKey.add(takingCard(card));
	}

	/**.
	 * We compute scores here to ensure players follow
	 * the rules of the game, but not to enforce a strategy
	 * This will update the min and max scores of the hand
	 */
	private void computeScores() {
		minScore = 0;
		maxScore = 0;
		for (char card : getKey()) {
			if (card == 'K' || card == 'Q'
					|| card == 'J' || card == '1') {
				minScore = minScore + 10;
				maxScore = maxScore + 10;
			} else if (card != 'A') {
				minScore = minScore + Character.valueOf(card) - 48;
				maxScore = maxScore + Character.valueOf(card) - 48;
			}
		}
		for (char card : getKey()) {
			if (card == 'A') {
				minScore = minScore + 1;
				maxScore = maxScore + 11;
				}
		}
	}

	/**.
	 * We use the compute score method to ensure a player hasn't gotten
	 * blackjack
	 * @return a boolean value of whether the player has blackjack
	 */
	public boolean checkBlackJack() {
		computeScores();
		if (maxScore == 21 && scoringKey.size() == 2) {
			return true;
		}
		return false;
	}

	/**.
	 * We check whether a player has 21 and stops pulling cards
	 * @return a boolean of whether the player has 21
	 */
	public boolean check21() {
		computeScores();
		if (maxScore == 21) {
			return true;
		} else {
			return false;
		}
	}

	/**.
	 * We check whether a player busted and automatically lost
	 * @return a boolean checking for a bust
	 */
	public boolean checkBust() {
		computeScores();
		if (minScore > 21) {
			return true;
		} else {
			return false;
		}
	}

	/**.
	 * A getter for a player's min score
	 * @return the player's min score
	 */
	public int getMinScore() {
		computeScores();
		return minScore;
	}

	/** A getter for a player's max score.
	 * @return the player's max score
	 */
	public int getMaxScore() {
		computeScores();
		return getMaxScore();
	}

}