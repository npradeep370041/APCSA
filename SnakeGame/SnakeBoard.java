/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	Naithik Pradeep
 *	@since	May 7, 2024
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		board = new char[height][width];
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		for(int i = 0; i <= board[0].length + 1; i++) {
			if(i == 0 || i == board[0].length + 1) {
				System.out.print("+ ");
			}
			else {
				System.out.print("- ");
			}
		}
		System.out.println();
		for(int j = 0; j < board.length; j++) {
			System.out.print("| ");
			for(int k = 0; k < board[0].length; k++) {
				if(snake.contains(new Coordinate(j, k))) {
					if(snake.indexOf(new Coordinate(j, k)) == 0) {
						board[j][k] = '@';
					}
					else {
						board[j][k] = '*';
					}
				}
				else if(target.equals(new Coordinate(j, k))) {
					board[j][k] = '+';
				}
				System.out.print(board[j][k] + " ");
			}
			System.out.println("|");
		}
		for(int j = 0; j <= board[0].length + 1; j++) {
			if(j == 0 || j == board[0].length + 1) {
				System.out.print("+ ");
			}
			else {
				System.out.print("- ");
			}
		}
		System.out.println();
	}
	
	/* Helper methods go here	*/
	
	/*	Accessor methods	*/
	
	public int numRows() {
		return board.length;
	}
	
	public int numCols() {
		return board[0].length;
	}
	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	/*public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
	*/
}
