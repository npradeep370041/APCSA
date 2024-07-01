import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *	SudokuSolver - Solves an incomplete Sudoku puzzle using recursion and backtracking
 *
 *	@author	
 *	@since	
 *
 */
public class SudokuSolver {

	private int[][] puzzle;		// the Sudoku puzzle
	
	private String PUZZLE_FILE = "puzzle1.txt";	// default puzzle file
	
	/* Constructor */
	public SudokuSolver() {
		puzzle = new int[9][9];
		// fill puzzle with zeros
		for (int row = 0; row < puzzle.length; row++)
			for (int col = 0; col < puzzle[0].length; col++)
				puzzle[row][col] = 0;
	}
	
	public static void main(String[] args) {
		SudokuSolver sm = new SudokuSolver();
		sm.run(args);
	}
	
	public void run(String[] args) {
		// get the name of the puzzle file
		String puzzleFile = PUZZLE_FILE;
		if (args.length > 0) puzzleFile = args[0];
		
		System.out.println("\nSudoku Puzzle Solver");
		// load the puzzle
		System.out.println("Loading puzzle file " + puzzleFile);
		loadPuzzle(puzzleFile);
		printPuzzle();
		// solve the puzzle starting in (0,0) spot (upper left)
		solvePuzzle(0, 0);
		printPuzzle();
	}
	
	/**	Load the puzzle from a file
	 *	@param filename		name of puzzle file
	 */
	public void loadPuzzle(String filename) {
		Scanner infile = FileUtils.openToRead(filename);
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				puzzle[row][col] = infile.nextInt();
		infile.close();
	}
	
	/**	Solve the Sudoku puzzle using brute-force method. 
	 * @param  row  The row of the square
	 * @param  col  The column of the square
	 * @param  num  The potential value that will be placed in the square
	 * @return true if the placement is valid, false if it is not
	 */
	public boolean solvePuzzle(int row, int col) {
		
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
		if(puzzle[row][col] == 0) {
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
					if(solvePuzzle(newRow, newCol)) {
						return true;
					}
				}
			}
			// Resets
			puzzle[row][col] = 0;
			return false;
		}
		else {
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
			if(solvePuzzle(newRow, newCol)) {
				return true;
			}
			return false;
		}
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
}
