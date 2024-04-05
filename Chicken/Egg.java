import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;

public class Egg extends Actor {
	private int steps;
	private final double DARKENING_FACTOR = 0.05;
	
	public Egg() {
		setColor(Color.WHITE);
		steps = 0;
	}
	
	public void act() {
		if(steps < 45) {
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
			setColor(new Color(red, green, blue));
		}
		else {
			setColor(Color.RED);
		}
		if(steps == 50) {
			Location loc = getLocation();
			Chicken chicken = new Chicken();
			chicken.putSelfInGrid(getGrid(), loc);
		}
		steps++;
	}
}
