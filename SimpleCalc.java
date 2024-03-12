import java.util.List;		// used by expression evaluator
import java.util.ArrayList;

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
		this.initializeIdentifiers();
		while(true) {
			String expression = Prompt.getString("");
			if(expression.equals("q")) {
				return;
			}
			if(expression.equals("h")) {
				this.printHelp();
			}
			else if(expression.equals("l")) {
				this.printList();
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
		System.out.println("  h - this message\n  q - quit\n  l - variables\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	public void printList() {
		System.out.println("Variables:");
		for(int i = 0; i < identifiers.size(); i++) {
			System.out.printf("%-20s = %s%n", "          " + identifiers.get(i).getName(),"          " + identifiers.get(i).getValue());  
		}
	}
	
	public void initializeIdentifiers() {
		identifiers.add(new Identifier());
		identifiers.get(0).setName("pi");
		identifiers.get(0).setValue(Math.PI);
		identifiers.add(new Identifier());
		identifiers.get(1).setName("e");
		identifiers.get(1).setValue(Math.E);
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		if(tokens.size() == 1) {
			try {
				return Double.parseDouble(tokens.get(0));
			}
			catch(NumberFormatException e) {
				for(int i = 0; i < identifiers.size(); i++) {
					if(identifiers.get(i).getName().equals(tokens.get(0))) {
						System.out.print(identifiers.get(i).getName() + " = ");
						return identifiers.get(i).getValue();
					}
				}
				identifiers.add(new Identifier());
				identifiers.get(identifiers.size() - 1).setName(tokens.get(0));
				identifiers.get(identifiers.size() - 1).setValue(0);
				System.out.print(identifiers.get(identifiers.size() - 1).getName() + " = ");
				return 0;
			}
		}
		boolean isVariableExpression = false;
		if(tokens.get(1).equals("=")) {
			isVariableExpression = true;
		}
		int counter = 0;
		for(String token : tokens) {
			if(isVariableExpression) {
				counter++;
			}
			if(counter > 2 || !isVariableExpression) {
				try {
					valueStack.push(Double.parseDouble(token));
				}
				catch(NumberFormatException e) {
					if(utils.isOperator(token.charAt(0))) { 
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
						for(int i = 0; i < identifiers.size(); i++) {
							if(identifiers.get(i).getName().equals(token)) {
								valueStack.push(identifiers.get(i).getValue());
							}
						}
					}	
				}		
			}
		}
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
		if(isVariableExpression) {
			int index = -1;
			for(int i = 0; i < identifiers.size(); i++) {
				if(identifiers.get(i).getName().equals(tokens.get(0))) {
					index = i;
				}
			}
			if(index == -1) {
				identifiers.add(new Identifier());
				identifiers.get(identifiers.size() - 1).setName(tokens.get(0));
				identifiers.get(identifiers.size() - 1).setValue(0);
			}
			for(int i = 0; i < identifiers.size(); i++) {
				if(identifiers.get(i).getName().equals(tokens.get(0))) {
					identifiers.get(i).setValue(valueStack.peek());
					System.out.print(identifiers.get(i).getName() + " = ");
				}
			}
		}
		return valueStack.peek();	
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
