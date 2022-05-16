package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class Bowser extends Enemy{
    /**
     * Constructor.
     *
     */
    public Bowser() {
        super("bowser", 'B', 500);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return null;
    }

    @Override
    public Actor getPlayerObj(Location currentLocation) {
        return null;
    }

    @Override
    public void tick(Location currentLocation) {

    }
}
