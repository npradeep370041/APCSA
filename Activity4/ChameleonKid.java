import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter {
	public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) {
			setColor(Color.BLACK);
            return;
        }
        
        ArrayList<Actor> indices = new ArrayList<Actor>();
        
        for(int i = 0; i < actors.size(); i++) {
			if(getLocation().getDirectionToward(actors.get(i).getLocation()) == getDirection() || 
				getLocation().getDirectionToward(actors.get(i).getLocation()) == getDirection() - 180) {
				indices.add(actors.get(i));
			}
		}
		super.processActors(indices);
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        super.makeMove(loc);
    }
}
