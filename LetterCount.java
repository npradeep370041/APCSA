import java.util.Scanner;
/**
 *	Counts the frequency of letters in a file and produces a histogram.
 *
 *	@author	Naithik Pradeep
 *	@since	September 14, 2023
 */
public class LetterCount {
	
	// Fields go here, all must be private
	int[] letterCount = new int[26];
	/* Constructor */							
	public LetterCount() {}
		for(int init = 0; init < 26; init++) {
			letterCount[init] = 0;
		}
	/* Main routine */
	public static void main(String[] args) {
		LetterCount lc = new LetterCount();
		if (args.length != 1)
			System.out.println("Usage: java LetterCount <inputFile>");
		else
			lc.run(args);
	}
	
	/**	The core program of the class, it
	 *	- opens the input file
	 *	- reads the file and counts the letters
	 *	- closes the input file
	 *	- prints the histogram of the letter count
	 */
	public void run(String[] args) {
		String fileName = args[0];
		Scanner inFile = FileUtils.openToRead(fileName);
		while(inFile.hasNext()) {
			String str = inFile.nextLine().toLowerCase();
			for(int i = 0; i < str.length(); i++) {
				char letter = str.charAt(i);
				
	}
	
}
