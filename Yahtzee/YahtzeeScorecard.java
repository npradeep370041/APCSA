/**
 *	Describe the scorecard here.
 *
 *	@author	Mr Greenstein and Naithik Pradeep
 *	@since	September 28, 2023
 */
public class YahtzeeScoreCard {
	
	private int[] score;
	private int NUMBER_OF_CATEGORIES = 13;
	private int NUMBER_OF_DICE = 5;
	
	/**
	 * Initializes an array that keeps track of score for each category
	 */
	public YahtzeeScoreCard() {
		score = new int[NUMBER_OF_CATEGORIES];
		for(int i = 0; i < score.length; i++) {
			score[i] = -1;
		}
	}
	/**
	 *	Get a category score on the score card.
	 *	@param category		the category number (1 to 13)
	 *	@return				the score of that category
	 */
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
	 * Sorts the dice in order from least to greatest
	 * @param dg  The dicegroup to sort
	 * @return 	  The array of sorted dice
	 */
	public int[] sort(DiceGroup dg) {
		int[] sortedDice = new int[NUMBER_OF_DICE];
		for(int a = 0; a < sortedDice.length; a++) {
			sortedDice[a] = dg.getDie(a).getLastRollValue();
		}
		boolean swapped = true;
		int i = 0;
		while(swapped) {
			swapped = false;
			i++;
			for(int j = 0; j < sortedDice.length - 1; j++) {
				if(sortedDice[j] > sortedDice[j + 1]) {
					int temp = sortedDice[j];
					sortedDice[j] = sortedDice[j + 1];
					sortedDice[j + 1] = temp;
					swapped = true;
				}
			}
		}
		return sortedDice;	
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		score[choice - 1]++;
		int[] sortedDice = this.sort(dg);
		for(int i = 0; i < NUMBER_OF_DICE; i++) {
			int die = sortedDice[i];
			if(die == choice) {
				score[choice - 1] = score[choice - 1] + choice;
			}
		}
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		score[6]++;
		int[] sortedDice = this.sort(dg);
		boolean isThreeOfAKind = false;
		for(int i = 0; i < 3; i++) {
			if(sortedDice[i] == sortedDice[i + 1] && sortedDice[i] == sortedDice[i + 2]) {
				isThreeOfAKind = true;
			}
		}
		if(isThreeOfAKind) {
			score[6] = dg.getTotal();
		}
	}
	
	/**
	 *	Updates the scorecard for Four Of A Kind choice.
	 *	@param dg	The DiceGroup to score
	 */
	public void fourOfAKind(DiceGroup dg) {
		score[7]++;
		int[] sortedDice = this.sort(dg);
		boolean isFourOfAKind = false;
		for(int i = 0; i < 2; i++) {
			if(sortedDice[i] == sortedDice[i + 1] && sortedDice[i] == sortedDice[i + 2] &&
					sortedDice[i] == sortedDice[i + 3]) {
				isFourOfAKind = true;
			}
		}
		if(isFourOfAKind) {
			score[7] = dg.getTotal();
		}
	}
	
	/**
	 *	Updates the scorecard for Full House choice.
	 *	@param dg	The DiceGroup to score
	 */
	public void fullHouse(DiceGroup dg) {
		score[8]++;
		int[] sortedDice = this.sort(dg);
		boolean isFullHouse = false;
		if(sortedDice[0] == sortedDice[2]) {
			if(sortedDice[0] == sortedDice[1] && sortedDice[0] == sortedDice[2] &&
					sortedDice[3] == sortedDice[4]) {
				isFullHouse = true;
			}
		}
		else {
			if(sortedDice[0] == sortedDice[1] && sortedDice[2] == sortedDice[3] &&
					sortedDice[2] == sortedDice[4]) {
				isFullHouse = true;
			}
		}
		if(isFullHouse) {
			score[8] = 25;
		}
	}
	
	/**
	 *	Updates the scorecard for Small Straight choice.
	 *	@param dg	The DiceGroup to score
	 */
	public void smallStraight(DiceGroup dg) {
		score[9]++;
		int[] sortedDice = this.sort(dg);
		int counter = 0;
		for(int i = 0; i < sortedDice.length - 1; i++) {
			if(sortedDice[i] - sortedDice[i + 1] == -1) {
				counter++;
			}
		}
		if(counter >= 3) {
			score[9] = 30;
		}
	}
	
	/**
	 *	Updates the scorecard for Large Straight choice.
	 *	@param dg	The DiceGroup to score
	 */
	public void largeStraight(DiceGroup dg) {
		score[10]++;
		int[] sortedDice = this.sort(dg);
		int counter = 0;
		for(int i = 0; i < sortedDice.length - 1; i++) {
			if(sortedDice[i] - sortedDice[i + 1] == -1) {
				counter++;
			}
		}
		if(counter >= 4) {
			score[10] = 40;
		}
	}
	
	/**
	 *	Updates the scorecard for Chance choice.
	 *	@param dg	The DiceGroup to score
	 */
	public void chance(DiceGroup dg) {
		score[11] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Yahtzee choice.
	 *	@param dg	The DiceGroup to score
	 */
	public void yahtzeeScore(DiceGroup dg) {
		score[12]++;
		int[] sortedDice = this.sort(dg);
		if(sortedDice[0] == sortedDice[1] && sortedDice[0] == sortedDice[2] &&
				sortedDice[0] == sortedDice[3] && sortedDice[0] == sortedDice[4]) {
			score[12] = 50;
		}
	}

}
