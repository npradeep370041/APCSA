/**
 *	Utilities for handling HTML
 *
 *	@author	Naithik Pradeep
 *	@since	October 31, 2023
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		int i = 0;
		int resultIndex = 0;
		boolean isInsideAngledBrackets = false;
		while(i < str.length()) {
			if(str.charAt(i) == '<') {
				isInsideAngledBrackets = true;
			}
			if(isInsideAngledBrackets) {
				if(result[resultIndex] == null) {
					result[resultIndex] = "" + str.charAt(i);
				}
				else {
					result[resultIndex] += str.charAt(i);
				}
				if(str.charAt(i) == '>') {
					isInsideAngledBrackets = false;
					resultIndex++;
				}
				
			}
			else {
				if(str.charAt(i) == ' ') {
					resultIndex++;
				}
				if(this.isPunctuation(str.charAt(i)) != 0) {
					resultIndex++;
					result[resultIndex] = "" + str.charAt(i);
					resultIndex++;
				}
				if(str.charAt(i) != ' ' && !this.isPunctuation(str.charAt(i))) {
					if(result[resultIndex] == null) {
						result[resultIndex] = "" + str.charAt(i);
					}
					else {
						result[resultIndex] += str.charAt(i);
					}
				}
			}
			i++;			
		}
		int correctIndex = 0;
		for(int j = 0; j < result.length; j++) {
			if(result[j] != null) {
				correctIndex++;
			}
		}
		String[] trueResult = new String[correctIndex];
		for(int k = 0; k < correctIndex; k++) {
			trueResult[k] = result[k];
		}
		// return the correctly sized array
		return trueResult;
	}

	public int isPunctuation(char c) {
		char[] punctuations = {'.', ',', ';', ':', '(', ')', '?', '!', '=', '&', '~', '+', '-'};
		for(int i = 0; i < punctuations.length; i++) {
			else if(c == punctuations[i]) {
				if(i == punctuations.length - 1) {
					return 2;
				}
				else {
					return 1;
				}
			}
		}
		return 0;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}
}
