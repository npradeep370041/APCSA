import info.gridworld.actor.Actor;

public class Tombstone extends Actor {	
	private int lifetime;
	
	public Tombstone() {
		lifetime = 20;
	}
	
	public void act() {
		lifetime--;
		if(lifetime == 0) {
			removeSelfFromGrid();
		}
	}
}
