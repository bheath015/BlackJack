import java.util.Arrays;
import java.util.Random;
/**
 * This class represents a deck of cards with an array
 * The banker can call on this deck to get a card
 * The deck calls on the card class to interpret the card
 * @author Brian
 *
 */
public class DeckOfCards {

	private int[] deck;
	private int cardsInDeck;

	/**.
	 * Our constructor establishes a deck and the number of cards
	 * in the deck
	 * We also shuffle the deck here (or reset the deck)
	 */
	public DeckOfCards() {
		deck = new int[52];
		cardsInDeck = 51;
		shuffle();
	}

	/**.
	 * When we shuffle we simply put all cards back in a pile
	 * from which we can pull randomly
	 */
	private void shuffle() {
		for (int i = 0; i < 52; i++) {
			deck[i] = i;
			cardsInDeck = 51;
		}
	}

	/**.
	 * We draw a card by first picking a random card from the pile
	 * and returning a string of words using the card's method translate
	 * @return a translated card for the user
	 */
	public String draw() {
		Card card = new Card();
		return card.translate(selectCard());
	}

	/**.
	 * This method selects a number from the beginning of the array
	 * to the end of the pickable cards
	 * When it chooses a card it swaps it with the card at
	 * the end of the pickable list
	 * and lowers the number from which we can pick next time
	 * Essentially we make a pick pile and a discard pile
	 * If we run out of cards in the pick pile we shuffle the deck
	 * @return we return the random int representing a pickable card
	 */
	private int selectCard() {
		Random rand = new Random();
		if (cardsInDeck < 0) {
			shuffle();
		}
		int nextDraw = rand.nextInt(cardsInDeck + 1);
		int cardSelected = deck[nextDraw];
		int lastAvailableCard = deck[cardsInDeck];
		deck[cardsInDeck] = cardSelected;
		deck[nextDraw] = lastAvailableCard;
		cardsInDeck--;
		return deck[cardsInDeck + 1];
	}

	/**.
	 * While not currently in use, this method
	 * is available to ensure we are using a fair deck
	 * and from a practical side to ensure we are moving cards to the
	 * discard pile correctly
	 */
	public void printDeck() {
		System.out.println(Arrays.toString(deck));
	}
}
