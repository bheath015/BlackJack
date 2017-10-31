/**
 * This class creates a BankerMC object
 * and instructs how many trials to run for the MC experiment
 * It then fetches the results
 * @author Brian
 *
 */

public class BlackJackMCTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BankerMC bankerMC = new BankerMC(1000);
		bankerMC.getResults();
	}

}