// imports go here
import java.util.Scanner;
/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	
 *	@since	
 */
public class MVCipher {
	
	// fields go here
	private static char[] keyLetters;	
	/** Constructor */
	public MVCipher() {

	}
		
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
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
		
		
		/* Prompt for an output file name */
		String outputFile = Prompt.getString("Name of output file");
		
		/* Read input file, encrypt or decrypt, and print to output file */
		if(crypt == 1) {
			System.out.println("The encrypted file " + outputFile + " has been created using the keyword -> " + key);
			encrypt(inputFile, outputFile);
		}
		else {
			System.out.println("The decrypted file " + outputFile + " has been created using the keyword -> " + key);
		}
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
	public static void encrypt(String inputFile, String outputFile) {
		Scanner readFile = FileUtils.openToRead(inputFile);
		int numLetters = 0;
		while(readFile.hasNext()) {
			String line = readFile.nextLine();
			for(int i = 0; i < line.length(); i++) {
				char character = line.charAt(i);
				if((character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z')) {
					character = (char)(character + keyLetters[numLetters] - 64);
					numLetters++;
					if(numLetters >= keyLetters.length) {
						numLetters = 0;
					}
				}
				System.out.print(character);
				
			}
			System.out.print("\n");
		}
	}
	public static void decrypt() {
		
	}
}
