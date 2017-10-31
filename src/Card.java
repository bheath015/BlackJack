/**
 * Our card class represents one card
 * We only translate cards here, not specify score
 * This class receives an integer from deck returns
 * a transalated string
 * @author Brian
 *
 */
public class Card {

	private int inputValue;
	private String card;
	/**.
	 * Our constructor just sets our input integer
	 * and output string to 0/null
	 */
	public Card() {
		inputValue = 0;
		card = null;
	}

	/**.
	 * In this method, we receive an integer representation
	 * for card from the deck
	 * We use some math to decide which rank, then which suit the card is
	 * Once decided we add that to the output string
	 * @param input is the integer representing a card
	 * @return is the string stating which card was pulled
	 */
	public String translate(int input) {
		inputValue = input + 1;
		String rank;
		String suit;
		int remainder = inputValue % 13;
		if (remainder == 0) {
			rank = "A of ";
		} else if (remainder == 1) {
			rank = "K of ";
		} else if (remainder == 11) {
			rank = "Q of ";
		} else if (remainder == 12) {
			rank = "J of ";
		} else {
			rank = Integer.toString(remainder) + " of ";
		}

		if (inputValue <= 13) {
			suit = "Spades";
		} else if (inputValue <= 26) {
			suit = "Clubs";
		} else if (inputValue <= 39) {
			suit = "Diamonds";
		} else {
			suit = "Hearts";
		}
		card = rank + suit;
		return card;
	}
}
