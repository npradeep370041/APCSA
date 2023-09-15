import java.util.Scanner;
/**
 *	Counts the frequency of letters in a file and produces a histogram.
 *
 *	@author	Naithik Pradeep
 *	@since	September 14, 2023
 */
public class LetterCount {
	
	// Fields go here, all must be private
	private int[] letterCount;
	/* Constructor */							
	public LetterCount() {
		letterCount = new int[26];
		for(int init = 0; init < 26; init++) {
			letterCount[init] = 0;
		}
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
		
		// Reading File and filling out letter array
		Scanner inFile = FileUtils.openToRead(fileName);
		while(inFile.hasNext()) {
			String str = inFile.nextLine().toLowerCase();
			for(int i = 0; i < str.length(); i++) {
				int letter = str.charAt(i);
				if(letter >= 97 && letter <= 122) {
					letterCount[letter - 97]++;
				}
			}
		}
		
		// Finding the maximum number of a letter and finding the value of a plus
		double max = 0.0;
		for(int counter = 0; counter < 26; counter++) {
			if(max < letterCount[counter]) {
				max = letterCount[counter];
			}
		}
		double plusValue = max/60.0;
		
		// Printing out the histogram
		System.out.println("\nHistogram of letter frequency in " + fileName + "\n");
		for(int letterData = 0; letterData < 26; letterData++) {
			System.out.print((char)(letterData + 97) + ":   ");
			System.out.printf("%4s", letterCount[letterData]);
			System.out.print(" ");
			int numPlus = (int)(letterCount[letterData]/plusValue);
			for(int addPlus = 0; addPlus < numPlus; addPlus++) {
				System.out.print("+");
			}
			System.out.print("\n");
		}		
	}
}
