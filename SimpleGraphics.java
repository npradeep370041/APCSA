/**
 *	SimpleGraphics.java
 *
 *	To compile Linux:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute Linux:	java -cp .:acm.jar SimpleGraphics
 *	To compile MS Powershell:	javac -cp ".;acm.jar" SimpleGraphics.java
 *	To execute MS Powershell:	java -cp ".;acm.jar" SimpleGraphics
 *
 *	@author	Naithik Pradeep
 *	@since	September 19, 2023
 */
 
/*	All package classes should be imported before the class definition.
 *	"java.awt.Color" means package java.awt contains class Color. */
import java.awt.Color;

/*	The following libraries are in the acm.jar file. */
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class SimpleGraphics extends GraphicsProgram {
	
	/*	All fields and constants should be declared here.
	 *	Only constants (final) are initialized here. */
	private GOval circle;
	private double radius = 156;
	
	private GRect rectangle;
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() {

	}
	
	/**	The run() method is executed after init().
	 *	The bulk of the program should be performed here.
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	public void run() {
		// target
		boolean isRed = true;
		for(int i = 0; i < 5;  i++) {
			circle = new GOval(221 + i * 24, 313 + i * 24, radius * 2, radius * 2);
			circle.setFilled(true);
			if(isRed == true) {
				circle.setFillColor(Color.RED);
				isRed = false;
			} 
			else {
				circle.setFillColor(Color.WHITE);
				isRed = true;
			}
			add(circle);
			radius = radius - 24;
			
		}
		
		// bricks
		/* (int j = 0; j < 55; j++) {
			rectangle = new GRect(127 + j * 50, 0 + j * 20, 20, 50);
		}
		add(rectangle); */
		
		rectangle = new GRect(50, 100, 100, 100);
		add(rectangle);
		
		
	}
}

