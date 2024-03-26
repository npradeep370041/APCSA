import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Critter;

public class BlusterCritter extends Critter {
	
	private int c;
	private final double CHANGE_FACTOR = 0.95;
	
	public BlusterCritter(int courage) {
		c = courage;
	}
	
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> critters = new ArrayList<Actor>();
		int firstRow = getLocation().getRow() - 2;
		if(firstRow < 0) {
			firstRow = 0;
		}
		int lastRow = getLocation().getRow() + 2;
		if(lastRow > getGrid().getNumRows() - 1) {
			lastRow = getGrid().getNumRows() - 1;
		}
		int firstCol = getLocation().getCol() - 2;
		if(firstCol < 0) {
			firstCol = 0;
		}
		int lastCol = getLocation().getCol() + 2;
		if(lastCol > getGrid().getNumCols() - 1) {
			lastCol = getGrid().getNumCols() - 1;
		}
		for(int i = firstRow; i <= lastRow; i++) {
			for(int j = firstCol; j <= lastCol; j++) {
				if(getGrid().get(new Location(i, j)) instanceof Critter) {
					critters.add(getGrid().get(new Location(i, j)));
				}
			}
		}
		return critters;				
	}
	
	public void processActors(ArrayList<Actor> actors) {
		double change = CHANGE_FACTOR;
		if (actors.size() < c) {
			change = 1/change;
		}
		Color col = getColor();
		int red = Math.min(255, (int) (col.getRed() * (change)));
        int green = Math.min(255, (int) (col.getGreen() * (change)));
        int blue = Math.min(255, (int) (col.getBlue() * (change)));
        setColor(new Color(red, green, blue));    
     }
}
