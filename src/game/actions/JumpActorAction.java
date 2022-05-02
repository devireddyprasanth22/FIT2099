package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class JumpActorAction extends Action {
    /**
     * Target location
     */
    protected Location moveToLocation;
    /**
     * One of the 8-d navigation
     */
    protected String direction;
    /**
     * Or the command key
     */
    protected String hotKey;

    public JumpActorAction(Location moveToLocation, String direction, String hotKey) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.hotKey = hotKey;
        System.out.println("Construccted");
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, moveToLocation);
        return this.menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction;
    }
}
