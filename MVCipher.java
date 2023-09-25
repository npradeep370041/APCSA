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
		
	/** Constructor */
	public MVCipher() { }
	
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
			}
		}
		
		/* Prompt for encrypt or decrypt */
		int crypt = Prompt.getInt("Encrypt or decrypt?", 1, 2);
			
		/* Prompt for an input file name */
		String fileName = "";
		if(crypt == 1) {
			fileName = Prompt.getString("Name of file to encrypt");
		}
		else {
			fileName = Prompt.getString("Name of file to decrypt");
		}
		Scanner inputFile = FileUtils.openToRead(fileName);
		
		
		/* Prompt for an output file name */
		String outputFile = Prompt.getString("Name of output file");
		
		/* Read input file, encrypt or decrypt, and print to output file */
		if(crypt == 1) {
			System.out.println("The encrypted file " + outputFile + " has been created using the keyword -> " + key);
		}
		else {
			System.out.println("The decrypted file " + outputFile + " has been created using the keyword -> " + key);
		}
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
	public static void encrypt() {
		
	}
	public static void decrypt() {
		
	}
}
