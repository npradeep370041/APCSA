import java.util.Scanner;
import java.util.Random;
public class PokerSquares {
	private static String[] deck;
	private static char[] suits;
	private static char[] ranks;
	private static int[] values;
	private static char[] boardSuits;
	private static char[] boardRanks;
	private static int[] boardValues;
	private boolean[] placedCards;
	private int turnCounter;
	private static int points;
	private int highScore;
	
	public PokerSquares(int score) {
		char[] setSuits = new char[] {'♠','❤','♣','♦'};
		char[] setRanks = new char[] {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
		suits = new char[52];
		ranks = new char[52];
		values = new int[52];
		deck = new String[52];
		int cardNumber = 0; 
		for(int suitInit = 0; suitInit < 4; suitInit++) {
			for(int rankInit = 0; rankInit < 13; rankInit++) {
				deck[cardNumber] = "\n _____\n|     |\n|  " + setRanks[rankInit] + "  |\n|  " + setSuits[suitInit] + "  |\n|_____|";
				cardNumber++;
				suits[suitInit] = setSuits[suitInit];
				ranks[rankInit] = setRanks[rankInit];
				values[rankInit] = rankInit + 2;
			}
		}
		boardSuits = new char[25];
		boardRanks = new char[25];
		boardValues = new int[25];
		placedCards = new boolean[25];
		for(int boardInit = 0; boardInit < 25; boardInit++) {
			boardSuits[boardInit] = ' ';
			boardRanks[boardInit] = ' ';
			boardValues[boardInit] = 0;
			placedCards[boardInit] = false;
		}
		turnCounter = 1;
		points = 0;
		highScore = score;
	}
	public static void shuffle() {
		Random random = new Random();
		for(int randomizer = 0; randomizer < 52; randomizer++) {
			int randomIndexToSwap = random.nextInt(deck.length);
			String temporary = deck[randomIndexToSwap];
			deck[randomIndexToSwap] = deck[randomizer];
			deck[randomizer] = temporary;
			
		}
		for(int index = 0; index < 52; index++) {
			String card = deck[index];
			// Setting Suits
			if(card.contains("♠")) {
				suits[index] = '♠';
			}
			else if(card.contains("❤")) {
				suits[index] = '❤';
			}
			else if(card.contains("♣")) {
				suits[index] = '♣';
			}
			else if(card.contains("♦")) {
				suits[index] = '♦';
			}
			
			// Setting Ranks and Values
			if(card.contains("2")) {
				ranks[index] = '2';
				values[index] = 2;
			}
			else if(card.contains("3")) {
				ranks[index] = '3';
				values[index] = 3;
			}
			else if(card.contains("4")) {
				ranks[index] = '4';
				values[index] = 4;
			}
			else if(card.contains("5")) {
				ranks[index] = '5';
				values[index] = 5;
			}
			else if(card.contains("6")) {
				ranks[index] = '6';
				values[index] = 6;
			}
			else if(card.contains("7")) {
				ranks[index] = '7';
				values[index] = 7;
			}
			else if(card.contains("8")) {
				ranks[index] = '8';
				values[index] = 8;
			}
			else if(card.contains("9")) {
				ranks[index] = '9';
				values[index] = 9;
			}
			else if(card.contains("T")) {
				ranks[index] = 'T';
				values[index] = 10;
			}
			else if(card.contains("J")) {
				ranks[index] = 'J';
				values[index] = 11;
			}
			else if(card.contains("Q")) {
				ranks[index] = 'Q';
				values[index] = 12;
			}
			else if(card.contains("K")) {
				ranks[index] = 'K';
				values[index] = 13;
			}
			else if(card.contains("A")) {
				ranks[index] = 'A';
				values[index] = 14;
			}
		}
	}
	public void startGame() {
		System.out.println("\nWelcome to Poker Squares!");
		System.out.println("\nHere are the rules:");
		System.out.println("	* There is a deck of 52 cards shuffled");
		System.out.println("	* The player places cards one by one into a 5 x 5 grid");
		System.out.println("	* Once a card has been placed, it cannot be moved again");
		System.out.println("	* Each row and column will combined and scored based on the poker hands");
		printHands(); 
		System.out.println("	* The objective of the game is to score as many points as possible");
		boolean answered = false;
		while(!answered) {
			System.out.println("Do you want to play? Type yes or no.");
			Scanner play = new Scanner(System.in);
			String answer = play.nextLine();
			if(answer.toLowerCase().equals("yes")) {
				answered = true;
			}
			else if(answer.toLowerCase().equals("no")) {
				answered = true;
				System.exit(0);
			}
			else {
				System.out.println("Answer with yes or no");
			}
		}
	}
	public void play() {
		while(turnCounter <= 25) {
			System.out.println("Here is your card:");
			System.out.println(deck[turnCounter - 1]);
			boolean answered = false;
			while(!answered) {
				boolean failed = true;
				Scanner enterPlacement = new Scanner(System.in);
				System.out.println("Where do you want to place the card? Type in the number of the square (Left to Right, Top to Bottom). Or type 'help' to see the Poker hands.");
				if(!enterPlacement.hasNextInt()) {
					String help = enterPlacement.nextLine();
					if(help.toLowerCase().equals("help")) {
						printHands();
						failed = false;
					}
				}
				else {
					int placement = enterPlacement.nextInt();
					if(placement > 0 && placement <= 25) {
						if(placedCards[placement - 1] != true) {
							boardSuits[placement - 1] = suits[turnCounter - 1];
							boardRanks[placement - 1] = ranks[turnCounter - 1];
							boardValues[placement - 1] = values[turnCounter - 1];
							placedCards[placement - 1] = true;
							turnCounter++;
							printBoard();
							failed = false;
							answered = true;
						}
						else {
							failed = true;
						}
					}
					else {
						failed = true;
					}
				}
				if(failed) {
					System.out.println("That is not a valid placement");
				}
			}
		}
	}
	
	public static void printHands() {
		System.out.println("Here are the poker hands:");
		System.out.println("	* Royal Flush -> 100 points");
		System.out.println("	* Straight Flush -> 75 points");
		System.out.println("	* Four of a Kind -> 50 points");
		System.out.println("	* Full House -> 25 points");
		System.out.println("	* Flush -> 20 points");
		System.out.println("	* Straight -> 15 points");
		System.out.println("	* Three of a Kind -> 10 points");
		System.out.println("	* Two Pairs -> 5 points");
		System.out.println("	* One Pair -> 2 points");
	}
	public static void printBoard() {
		System.out.println("Here is the board:");
		System.out.println(" _____  _____  _____  _____  _____ \n" +
						   "|     ||     ||     ||     ||     |\n" +
						   "|  "+boardRanks[0]+"  ||  "+boardRanks[1]+"  ||  "+boardRanks[2]+"  ||  "+boardRanks[3]+"  ||  "+boardRanks[4]+"  |\n" +
						   "|  "+boardSuits[0]+"  ||  "+boardSuits[1]+"  ||  "+boardSuits[2]+"  ||  "+boardSuits[3]+"  ||  "+boardSuits[4]+"  |\n" +
						   "|_____||_____||_____||_____||_____|\n" +
						   " _____  _____  _____  _____  _____ \n" +
						   "|     ||     ||     ||     ||     |\n" +
						   "|  "+boardRanks[5]+"  ||  "+boardRanks[6]+"  ||  "+boardRanks[7]+"  ||  "+boardRanks[8]+"  ||  "+boardRanks[9]+"  |\n" +
						   "|  "+boardSuits[5]+"  ||  "+boardSuits[6]+"  ||  "+boardSuits[7]+"  ||  "+boardSuits[8]+"  ||  "+boardSuits[9]+"  |\n" +
						   "|_____||_____||_____||_____||_____|\n" +
						   " _____  _____  _____  _____  _____ \n" +
						   "|     ||     ||     ||     ||     |\n" +
						   "|  "+boardRanks[10]+"  ||  "+boardRanks[11]+"  ||  "+boardRanks[12]+"  ||  "+boardRanks[13]+"  ||  "+boardRanks[14]+"  |\n" +
						   "|  "+boardSuits[10]+"  ||  "+boardSuits[11]+"  ||  "+boardSuits[12]+"  ||  "+boardSuits[13]+"  ||  "+boardSuits[14]+"  |\n" +
						   "|_____||_____||_____||_____||_____|\n" +
						   " _____  _____  _____  _____  _____ \n" +
						   "|     ||     ||     ||     ||     |\n" +
						   "|  "+boardRanks[15]+"  ||  "+boardRanks[16]+"  ||  "+boardRanks[17]+"  ||  "+boardRanks[18]+"  ||  "+boardRanks[19]+"  |\n" +
						   "|  "+boardSuits[15]+"  ||  "+boardSuits[16]+"  ||  "+boardSuits[17]+"  ||  "+boardSuits[18]+"  ||  "+boardSuits[19]+"  |\n" +
						   "|_____||_____||_____||_____||_____|\n" +
						   " _____  _____  _____  _____  _____ \n" +
						   "|     ||     ||     ||     ||     |\n" +
						   "|  "+boardRanks[20]+"  ||  "+boardRanks[21]+"  ||  "+boardRanks[22]+"  ||  "+boardRanks[23]+"  ||  "+boardRanks[24]+"  |\n" +
						   "|  "+boardSuits[20]+"  ||  "+boardSuits[21]+"  ||  "+boardSuits[22]+"  ||  "+boardSuits[23]+"  ||  "+boardSuits[24]+"  |\n" +
						   "|_____||_____||_____||_____||_____|\n");
	}

	public static boolean isRoyalFlush(int tier, int tierNumber) {
		if(tier == 0) {
			if(boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 1] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 2] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber -1 ) + 3] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 4]) {
				int counter = 0;
				boolean checkt = true;
				boolean checkj = true;
				boolean checkq = true;
				boolean checkk = true;
				boolean checka = true;
				for(int checker = 0; checker < 5; checker++) {
					if(boardRanks[5 * (tierNumber - 1) + checker] == 'T' && checkt == true) {
						counter++;
						checkt = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == 'J' && checkj == true) {
						counter++;
						checkj = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == 'Q' && checkq == true) {
						counter++;
						checkq = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == 'K' && checkk == true) {
						counter++;
						checkk = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == 'A' && checka == true) {
						counter++;
						checka = false;
					}
				}
				if(counter == 5) {
					return true;
				} 
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			if(boardSuits[tierNumber - 1] == boardSuits[tierNumber + 4] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 9] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 14] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 19]) {
				int counter = 0;
				boolean checkt = true;
				boolean checkj = true;
				boolean checkq = true;
				boolean checkk = true;
				boolean checka = true;
				for(int checker = 0; checker < 5; checker++) {
					if(boardRanks[tierNumber - 1 + (5 * checker)] == 'T' && checkt == true) {
						counter++;
						checkt = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == 'J' && checkj == true) {
						counter++;
						checkj = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == 'Q' && checkq == true) {
						counter++;
						checkq = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == 'K' && checkk == true) {
						counter++;
						checkk = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == 'A' && checka == true) {
						counter++;
						checka = false;
					}
				}
				if(counter == 5) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
	}
	public static boolean isStraightFlush(int tier, int tierNumber) {
		if(tier == 0) {
			if(boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 1] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 2] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber -1 ) + 3] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 4]) {
				int lowest = 100;
				boolean isAce = false;
				for(int lower = 0; lower < 5; lower++) {
					if(lowest > boardValues[5 * (tierNumber - 1) + lower]) {
						lowest = boardValues[5 * (tierNumber - 1) + lower];
					}
					if(boardRanks[5 * (tierNumber - 1) + lower] == 'A') {
						isAce = true;
					}
				}
				if(isAce && lowest == 2) {
					int counter = 0;
					boolean checka = true;
					boolean check2 = true;
					boolean check3 = true;
					boolean check4 = true;
					boolean check5 = true;
					for(int checker = 0; checker < 5; checker++) {
						if(boardRanks[5 * (tierNumber - 1) + checker] == 'A' && checka == true) {
							counter++;
							checka = false;
						}
						if(boardRanks[5 * (tierNumber - 1) + checker] == '2' && check2 == true) {
							counter++;
							check2 = false;
						}
						if(boardRanks[5 * (tierNumber - 1) + checker] == '3' && check3 == true) {
							counter++;
							check3 = false;
						}
						if(boardRanks[5 * (tierNumber - 1) + checker] == '4' && check4 == true) {
							counter++;
							check4 = false;
						}
						if(boardRanks[5 * (tierNumber - 1) + checker] == '5' && check5 == true) {
							counter++;
							check5 = false;
						}
					}
					if(counter == 5) {
						return true;
					} 
					else {
						return false;
					}
				}
				else {
					int counter = 0;
					for(int checker1 = 0; checker1 < 5; checker1++) {
						for(int checker2 = 0; checker2 < 5; checker2++) {
							if(boardValues[5 * (tierNumber - 1) + checker1] - boardValues[5 * (tierNumber - 1) + checker2] == 1) {
								counter++;
							}
						}
					}
					if(counter == 4) {
						return true;
					}
					else {
						return false;
					}
				}	
			}
			else {
				return false;
			}
		}
		else {
			if(boardSuits[tierNumber - 1] == boardSuits[tierNumber + 4] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 9] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 14] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 19]) {
				int lowest = 100;
				boolean isAce = false;
				for(int lower = 0; lower < 5; lower++) {
					if(lowest > boardValues[tierNumber - 1 + (5 * lower)]) {
						lowest = boardValues[tierNumber - 1 + (5 * lower)];
					}
					if(boardRanks[tierNumber - 1 + (5 * lower)] == 'A') {
						isAce = true;
					}
				}
				if(isAce && lowest == 2) {
					int counter = 0;
					boolean checka = true;
					boolean check2 = true;
					boolean check3 = true;
					boolean check4 = true;
					boolean check5 = true;
					for(int checker = 0; checker < 5; checker++) {
						if(boardRanks[tierNumber - 1 + (5 * checker)] == 'A' && checka == true) {
							counter++;
							checka = false;
						}
						if(boardRanks[tierNumber - 1 + (5 * checker)] == '2' && check2 == true) {
							counter++;
							check2 = false;
						}
						if(boardRanks[tierNumber - 1 + (5 * checker)] == '3' && check3 == true) {
							counter++;
							check3 = false;
						}
						if(boardRanks[tierNumber - 1 + (5 * checker)] == '4' && check4 == true) {
							counter++;
							check4 = false;
						}
						if(boardRanks[tierNumber - 1 + (5 * checker)] == '5' && check5 == true) {
							counter++;
							check5 = false;
						}
					}
					if(counter == 5) {
						return true;
					} 
					else {
						return false;
					}
				}
				else {
					int counter = 0;
					for(int checker1 = 0; checker1 < 5; checker1++) {
						for(int checker2 = 0; checker2 < 5; checker2++) {
							if(boardValues[tierNumber - 1 + (5 * checker1)] - boardValues[tierNumber - 1 + (5 * checker2)] == 1) {
								counter++;
							}
						}
					}
					if(counter == 4) {
						return true;
					}
					else {
						return false;
					}
				}	
			}
			else {
				return false;
			}
		}
	}
	public static boolean isFourOfAKind(int tier, int tierNumber) {
		if(tier == 0) {
			int counter = 0;
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[5 * (tierNumber - 1) + counter1] == boardValues[5 * (tierNumber - 1) + counter2]) {
						counter++;
					}
				}
			}
			if(counter == 16) {
				return true;
			} 
			else {
				return false;
			}
		}
		else {
			int counter = 0; 
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[tierNumber - 1 + (5 * counter1)] == boardValues[tierNumber - 1 + (5 * counter2)]) {
						counter++;
					}
				}
			}
			if(counter == 16) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public static boolean isFullHouse(int tier, int tierNumber) {
		if(tier == 0) {
			int counter = 0;
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[5 * (tierNumber - 1) + counter1] == boardValues[5 * (tierNumber - 1) + counter2]) {
						counter++;
					}
				}
			}
			if(counter == 13) {
				return true;
			} 
			else {
				return false;
			}
		}
		else {
			int counter = 0; 
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[tierNumber - 1 + (5 * counter1)] == boardValues[tierNumber - 1 + (5 * counter2)]) {
						counter++;
					}
				}
			}
			if(counter == 13) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public static boolean isFlush(int tier, int tierNumber) {
		if(tier == 0) {
			if(boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 1] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 2] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber -1 ) + 3] && boardSuits[5 * (tierNumber - 1)] == boardSuits[5 * (tierNumber - 1) + 4]) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(boardSuits[tierNumber - 1] == boardSuits[tierNumber + 4] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 9] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 14] && boardSuits[tierNumber - 1] == boardSuits[tierNumber + 19]) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public static boolean isStraight(int tier, int tierNumber) {
		if(tier == 0) {
			int lowest = 100;
			boolean isAce = false;
			for(int lower = 0; lower < 5; lower++) {
				if(lowest > boardValues[5 * (tierNumber - 1) + lower]) {
					lowest = boardValues[5 * (tierNumber - 1) + lower];
				}
				if(boardRanks[5 * (tierNumber - 1) + lower] == 'A') {
					isAce = true;
				}
			}
			if(isAce && lowest == 2) {
				int counter = 0;
				boolean checka = true;
				boolean check2 = true;
				boolean check3 = true;
				boolean check4 = true;
				boolean check5 = true;
				for(int checker = 0; checker < 5; checker++) {
					if(boardRanks[5 * (tierNumber - 1) + checker] == 'A' && checka == true) {
						counter++;
						checka = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == '2' && check2 == true) {
						counter++;
						check2 = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == '3' && check3 == true) {
						counter++;
						check3 = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == '4' && check4 == true) {
						counter++;
						check4 = false;
					}
					if(boardRanks[5 * (tierNumber - 1) + checker] == '5' && check5 == true) {
						counter++;
						check5 = false;
					}
				}
				if(counter == 5) {
					return true;
				} 
				else {
					return false;
				}
			}
			else {
				int counter = 0;
				for(int checker1 = 0; checker1 < 5; checker1++) {
					for(int checker2 = 0; checker2 < 5; checker2++) {
						if(boardValues[5 * (tierNumber - 1) + checker1] - boardValues[5 * (tierNumber - 1) + checker2] == 1) {
							counter++;
						}
					}
				}
				if(counter == 4) {
					return true;
				}
				else {
					return false;
				}
			}	
		}
		else {
			int lowest = 100;
			boolean isAce = false;
			for(int lower = 0; lower < 5; lower++) {
				if(lowest > boardValues[tierNumber - 1 + (5 * lower)]) {
					lowest = boardValues[tierNumber - 1 + (5 * lower)];
				}
				if(boardRanks[ tierNumber - 1 + (5 * lower)] == 'A') {
					isAce = true;
				}
			}
			if(isAce && lowest == 2) {
				int counter = 0;
				boolean checka = true;
				boolean check2 = true;
				boolean check3 = true;
				boolean check4 = true;
				boolean check5 = true;
				for(int checker = 0; checker < 5; checker++) {
					if(boardRanks[tierNumber - 1 + (5 * checker)] == 'A' && checka == true) {
						counter++;
						checka = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == '2' && check2 == true) {
						counter++;
						check2 = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == '3' && check3 == true) {
						counter++;
						check3 = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == '4' && check4 == true) {
						counter++;
						check4 = false;
					}
					if(boardRanks[tierNumber - 1 + (5 * checker)] == '5' && check5 == true) {
						counter++;
						check5 = false;
					}
				}
				if(counter == 5) {
					return true;
				} 
				else {
					return false;
				}
			}
			else {
				int counter = 0;
				for(int checker1 = 0; checker1 < 5; checker1++) {
					for(int checker2 = 0; checker2 < 5; checker2++) {
						if(boardValues[tierNumber - 1 + (5 * checker1)] - boardValues[tierNumber - 1 + (5 * checker2)] == 1) {
							counter++;
						}
					}
				}
				if(counter == 4) {
					return true;
				}
				else {
					return false;
				}
			}	
		}
	}
	public static boolean isThreeOfAKind(int tier, int tierNumber) {
		if(tier == 0) {
			int counter = 0;
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[5 * (tierNumber - 1) + counter1] == boardValues[5 * (tierNumber - 1) + counter2]) {
						counter++;
					}
				}
			}
			if(counter == 9) {
				return true;
			} 
			else {
				return false;
			}
		}
		else {
			int counter = 0; 
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[tierNumber - 1 + (5 * counter1)] == boardValues[tierNumber - 1 + (5 * counter2)]) {
						counter++;
					}
				}
			}
			if(counter == 9) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public static boolean isTwoPairs(int tier, int tierNumber) {
		if(tier == 0) {
			int counter = 0;
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[5 * (tierNumber - 1) + counter1] == boardValues[5 * (tierNumber - 1) + counter2]) {
						counter++;
					}
				}
			}
			if(counter == 8) {
				return true;
			} 
			else {
				return false;
			}
		}
		else {
			int counter = 0; 
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[tierNumber - 1 + (5 * counter1)] == boardValues[tierNumber - 1 + (5 * counter2)]) {
						counter++;
					}
				}
			}
			if(counter == 8) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public static boolean isOnePair(int tier, int tierNumber) {
		if(tier == 0) {
			int counter = 0;
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[5 * (tierNumber - 1) + counter1] == boardValues[5 * (tierNumber - 1) + counter2]) {
						counter++;
					}
				}
			}
			if(counter == 4) {
				return true;
			} 
			else {
				return false;
			}
		}
		else {
			int counter = 0; 
			for(int counter1 = 0; counter1 < 5; counter1++) {
				for(int counter2 = 0; counter2 < 5; counter2++) {
					if(boardValues[tierNumber - 1 + (5 * counter1)] == boardValues[tierNumber - 1 + (5 * counter2)]) {
						counter++;
					}
				}
			}
			if(counter == 4) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public static void determinePoints() {
		int tier = 0;
		while(tier <= 1) {
			for(int tierNumber = 1; tierNumber <= 5; tierNumber++) {
				if(isRoyalFlush(tier, tierNumber) == true) {
					points = points + 100;
					System.out.println(tierNumber + " rflush");
				}
				else if(isStraightFlush(tier, tierNumber) == true) {
					points = points + 75;
					System.out.println(tierNumber + " sflush");
				}
				else if(isFourOfAKind(tier, tierNumber) == true) {
					points = points + 50;
					System.out.println(tierNumber + " four");
				}
				else if(isFullHouse(tier, tierNumber) == true) {
					points = points + 25;
					System.out.println(tierNumber + " full");
				}
				else if(isFlush(tier, tierNumber) == true) {
					points = points + 20;
					System.out.println(tierNumber + " flush");
				}
				else if(isStraight(tier, tierNumber) == true) {
					points = points + 15;
					System.out.println(tierNumber + " straight");
				}
				else if(isThreeOfAKind(tier, tierNumber) == true) {
					points = points + 10;
					System.out.println(tierNumber + " three");
				}
				else if(isTwoPairs(tier, tierNumber) == true) {
					points = points + 5;
					System.out.println(tierNumber + " twopair");
				}
				else if(isOnePair(tier, tierNumber) == true) {
					points = points + 2;
					System.out.println(tierNumber + " onepair");
				}
			}
			tier++;
		}
		System.out.println("You have " + points + " points");
	}
	public boolean playAgain() {
		return false;
	}
	public static void main(String[] args) {
		boolean canPlayAgain = true;
		int score = 0;
		while(canPlayAgain) {
			PokerSquares game = new PokerSquares(score);
			game.shuffle();
			game.startGame();
			game.play();
			game.determinePoints();
			if(points > score) {
				score = points;
			}
			canPlayAgain = game.playAgain();
		}
	}
}
