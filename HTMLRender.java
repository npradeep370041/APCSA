import java.util.Scanner;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Naithik Pradeep
 *  @since  November 16, 2023
 *	@version
 */
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array
	private int tokenIndex;

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	
	// HTMLUtilities used in Render
	private HTMLUtilities util;
	
	private boolean isParagraph;
	private enum textState { NONE, QUOTED, BOLD, ITALICIZED };
	private textState tState = textState.NONE;
	private enum headingState { NONE, HEADING1, HEADING2, HEADING3, HEADING4, HEADING5, HEADING6 };
	private headingState hState = headingState.NONE;
	private enum maxLineLength { INFINITE, L40, L50, L60, L80, L100, L120 };
	private maxLineLength length = maxLineLength.L80;
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		tokenIndex = 0;
		
		// Initialize Simple Browser
		//render = new SimpleHtmlRenderer();
		//browser = render.getHtmlPrinter();
		
		//Initialize HTMLUtilites
		util = new HTMLUtilities();
		
		isParagraph = false;
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		hf.readFile(args);
		hf.run();
	}
	
	public void readFile(String[] args) {
		Scanner input = null;
		String fileName = "";
		// if the command line contains the file name, then store it
		if (args.length > 0)
			fileName = args[0];
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		
		// Open the HTML file
		input = FileUtils.openToRead(fileName);
		
		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			String [] lineTokens = util.tokenizeHTMLString(line);
			for(int i = 0; i < lineTokens.length; i++) {
				tokens[tokenIndex + i] = lineTokens[i];
			}
			tokenIndex += lineTokens.length;
		}
		input.close();
	}
	
	public void run() {
		for(int i = 0; i < TOKENS_SIZE; i++) {
			if(tokens[i] != null) {
				if(tokens[i].trim().equalsIgnoreCase("<p>")) {
					isParagraph = true;
				}
				if(tokens[i].trim().equalsIgnoreCase("<q>")) {
					tState = textState.QUOTED;
					length = maxLineLength.L80;
				}
				else if(tokens[i].trim().equalsIgnoreCase("<b>")) {
					tState = textState.BOLD;
					length = maxLineLength.L80;
				}
				else if(token[i].trim().equalsIgnoreCase("<i>")) {
					tState = textState.ITALICIZED;
					length = maxLineLength.L80;
				}
				if(token[i].trim().equalsIgnoreCase("<h1>")) {
					hState = headingState.HEADING1;
					length = maxLineLength.L40;
				}
				else if(token[i].trim().equalsIgnoreCase("<h2>")) {
					hState = headingState.HEADING2;
					length = maxLineLength.L50;
				}
				else if(token[i].trim().equalsIgnoreCase("<h3")) {
					hState = headingState.HEADING3;
					length = maxLineLength.L60;
				}
				else if(token[i].trim().equalsIgnoreCase("<h4>")) {
					hState = headingState.HEADING4;
					length = maxLineLength.L80;
				}
				else if(token[i].trim().equalsIgnoreCase("<h5>")) {
					hState = headingState.HEADING5;
					length = maxLineLength.L100;
				}
				else if(toke[i].trim().equalsIgnoreCase("<h6>")) {
					hState = headingState.HEADING6;
					length = maxLineLength.L120;
				}
			}
		}
	}
	
	
	
	
	
	
	/* public void run() {
		// Sample renderings from HtmlPrinter class
		
		// Print plain text without line feed at end
		browser.print("First line");
		
		// Print line feed
		browser.println();
		
		// Print bold words and plain space without line feed at end
		browser.printBold("bold words");
		browser.print(" ");
		
		// Print italic words without line feed at end
		browser.printItalic("italic words");
		
		// Print horizontal rule across window (includes line feed before and after)
		browser.printHorizontalRule();
		
		// Print words, then line feed (printBreak)
		browser.print("A couple of words");
		browser.printBreak();
		browser.printBreak();
		
		// Print a double quote
		browser.print("\"");
		
		// Print Headings 1 through 6 (Largest to smallest)
		browser.printHeading1("Heading1");
		browser.printHeading2("Heading2");
		browser.printHeading3("Heading3");
		browser.printHeading4("Heading4");
		browser.printHeading5("Heading5");
		browser.printHeading6("Heading6");
		
		// Print pre-formatted text (optional)
		browser.printPreformattedText("Preformat Monospace\tfont");
		browser.printBreak();
		browser.print("The end");
		
	}
	
	*/
}

