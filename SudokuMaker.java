/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author Naithik Pradeep
 *	@version 1
 *
 */
public class SudokuMaker {

	private int[][] puzzle;
	
	/**
	 * Initializes the grid and sets all squares to 0
	 */
	public SudokuMaker() {
		puzzle = new int[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++)  {
				puzzle[i][j] = 0;
			}
		}
	}
	
	/**
	 *	printPuzzle - prints the Sudoku puzzle with borders
	 *	If the value is 0, then print an empty space; otherwise, print the number.
	 */
	public void printPuzzle() {
		System.out.print("  +-----------+-----------+-----------+\n");
		String value = "";
		for (int row = 0; row < puzzle.length; row++) {
			for (int col = 0; col < puzzle[0].length; col++) {
				// if number is 0, print a blank
				if (puzzle[row][col] == 0) value = " ";
				else value = "" + puzzle[row][col];
				if (col % 3 == 0)
					System.out.print("  |  " + value);
				else
					System.out.print("  " + value);
			}
			if ((row + 1) % 3 == 0)
				System.out.print("  |\n  +-----------+-----------+-----------+\n");
			else
				System.out.print("  |\n");
		}
	}
	
	/**
	 * Creates the puzzle
	 * @param  row  The row of the square that the method will produce a value for
	 * @param  col  The column of the square that the method will produce a value for
	 * @return true if a value has been added, false if not
	 */
	public boolean createPuzzle(int row, int col) {
		// Base Case: When the row becomes 9, finishing the grid
		if(row == 9) {
			return true;
		}
		
		// Array of potential values
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		//Randomizes array
		for(int i = numbers.length - 1; i > 0; i--) {
			int index = (int)(Math.random() * (i + 1));
			int temp = numbers[index];
			numbers[index] = numbers[i];
			numbers[i] = temp;
		}
		
		// Picks each number and tests if it is valid
		for(int i = 0; i < numbers.length; i++) {
			if(isValidPlacement(row, col, numbers[i])) {
				puzzle[row][col] = numbers[i];
				
				// Row and Column values of the next square
				int newRow = 0;
				int newCol = 0;
				if(col == 8) {
					newRow = row + 1;
					newCol = 0;
				}
				else {
					newRow = row; 
					newCol = col + 1;
				}
				if(createPuzzle(newRow, newCol)) {
					return true;
				}
			}
		}
		// Resets
		puzzle[row][col] = 0;
		return false;
	}
	
	/**
	 * Checks if the potential value is allowed to be placed in the square
	 * @param  row  The row of the square
	 * @param  col  The column of the square
	 * @param  num  The potential value that will be placed in the square
	 * @return true if the placement is valid, false if it is not
	 */
	public boolean isValidPlacement(int row, int col, int num) {
		
		// Checks if the number is already in the row or column
		for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == num || puzzle[i][col] == num) {
                return false;
            }
        }
        
        // Checks if the number is already in the square
		for(int a = 0; a < 3; a++) {
			for(int b = 0; b < 3; b++) {
				if(puzzle[a + row - row % 3][b + col - col % 3] == num) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Main method creates a puzzle object and creates the puzzle
	 */
	public static void main(String[] args) {
		SudokuMaker puzzle = new SudokuMaker();
		puzzle.createPuzzle(0, 0);
		puzzle.printPuzzle();
	}
}
