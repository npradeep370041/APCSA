/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass should solve the "repair the quad"
 * problem from Assignment 1.  
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while(frontIsClear() || leftIsClear()) {
			turnLeft();
			while(frontIsClear()) {
				if(beepersPresent()) {
					move();
				}
				else {
					putBeeper();
					move();
				}
			}
			if(noBeepersPresent()) {
				putBeeper();
			}
			turnAround();
			if(leftIsClear()) {
				while(frontIsClear()) {
					move();
				}
				turnLeft();
				move();
				move();
				move();
				move();
			}
			else {
				break;
			}
		}
	}
	
}
