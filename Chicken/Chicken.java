import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;

public class Chicken extends Critter {
	private int steps;
	private final double DARKENING_FACTOR = 0.2;
	
	public Chicken() {
		setColor(Color.WHITE);
		steps = 0;
	}
	
	public void act() {
		if (getGrid() == null) {
			return;
        }
        if(steps <= 200) {
			Location initialLoc = getLocation();
			ArrayList<Location> moveLocs = getMoveLocations();
			Location loc = selectMoveLocation(moveLocs);
			makeMove(loc);
			if(initialLoc != getLocation()) {
				int r = (int) (Math.random() * 20); 
				if(r == 1) {
					Egg egg = new Egg();
					egg.putSelfInGrid(getGrid(), initialLoc);
				}
			}
		}
		else if(steps <= 280 && steps % 2 == 0) {
			ArrayList<Location> moveLocs = getMoveLocations();
			Location loc = selectMoveLocation(moveLocs);
			makeMove(loc);
		}
		else if(steps < 300 && steps % 4 == 0) {
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
			setColor(new Color(red, green, blue));
		}
		else if(steps == 300) {
			Location loc = getLocation();
			Tombstone tomb = new Tombstone();
			tomb.putSelfInGrid(getGrid(), loc);
		}
        steps++;
	}
	
	public ArrayList<Location> getMoveLocations() {
        return super.getMoveLocations();
    }
    
     public Location selectMoveLocation(ArrayList<Location> locs) {
		int r = (int) (Math.random() * (locs.size() + 8)); 
		if(r > locs.size()) {
			setDirection((r - locs.size()) * 45);
			return getLocation();
		}
		else {
			return super.selectMoveLocation(locs);
		}
     }
}
