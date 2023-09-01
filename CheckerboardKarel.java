/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment Karel4.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run() {
		putBeeper();
		while(leftIsClear()) {
			if(frontIsClear()) {
				move();
				if(frontIsClear()) {
					move();
					putBeeper();
				}
			}
			else {
				if(facingEast()) {
					turnLeft();
					if(frontIsClear() && beepersPresent()) {
						move();
						turnLeft();
						if(frontIsClear()) {
							move();
							putBeeper();
						}
					}
					else if(frontIsClear() && noBeepersPresent()) {
						if(frontIsClear()) {
							move();
							putBeeper();
						}
						turnLeft();
					}
				}
				else if(facingWest() && rightIsClear()) {
					turnRight();
					if(frontIsClear()) {
						move();
						putBeeper();
					}
					turnRight();
				}
				else {
					turnRight();
				}
			}
		}
		while(facingEast() && leftIsBlocked() && frontIsClear()) {
			if(beepersPresent()) {
				move();
				if(frontIsClear()) {
					move();
					putBeeper();
				}
			}
			else {
				if(frontIsClear()) {
					move();
					putBeeper();
				}
			}
		}
	}
}
