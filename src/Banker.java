/** .
 * This class represents the banker function of a dealer
 * The banker will draw cards for two cards for each player
 * The banker will reveal cards as per the rules of the game
 * The banker will draw cards for a player until told otherwise
 * The banker will check players' hands to decide a winner
 * @author Brian
 *
 */
public class Banker {

private DeckOfCards deck = new DeckOfCards();
private Hand p1Hand = new Hand();
private Hand c1Hand = new Hand();
private HumanPlayer p1 = new HumanPlayer(p1Hand);
private ComputerPlayer c1 = new ComputerPlayer(c1Hand);

	/**
	 * This method represents the structure of the game
	 * The banker communicates with players per the rules of the game
	 * The banker permits specific activities as specific times 
	 * based on the rules
	 * The banker calls on other methods to execute gameplay
	 */
	public void playGame() {
		p1Hand.initalDraw(deck.draw(), deck.draw());
		c1Hand.initalDraw(deck.draw(), deck.draw());
		System.out.println("You have: " + p1Hand.getHand());
		System.out.println("The computer has: "
		+ c1Hand.getFirstCard() + " and a mystery card!");
		if (p1Hand.checkBlackJack()) {
			System.out.println("You have Black Jack!");
			System.out.println("The computer has: "
			+ c1Hand.getHand());
			System.out.println("You win!");
			if (c1Hand.checkBlackJack()) {
				System.out.println("Two BlackJacks! Push!");
			}
		} else {
			System.out.println("What would you like to do?");
			humanPlay();
			if (p1Hand.checkBust()) {
				System.out.println("Bust! You Lose!");
			} else if (p1Hand.check21()) {
				System.out.println("You reached 21, can "
						+ "the computer match you?");
				System.out.println("The computer "
						+ "has " + c1Hand.getHand());
				computerPlay();
				judgeWinner();
			} else {
				System.out.println("The computer "
						+ "has " + c1Hand.getHand());
				computerPlay();
				judgeWinner();
			}
		}
	}

	/**.
	 * This method allows the banker to see how many points each user has
	 * The banker compares those points and declares a winner
	 */
	private void judgeWinner() {
		if (p1.getScore() > c1.getScore() || c1Hand.checkBust()) {
			System.out.println("I declare p1 the winner!");
		} else if (p1.getScore() == c1.getScore()) {
			System.out.println("Push!");
		} else {
			System.out.println("You lose!");
		}
		System.out.println("P1: " + p1.getScore() + " points.");
		System.out.println("C1: " + c1.getScore() + " points.");
	}

	/**.
	 * This method is called to execute the human's turn
	 * The banker ensure the human wants another
	 * card and hasn't busted or hit 21
	 * If these conditions are met the banker will
	 * draw another card for the human
	 * The banker keeps the human aware of their cards
	 */
	private void humanPlay() {
		while (p1.requestHit()
				&& p1Hand.checkBust() == false
				&& p1Hand.check21() == false) {
			String newCard = deck.draw();
			p1Hand.anotherDraw(newCard);
			System.out.println("You drew a " + newCard);
			System.out.println("You have " + p1Hand.getHand());
			if (p1Hand.checkBust() || p1Hand.check21()) {
				break;
			} else {
			System.out.println("What would you like to do?");
			}
		}
	}

	/**.
	 * This method executes the computer's turn
	 * The banker ensure the computer wants another
	 * card and hasn't busted or hit 21
	 * If these conditions are met the banker draws another card
	 * The banker let's the computer know which cards they have
	 */
	private void computerPlay() {
		while (c1.requestHit()
				&& c1Hand.checkBust() == false
				&& c1Hand.check21() == false) {
			String newCard = deck.draw();
			c1Hand.anotherDraw(newCard);
			System.out.println("The computer drew a " + newCard);
			System.out.println("The computer "
					+ "now has " + c1Hand.getHand());
			if (c1Hand.checkBust() || c1Hand.check21()) {
				break;
			} else if (c1.requestHit()) {
			System.out.println("Another hit!");
			}
		}
	}
}
