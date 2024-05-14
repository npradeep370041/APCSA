/**
 *	Snake Game - <Description goes here>
 *	
 *	@author	
 *	@since	
 */
 
import java.io.PrintWriter;
import java.util.Scanner;

public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game

	/*	Constructor	*/
	public SnakeGame(int height, int width) { 
		board = new SnakeBoard(height, width);
		score = 0;
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame game = new SnakeGame(20, 25);
		game.run();
	}
	public void run() {
		int randRow = (int)(Math.random() * (board.numRows() - 4));
		int randCol = (int)(Math.random() * board.numCols());
		snake = new Snake(new Coordinate(randRow, randCol));
		int remain = board.numRows() * board.numCols() - snake.size();
		int rand = (int)(Math.random() * remain);
		int index = 0;
		int rowIndex = 0;
		int colIndex = 0;
		while(index != rand) {
			if(!snake.contains(new Coordinate(rowIndex, colIndex))) {
				index++;
			}
			if(colIndex != board.numCols() - 1) {
				colIndex++;
			}
			else {
				rowIndex++;
				colIndex = 0;
			}
		}
		target = new Coordinate(rowIndex, colIndex);
		board.printBoard(snake, target);
		boolean endGame = false;
		while(!endGame) {
			if(board.numRows() * board.numCols() - snake.size() <= 5) {
				endGame = true;
			}
			else {
				String action = Prompt.getString("Score: " + score + " (w - North, s - South, d - East, a - West, h - Help)");
				if(action.equals("q")) {
					endGame = true;
				}
				else if(action.equals("h")) {
					helpMenu();
				}
				else if(action.equals("f")) {
					PrintWriter file = FileUtils.openToWrite("savedGame.txt");
					file.println("Score " + score);
					file.println("Target " + target.getRow() + " " + target.getCol());
					file.println("Snake " + snake.size());
					for(int i = 0; i < snake.size(); i++) {
						file.println(snake.get(i).getValue().getRow() + " " + snake.get(i).getValue().getCol());
					}
					file.close();
					endGame = true;
				}
				else if(action.equals("r")) {
					Scanner file = FileUtils.openToRead("savedGame.txt");
					score = file.nextInt();
				}
				else if(action.equals("w")) {
					Coordinate c = new Coordinate(snake.get(0).getValue().getRow() - 1, snake.get(0).getValue().getCol());
					if(c.getRow() < 0 || snake.indexOf(c) != -1) {
						endGame = true;
					}
					else if(c.equals(target)) {
						snake.add(0, c);
						int remaining = board.numRows() * board.numCols() - snake.size();
						int random = (int)(Math.random() * remaining);
						int i = 0;
						int row = 0;
						int col = 0;
						while(i != random) {
							if(!snake.contains(new Coordinate(row, col))) {
								i++;
							}
							if(col != board.numCols() - 1) {
								col++;
							}
							else {
								row++;
								col = 0;
							}
						}
						target = new Coordinate(row, col);
						score++;
					}
					else {
						snake.add(0, c);
						snake.remove(snake.size() - 1);
					}
				}
				else if(action.equals("a")) {
					Coordinate c = new Coordinate(snake.get(0).getValue().getRow(), snake.get(0).getValue().getCol() - 1);
					if(c.getCol() < 0 || snake.contains(c)) {
						endGame = true;
					}
					else if(c.equals(target)) {
						snake.add(0, c);
						int remaining = board.numRows() * board.numCols() - snake.size();
						int random = (int)(Math.random() * remaining);
						int i = 0;
						int row = 0;
						int col = 0;
						while(i != random) {
							if(!snake.contains(new Coordinate(row, col))) {
								i++;
							}
							if(col != board.numCols() - 1) {
								col++;
							}
							else {
								row++;
								col = 0;
							}
						}
						target = new Coordinate(row, col);
						score++;
					}
					else {
						snake.add(0, c);
						snake.remove(snake.size() - 1);
					}
				}
				else if(action.equals("s")) {
					Coordinate c = new Coordinate(snake.get(0).getValue().getRow() + 1, snake.get(0).getValue().getCol());
					if(c.getRow() > board.numRows() - 1 || snake.contains(c)) {
						endGame = true;
					}
					else if(c.equals(target)) {
						snake.add(0, c);
						int remaining = board.numRows() * board.numCols() - snake.size();
						int random = (int)(Math.random() * remaining);
						int i = 0;
						int row = 0;
						int col = 0;
						while(i != random) {
							if(!snake.contains(new Coordinate(row, col))) {
								i++;
							}
							if(col != board.numCols() - 1) {
								col++;
							}
							else {
								row++;
								col = 0;
							}
						}
						target = new Coordinate(row, col);
						score++;
					}
					else {
						snake.add(0, c);
						snake.remove(snake.size() - 1);
					}
				}
				else if(action.equals("d")) {
					Coordinate c = new Coordinate(snake.get(0).getValue().getRow(), snake.get(0).getValue().getCol() + 1);
					if(c.getCol() > board.numCols() - 1 || snake.contains(c)) {
						endGame = true;
					}
					else if(c.equals(target)) {
						snake.add(0, c);
						int remaining = board.numRows() * board.numCols() - snake.size();
						int random = (int)(Math.random() * remaining);
						int i = 0;
						int row = 0;
						int col = 0;
						while(i != random) {
							if(!snake.contains(new Coordinate(row, col))) {
								i++;
							}
							if(col != board.numCols() - 1) {
								col++;
							}
							else {
								row++;
								col = 0;
							}
						}
						target = new Coordinate(row, col);
						score++;
					}
					else {
						snake.add(0, c);
						snake.remove(snake.size() - 1);
					}
				}
			}
			board.printBoard(snake, target);
		}
		System.out.println("Thanks for playing SnakeGame!!!");
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
}
