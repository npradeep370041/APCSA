public class Fox extends Critter {
	public Fox() {
		setColor(null);
	}
	
	public ArrayList<Location> getMoveLocations() {
		return super.getMoveLocations();
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs) {
		return super.selectMoveLocation(locs);
	}
	
	public void makeMove(Location loc) {
		if(getLocation() == loc) {
			int r = (int) (Math.random() * 8); 
			setDirection(r * 45);
		}
		else {
			moveTo(loc);
		}
	}
}
