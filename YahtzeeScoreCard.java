/**
 *	Describe the scorecard here.
 *
 *	@author	Mr Greenstein and Naithik Pradeep
 *	@since	September 28, 2023
 */
public class YahtzeeScoreCard {
	
	private int[] score;
	private int NUMBER_OF_CATEGORIES = 13;
	/**
	 *	Get a category score on the score card.
	 *	@param category		the category number (1 to 13)
	 *	@return				the score of that category
	 */
	public YahtzeeScoreCard() {
		score = new int[NUMBER_OF_CATEGORIES];
		for(int i = 0; i < score.length; i++) {
			score[i] = -1;
		}
	}
	public int getScore(int category) {
		return score[category - 1];
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if(score[choice - 1] <= -1) {
			if(choice <= 6) {
				this.numberScore(choice, dg);
			}
			if(choice == 7) {
				this.threeOfAKind(dg);
			}
			if(choice == 8) {
				this.fourOfAKind(dg);
			}
			if(choice == 9) {
				this.fullHouse(dg);
			}
			if(choice == 10) {
				this.smallStraight(dg);
			}
			if(choice == 11) {
				this.largeStraight(dg);
			}
			if(choice == 12) {
				this.chance(dg);
			}
			if(choice == 13) {
				this.yahtzeeScore(dg);
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		score[choice]++;
		for(int i = 0; i < NUMBER_OF_DICE; i++) {
			int die = dg.getDie(i);
			if(die == choice) {
				score[choice] = score[choice] + choice;
			}
		}
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {}
	
	public void fourOfAKind(DiceGroup dg) {}
	
	public void fullHouse(DiceGroup dg) {}
	
	public void smallStraight(DiceGroup dg) {}
	
	public void largeStraight(DiceGroup dg) {}
	
	public void chance(DiceGroup dg) {}
	
	public void yahtzeeScore(DiceGroup dg) {}

}
