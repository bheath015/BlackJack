/**.
 * This class serves the same purpose of the Banker class
 * But also adds in functionality to run
 * a Monte Carlo simulation
 */
public class BankerMC {

	private int p1WinCount;
	private int c1WinCount;
	private int pushCount;
	private int trials;
	private int errorCount;
	
	/**
	 * Our constructor establishes the variables necessary
	 * for a new simulation, and takes in the number of 
	 * trials to run
	 * We also start our experiment upon construction
	 * @param trialCount is the number of trials
	 */
	public BankerMC(int trialCount) {
		p1WinCount = 0;
		c1WinCount = 0;
		pushCount = 0;
		errorCount = 0;
		trials = trialCount;
		runExperiment();
	}
	/**
	 * This method prints the results of an experiment
	 */
	public void getResults() {
		System.out.println("The Human won " + p1WinCount + " times!");
		System.out.println("The Computer won " + c1WinCount + " times!");
		System.out.println("There were " + pushCount + " instances of a push!");
		System.out.println("Errors: " + errorCount);
	}
	
	/**
	 * This method simply runs the playgame() method
	 * a specified number of times
	 */
	private void runExperiment() {
		for (int i = 0; i < trials; i++) {
			playGame();
		}
	}

	/**
	 * This method resets the deck, hands, and players each trial
	 * This method tracks who won each round (or if a push) if blackjack
	 */
	public void playGame() {
		DeckOfCards deck = new DeckOfCards();
		Hand p1Hand = new Hand();
		Hand c1Hand = new Hand();
		HumanPlayerMC p1 = new HumanPlayerMC(p1Hand);
		ComputerPlayer c1 = new ComputerPlayer(c1Hand);
		p1Hand.initalDraw(deck.draw(), deck.draw());
		c1Hand.initalDraw(deck.draw(), deck.draw());
		if (p1Hand.checkBlackJack() && c1Hand.checkBlackJack() != true) {
			p1WinCount++;
		} else if (p1Hand.checkBlackJack() && c1Hand.checkBlackJack()) {
				pushCount++;
		} else {
			humanPlay(p1, p1Hand, deck);
			if (p1Hand.checkBust() == true) {
				c1WinCount++;
			} else if (p1Hand.check21() == true ){	
				computerPlay(c1, c1Hand, deck);
				judgeWinner(p1, c1, c1Hand);
			} else {
				computerPlay(c1, c1Hand, deck);
				judgeWinner(p1, c1, c1Hand);
			}
		}
	}

	/**
	 * This method judges the winner if there is no blackjack
	 */
	private void judgeWinner(HumanPlayerMC p1, ComputerPlayer c1, Hand c1Hand) {
		if (p1.getScore() > c1.getScore() || c1Hand.checkBust() == true) {
			p1WinCount++;
		} else if (p1.getScore() == c1.getScore()) {
			pushCount++;
		} else {
			c1WinCount++;
		}
	}
	
	/**
	 * This method takes the current deck, HP, and HP hand objects
	 * and allows the human player to play according to their strategy
	 * @param p1 is the player object
	 * @param p1Hand is the player's hand
	 * @param deck is the shared deck in use
	 */
	private void humanPlay(HumanPlayerMC p1, Hand p1Hand, DeckOfCards deck) {
		while (p1.requestHit() && p1Hand.checkBust() == false && p1Hand.check21() == false) {
			String newCard = deck.draw();
			p1Hand.anotherDraw(newCard);
			if (p1Hand.checkBust() || p1Hand.check21()) {
				break;
			}
		} 
	}
	
	/**
	 * This method takes the current deck, CP, and CP hand objects
	 * and allows the computer player to play according to their strategy
	 * @param c1 is the computer object
	 * @param c1Hand is the computer's hand
	 * @param deck is the shared deck in use
	 */
	private void computerPlay(ComputerPlayer c1, Hand c1Hand, DeckOfCards deck) {
		while (c1.requestHit() && !c1Hand.checkBust() && c1Hand.check21() == false) {
			String newCard = deck.draw();
			c1Hand.anotherDraw(newCard);
			if (c1Hand.checkBust() || c1Hand.check21()) {
				break;
			} 
		}
	}
}
