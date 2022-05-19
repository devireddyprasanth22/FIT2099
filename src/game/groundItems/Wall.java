package game.groundItems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Wall extends Ground {

	public Wall() {
		super('#');
		this.addCapability(Status.WALL);
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	public ActionList allowableActions(Actor actor, Location location, String direction){
		return new ActionList();
	}
}
