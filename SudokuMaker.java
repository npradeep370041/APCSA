/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author Naithik Pradeep
 *	@version
 *
 */
public class SudokuMaker {

	private int[][] puzzle;
	
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
	public void createPuzzle(int row, int col) {
		boolean isGood = false;
		while(!isGood) {
			isGood = true;
			puzzle[row][col] = (int)(Math.random() * 9) + 1;
			for(int i = 0; i < 9; i++) {
				if(i != row && puzzle[i][col] == puzzle[row][col]) {
					isGood = false;
				}
			}
			for(int j = 0; j < 9; j++)  {
				if(j != col && puzzle[row][j] == puzzle[row][col]) {
					isGood = false;
				}
			}
			if(row
		}
		if(col == 8 && row != 8) {
			createPuzzle(row + 1, 0);
		}
		else if(row != 8) {
			createPuzzle(row, col + 1);
		}
		printPuzzle();
	}
	
	public static void main(String[] args) {
		SudokuMaker puzzle = new SudokuMaker();
		puzzle.createPuzzle(0, 0);
		puzzle.printPuzzle();
	}
}
