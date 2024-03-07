import java.util.List;		// used by expression evaluator

/**
 *	<Description goes here>
 *
 *	@author	
 *	@since	
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack
	
	private List<Identifier> identifiers;

	// constructor	
	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		identifiers = new ArrayList<Identifier>();
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() {
		while(true) {
			String expression = Prompt.getString("");
			if(expression.equals("q")) {
				return;
			}
			if(expression.equals("h")) {
				this.printHelp();
			}
			else {
				List<String> tokens = utils.tokenizeExpression(expression);
				double answer = this.evaluateExpression(tokens);
				System.out.println(answer);
			}
		}
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	public void initializeIdentifiers() {
		Identifier pi = new Identifier();
		pi.setName("pi");
		pi.setValue(Math.PI);
		Identifier e = new Identifier();
		e.setName("e");
		e.setValue(Math.E);
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		boolean isVariableExpression = false;
		for(String token : tokens) {
			try {
				valueStack.push(Double.parseDouble(token));
			}
			catch(NumberFormatException e) {
				if(utils.isOperator()) { 
					if(!operatorStack.isEmpty()) {
						if(this.hasPrecedence(operatorStack.peek(), token)) {
							operatorStack.push(token);
						}
						else {
							boolean checker = true;
							while(!operatorStack.isEmpty() && this.hasPrecedence(token, operatorStack.peek()) && checker) {
								if(!operatorStack.isEmpty() && operatorStack.peek().equals("+")) {
									double temp = valueStack.pop();
									double newVal = valueStack.pop() + temp;
									valueStack.push(newVal);
									operatorStack.pop();
								}
								if(!operatorStack.isEmpty() && operatorStack.peek().equals("-")) {
									double temp = valueStack.pop();
									double newVal = valueStack.pop() - temp;
									valueStack.push(newVal);
									operatorStack.pop();
								}
								if(!operatorStack.isEmpty() && operatorStack.peek().equals("*")) {
									double temp = valueStack.pop();
									double newVal = valueStack.pop() * temp;
									valueStack.push(newVal);
									operatorStack.pop();
								}
								if(!operatorStack.isEmpty() && operatorStack.peek().equals("/")) {
									double temp = valueStack.pop();
									double newVal = valueStack.pop() / temp;
									valueStack.push(newVal);
									operatorStack.pop();
								}
								if(!operatorStack.isEmpty() && operatorStack.peek().equals("^")) {
									double temp = valueStack.pop();
									double newVal = Math.pow(valueStack.pop(), temp);
									valueStack.push(newVal);
									operatorStack.pop();
								}
								if(!operatorStack.isEmpty() && operatorStack.peek().equals("%")) {
									double temp = valueStack.pop();
									double newVal = valueStack.pop() % temp;
									valueStack.push(newVal);
									operatorStack.pop();
								}
								if(!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
									operatorStack.pop();
									checker = false;
								}
							}
							if(!token.equals(")")) {
								operatorStack.push(token);	
							}
						}
					}
					else {
						operatorStack.push(token);
					}
				}
				else {
					isVariableExpression = true;
					int index = -1;
					for(int i = 0; i < identifiers.size(); i++) {
						if(identifiers[i].getName().equals(token)) {
							index = i;
						}
					}
					if(index == -1) {
						identifiers.add(new Identifier());
						identifiers[identifiers.size() - 1].setName(token);
					}
					else {
						valueStack.push(identifiers[index].getValue());
					}
				}	
			}
			if(!isVariableExpression) {
				while(!operatorStack.isEmpty()) {
					if(!operatorStack.isEmpty() && operatorStack.peek().equals("+")) {
						double temp = valueStack.pop();
						double newVal = valueStack.pop() + temp;
						valueStack.push(newVal);
						operatorStack.pop();
					}
					if(!operatorStack.isEmpty() && operatorStack.peek().equals("-")) {
						double temp = valueStack.pop();
						double newVal = valueStack.pop() - temp;
						valueStack.push(newVal);
						operatorStack.pop();
					}
					if(!operatorStack.isEmpty() && operatorStack.peek().equals("*")) {
						double temp = valueStack.pop();
						double newVal = valueStack.pop() * temp;
						valueStack.push(newVal);
						operatorStack.pop();
					}
					if(!operatorStack.isEmpty() && operatorStack.peek().equals("/")) {
						double temp = valueStack.pop();
						double newVal = valueStack.pop() / temp;
						valueStack.push(newVal);
						operatorStack.pop();
					}
					if(!operatorStack.isEmpty() && operatorStack.peek().equals("^")) {
						double temp = valueStack.pop();
						double newVal = Math.pow(valueStack.pop(), temp);
						valueStack.push(newVal);
						operatorStack.pop();
					}
					if(!operatorStack.isEmpty() && operatorStack.peek().equals("%")) {
						double temp = valueStack.pop();
						double newVal = valueStack.pop() % temp;
						valueStack.push(newVal);
						operatorStack.pop();
					}
					if(!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
						operatorStack.pop();
					}
					if(!operatorStack.isEmpty() && operatorStack.peek().equals(")")) {
						operatorStack.pop();
					}
				}
				return valueStack.peek();	
			}	
		}
	}
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) {
		if (op1.equals("^") && !op2.equals("(")) return false;
		if (op2.equals("(")) return true;
		if (op2.equals(")")) return false;
		if (op1.equals("(")) return true;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		if (op1.equals("-") && op2.equals("+")) return false;
		if (op1.equals("/") && op2.equals("*")) return false;
		return true;
	}
	 
}
