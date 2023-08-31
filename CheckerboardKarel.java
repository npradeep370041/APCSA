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
		while(leftIsClear() || frontIsClear()) {
			if(frontIsClear()) {
				turnAround();
				if(frontIsBlocked() && leftIsClear()) {
					turnLeft();
					move();
					if(beepersPresent()) {
						turnAround();
						move();
						turnRight();
						move();
					}
					else {
						turnAround();
						move();
						turnRight();
						putBeeper();
						move();
					}
				}
				else if(frontIsBlocked()) {
					turnAround();
					move();
					if(beepersPresent()) {
						turnAround();
						move();
					}
					else {
						turnAround();
						move();
						putBeeper();
					}
					if(frontIsClear()){
						move();
					}
				}
			}
			else {
				turnAround();
				while(frontIsClear()) {
					move();
				}
				turnRight();
				move();
				turnRight();
			}
		}		
	}
}
