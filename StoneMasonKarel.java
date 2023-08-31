/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
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
