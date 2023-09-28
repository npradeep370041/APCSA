// imports go here
import java.util.Scanner;
import java.io.PrintWriter;
/**
 *	MVCipher - A keyword is entered that is only letters and is more 
 *  than 3 letters and is used to encrypt or decrypt certain files
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Naithik Pradeep
 *	@since	September 21, 2023
 */
public class MVCipher {
	
	// fields go here
	
	// Letters of the key
	private char[] keyLetters;
	
	// ASCII Value of 64 used to get encrypt or decrypt
	private final int ASCII_VALUE_64 = 64;
	
	// Number of letters in the alphabet used to loop around when encrypting or decrypting
	private final int NUMBER_OF_LETTERS = 26;
	
	// Letters used to wrap around the ASCII Alphabet
	private final int LOWERCASE_A = 97;
	private final int UPPERCASE_A = 65;
	private final int LOWERCASE_Z = 122;
	private final int UPPERCASE_Z = 90;
	
	/** Constructor */
	public MVCipher() {

	}
		
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Runs the Cipher Machine
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		String key = "";
		boolean foundKey = false;
		while(!foundKey) {
			String input = Prompt.getString("Please input a word to use as key (letters only)");
			if(input.length() >= 3) {
				boolean failed = false;
				for(int i = 0; i < input.length(); i++) {
					char character = input.charAt(i);
					if((!(character >= 'A' && character <= 'Z')) && (!(character >= 'a' && character <= 'z'))) {
						failed = true;
						foundKey = false;
					}
				}
				if(!failed) {
					foundKey = true;
				}
			}
			else {
				foundKey = false;
			}
			if(!foundKey) {
				System.out.println("ERROR: Key must be all letters and at least 3 characters long");
			}	
			else {
				key = input.toUpperCase();
				keyLetters = key.toCharArray();
				
			}
		}
		
		/* Prompt for encrypt or decrypt */
		int crypt = Prompt.getInt("Encrypt or decrypt?", 1, 2);
			
		/* Prompt for an input file name */
		String inputFile = "";
		if(crypt == 1) {
			inputFile = Prompt.getString("Name of file to encrypt");
		}
		else {
			inputFile = Prompt.getString("Name of file to decrypt");
		}
		
		/* Checks to make sure the file exists using a scanner
		 * Closes the file after checking */
		Scanner checker = FileUtils.openToRead(inputFile);
		checker.close();
		
		/* Prompt for an output file name */
		String outputFile = Prompt.getString("Name of output file");
		
		/* Read input file, encrypt or decrypt, and print to output file */
		if(crypt == 1) {
			System.out.println("The encrypted file " + outputFile + " has been created using the keyword -> " + key);
			this.encrypt(inputFile, outputFile);
		}
		else {
			System.out.println("The decrypted file " + outputFile + " has been created using the keyword -> " + key);
			this.decrypt(inputFile, outputFile);
		}
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
	/**
	 * This method encrypts the file using the keyword
	 * @param inputFile  name of the file to read
	 * @param outputFile name of the file to write
	 */
	public void encrypt(String inputFile, String outputFile) {
		Scanner readFile = FileUtils.openToRead(inputFile);
		PrintWriter writeFile = FileUtils.openToWrite(outputFile);
		int numLetters = 0;
		while(readFile.hasNext()) {
			String line = readFile.nextLine();
			for(int i = 0; i < line.length(); i++) {
				char character = line.charAt(i);
				
				// Checking if the character is uppercase
				if(character >= 'A' && character <= 'Z')  {
					character = (char)(character + keyLetters[numLetters] - ASCII_VALUE_64);
					if(character > UPPERCASE_Z) {
						character = (char)(character - NUMBER_OF_LETTERS);
					}
					numLetters++;
					
					// Resets the letter of the keyword that is being used to encrypt
					if(numLetters >= keyLetters.length) {
						numLetters = 0;
					}
				}
				
				// Checking if the character is lowercase
				if(character >= 'a' && character <= 'z') {
					character = (char)(character + keyLetters[numLetters] - ASCII_VALUE_64);
					if(character > LOWERCASE_Z) {
						character = (char)(character - NUMBER_OF_LETTERS);
					}
					numLetters++;
					
					// Resets the letter of the keyword that is being used to encrypt
					if(numLetters >= keyLetters.length) {
						numLetters = 0;
					}
				}
				
				// Adds the character to the file
				writeFile.print(character);
			}
			
			// Adds next line
			writeFile.print("\n");
		}
		
		// Closes the file
		writeFile.close();
	}
	
	/**
	 * This method decrypts the file using the keyword
	 * @param inputFile  name of the file to read
	 * @param outputFile name of the file to write
	 */
	public void decrypt(String inputFile, String outputFile) {
		Scanner readFile = FileUtils.openToRead(inputFile);
		PrintWriter writeFile = FileUtils.openToWrite(outputFile);
		int numLetters = 0;
		while(readFile.hasNext()) {
			String line = readFile.nextLine();
			for(int i = 0; i < line.length(); i++) {
				char character = line.charAt(i);
				if(character >= 'A' && character <= 'Z') {
					character = (char)(character - keyLetters[numLetters] + ASCII_VALUE_64);
					if(character < UPPERCASE_A) {
						character = (char)(character + NUMBER_OF_LETTERS);
					}
					numLetters++;
					if(numLetters >= keyLetters.length) {
						numLetters = 0;
					}
				}
				if(character >= 'a' && character <= 'z') {
					character = (char)(character - keyLetters[numLetters] + ASCII_VALUE_64);
					if(character < LOWERCASE_A) {
						character = (char)(character + NUMBER_OF_LETTERS);
					}
					numLetters++;
					if(numLetters >= keyLetters.length) {
						numLetters = 0;
					}
				}
				writeFile.print(character);
			}
			writeFile.print("\n");
		}
		writeFile.close();
	}
}
