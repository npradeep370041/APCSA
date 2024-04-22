import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;

public class Fox extends Critter {
	private boolean isAsleep;
	private int steps;
	
	public Fox() {
		setColor(null);
		isAsleep = false;
		steps = 0;
	}
	
	public void act() {
		if(this.getGrid() == null) {
			return;
		}
		if(!isAsleep && steps < 20) {
			ArrayList<Location> moveLocs = getMoveLocations();
			Location loc = selectMoveLocation(moveLocs);
			makeMove(loc);
			ArrayList<Location> actors = this.getGrid().getOccupiedAdjacentLocations(getLocation());
			for(int randomizer = 0; randomizer < actors.size(); randomizer++) {
				int randomIndexToSwap = (int)(Math.random() * actors.size());
				Location temporary = actors.get(randomIndexToSwap);
				actors.set(randomIndexToSwap, actors.get(randomizer));
				actors.set(randomizer, temporary);
			}
			boolean notEaten = true;
			for(int i = 0; i < actors.size(); i++) {
				if(getGrid().get(actors.get(i)) instanceof Chicken && notEaten) {
					Tombstone tomb = new Tombstone();
					tomb.putSelfInGrid(getGrid(), actors.get(i));
					isAsleep = true;
					steps = 0;
					notEaten = false;
				}
			}
		}
		else if(!isAsleep && steps == 20) {
			Location loc = getLocation();
			Tombstone tomb = new Tombstone();
			tomb.putSelfInGrid(getGrid(), loc);
			return;
		}
		if(isAsleep && steps == 10) {
			isAsleep = false;
			steps = 0;
		}
		
		steps++;
	}
	
	public ArrayList<Location> getMoveLocations() {
		return super.getMoveLocations();
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs) {
		double minDist = 1000000;
		int index = -1;
		ArrayList<Location> actors = getGrid().getOccupiedLocations();
		for(int randomizer = 0; randomizer < actors.size(); randomizer++) {
			int randomIndexToSwap = (int)(Math.random() * actors.size());
			Location temporary = actors.get(randomIndexToSwap);
			actors.set(randomIndexToSwap, actors.get(randomizer));
			actors.set(randomizer, temporary);
		}
		for(int i = 0; i < actors.size(); i++) {
			if(getGrid().get(actors.get(i)) instanceof Chicken) {
				double dist = getDistance(getLocation(), actors.get(i));
				if(minDist > dist) {
					minDist = dist;
					index = i;
				}
			}
		}
		if(index == -1) {
			return getLocation();
		}
		setDirection(getLocation().getDirectionToward(actors.get(index)));
		for(int j = 0; j < locs.size(); j++) {
			if(getLocation().getDirectionToward(locs.get(j)) == getDirection()) {
				return locs.get(j);
			}
		}
		return super.selectMoveLocation(locs);
	}
	
	private double getDistance(Location loc1, Location loc2) {
		return Math.sqrt(Math.pow((loc2.getRow() - loc1.getRow()), 2) 
			+ Math.pow((loc2.getCol() - loc1.getCol()), 2));
	}
	
	public void makeMove(Location loc) {
		if(getLocation() == loc) {
			ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(getLocation());
			int r = (int) (Math.random() * locs.size()); 
			if(locs.size() == 0) {
				return;
			}
			moveTo(locs.get(r));
		}
		else {
			moveTo(loc);
		}
	}
}
