public class KingCrab extends CrabCritter {
	public void processActors(ArrayList<Actor> actors) {
		for(Actor actor : actors) {
			if(!moveActor(actor)) {
				actor.removeSelfFromGrid();
			}
		}
	}
	
	private boolean moveActor(Actor actor) {
		ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(actor.getLocation());
		for(int i = 0; i < locs.size(); i++) {
			if(distance(locs.get(i), getLocation()) > 1) {
				actor.moveTo(locs.get(i));
				return true;
			}
		}
		return false;
	}
	
	private int distance(Location loc1, Location loc2) {
		int r1 = loc1.getRow();
		int r2 = loc2.getRow();
		int c1 = loc1.getCol();
		int c2 = loc2.getCol();
		return (int)(Math.round(Math.sqrt(Math.pow((r2 - r1), 2) + Math.pow((c2 - c1), 2))));
	}
}
