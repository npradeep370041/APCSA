import java.util.Scanner;
/**
 *	Introduce the game here
 *
 *	@author	Mr Greenstein and Naithik Pradeep
 *	@since	September 28, 2023
 */
 
public class Yahtzee {

	private YahtzeePlayer player1;
	private YahtzeePlayer player2;
	private DiceGroup dg;
	private boolean isTurnPlayer1;
	private final int NUMBER_OF_ROUNDS = 13;
	private int scorePlayer1;
	private int scorePlayer2;
	
	/**
	 * Constructor for Yahtzee Game
	 */
	public Yahtzee() {
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		dg = new DiceGroup();
		isTurnPlayer1 = true;
		scorePlayer1 = 0;
		scorePlayer2 = 0;
	}
		
	/**
	 * Prints the header to start the game
	 */
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	
	/**
	 * Starts the game by determining who goes first
	 */
	public void startGame() {
		Scanner getPlayer1 = new Scanner(System.in);
		System.out.print("\nPlayer 1, please enter your first name : ");
		player1.setName(getPlayer1.nextLine());
		Scanner getPlayer2 = new Scanner(System.in);
		System.out.print("\nPlayer 2, please enter your first name : ");
		player2.setName(getPlayer2.nextLine());
		boolean determined = false;
		while(!determined) {
			Scanner startPlayer = new Scanner(System.in);
			System.out.print("\nLet's see who will go first. " + player1.getName() + ", please hit enter to roll the dice : ");
			startPlayer.nextLine();
			dg.rollDice();
			dg.printDice();
			int totalPlayer1 = dg.getTotal();
			System.out.print("\n" + player2.getName() + ", it's your turn. Please hit enter to roll the dice : ");
			startPlayer.nextLine();
			dg.rollDice();
			dg.printDice();
			int totalPlayer2 = dg.getTotal();
			if(totalPlayer1 != totalPlayer2) {
				System.out.println(player1.getName() + ", you rolled a sum of " + totalPlayer1 +
					", and " + player2.getName() + ", you rolled a sum of " + totalPlayer2 + ".");
			}
			if(totalPlayer1 > totalPlayer2) {
				determined = true;
				isTurnPlayer1 = true;
				System.out.println(player1.getName() + ", since your sum was higher, you'll roll first.");
				
			}
			else if(totalPlayer1 < totalPlayer2) {
				determined = true;
				isTurnPlayer1 = false;
				System.out.println(player2.getName() + ", since your sum was higher, you'll roll first.");
				
			}
			else {
				System.out.print("Whoops, we have a tie (both rolled " + totalPlayer1 + 
					"). Looks like we'll have to try that again . . .");
			}
		}
	}
	
	/**
	 * Plays the game for 13 Rounds
	 */
	public void play() {
		for(int i = 1; i <= NUMBER_OF_ROUNDS; i++) {
			this.startRound(i);
			for(int j = 1; j <= 2; j++) {
				if(isTurnPlayer1) {
					this.turnPlayer1();
					isTurnPlayer1 = false;
				}
				else {
					this.turnPlayer2();
					isTurnPlayer1 = true;
				}
			}
		}
	}
	
	/**
	 * Prints the entire scorecard
	 */
	public void printCard() {
		player1.getScoreCard().printCardHeader();
		player1.getScoreCard().printPlayerScore(player1);
		player2.getScoreCard().printPlayerScore(player2);
		System.out.printf("      \t\t  1    2    3    4    5    6    7    8    9  " +
						" 10   11   12   13 \n");	
	}
	
	/**
	 * Begins each round
	 * @param roundNumber  The round number out of 13
	 */
	public void startRound(int roundNumber) {
		this.printCard();
		System.out.println("Round " + roundNumber + " of 13 rounds.");
		
	}
	
	/**
	 * Executes Player 1's Turn
	 */
	public void turnPlayer1() {
		Scanner rollPlayer1 = new Scanner(System.in);
		System.out.print(player1.getName() + ", it's your turn. Please hit enter to roll the dice : ");
		rollPlayer1.nextLine();
		dg.rollDice();
		int rollCounter = 0;
		boolean endTurn = false;
		while(rollCounter < 2 && !endTurn) {
			dg.printDice();
			Scanner input = new Scanner(System.in);
			System.out.print("Which di(c)e would you like to keep?" + 
								" Enter the values you'd like to 'hold' without spaces." + 
								" For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125" + 
								"(enter -1 if you'd like to end the turn) : ");
			String choice = input.next();
			if(choice.contains("-1")) {
				endTurn = true;
			}
			else {
				dg.rollDice(choice);
			}
			rollCounter++;
		}
		if(rollCounter == 2) {
			dg.printDice();
		}
		this.printCard();
		Scanner category = new Scanner(System.in);
		System.out.print(player1.getName() + ", now you need to make a choice. Pick a valid integer from the list above : ");
		boolean answered = false;
		while(!answered) {
			if(player1.getScoreCard().changeScore(category.nextInt(), dg) == false) {
				System.out.print("Pick a valid integer from the list above : ");
			}
			else {
				answered = true;
			}
		}
		this.printCard();
	}
	
	/**
	 * Executes Player 2's Turn
	 */
	public void turnPlayer2() {
		Scanner rollPlayer2 = new Scanner(System.in);
		System.out.print(player2.getName() + ", it's your turn. Please hit enter to roll the dice : ");
		rollPlayer2.nextLine();
		dg.rollDice();
		int rollCounter = 0;
		boolean endTurn = false;
		while(rollCounter < 2 && !endTurn) {
			dg.printDice();
			Scanner input = new Scanner(System.in);
			System.out.print("Which di(c)e would you like to keep?" + 
								" Enter the values you'd like to 'hold' without spaces." + 
								" For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125" + 
								"(enter -1 if you'd like to end the turn) : ");
			String choice = input.next();
			if(choice.contains("-1")) {
				endTurn = true;
			}
			else {
				dg.rollDice(choice);
			}
			rollCounter++;
		}
		if(rollCounter == 2) {
			dg.printDice();
		}
		this.printCard();
		Scanner category = new Scanner(System.in);
		System.out.print(player2.getName() + ", now you need to make a choice. Pick a valid integer from the list above : ");
		boolean answered = false;
		while(!answered) {
			if(player2.getScoreCard().changeScore(category.nextInt(), dg) == false) {
				System.out.print("Pick a valid integer from the list above : ");
			}
			else {
				answered = true;
			}
		}
		this.printCard();
	}
	
	/**
	 * Determines winner by adding up points 
	 */
	public void determineWinner() {
		for(int i = 1; i <= NUMBER_OF_ROUNDS; i++) {
			scorePlayer1 = scorePlayer1 + player1.getScoreCard().getScore(i);
			scorePlayer2 = scorePlayer2 + player2.getScoreCard().getScore(i);
		}
		System.out.println(player1.getName() + " had a score of " + scorePlayer1 + ".");
		System.out.println(player2.getName() + " had a score of " + scorePlayer2 + ".");
		if(scorePlayer1 > scorePlayer2) {
			System.out.print("The Winner is..." + player1.getName() + "!");
		}
		else if(scorePlayer1 < scorePlayer2) {
			System.out.print("The Winner is..." + player2.getName() + "!");
		}
		else {
			System.out.print("There was a tie!");
		}
	}
	
	/**
	 * Main method that runs the entire game
	 */
	public static void main(String[] args) {
		Yahtzee yahtzee = new Yahtzee();
		yahtzee.startGame();
		yahtzee.play();
		yahtzee.determineWinner();
	}
}
