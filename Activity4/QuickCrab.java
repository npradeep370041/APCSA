import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
	
	private void addMoveLocations(int[] directions, ArrayList<Location> locs) {
        Grid gr = getGrid();
        Location loc = getLocation();
        for(int i = 0; i < directions.length; i++) {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + directions[i]);
            if (gr.isValid(neighborLoc)) {
				Location twoNeighborLoc = neighborLoc.getAdjacentLocation(getDirection() + directions[i]);
                if(gr.isValid(twoNeighborLoc)) {
					locs.add(twoNeighborLoc);
				}
			}
        }
	}
	
	public void getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();
		int[] directions = {Location.LEFT, Location.RIGHT };
		addMoveLocations(directions, locs);
		if(locs.size > 0) {
			return locs;
		}
		return super.getMoveLocations();
		
	} 
}
