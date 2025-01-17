package game.resetAction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Status;
import game.groundItems.Dirt;

import java.util.Random;

public class Reset extends Action {

    private final Player actor;
    private final GameMap map;

    /**
     * Constructor for Reset
     *
     * @param actor mario
     * @param map
     */
    public Reset(Player actor, GameMap map) {
        this.map = map;
        this.actor = actor;

    }

    /**
     * This is the method that executes the actual reset.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return null
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.actor.setHasReset(true);
        this.actor.resetMaxHp(100);
        for (int i = 0; i < this.map.getXRange().max(); i++) {
            for (int j = 0; j < this.map.getYRange().max(); j++) {
                if (this.map.isAnActorAt(new Location(this.map, i, j))) {
                    Actor currActor = this.map.getActorAt(new Location(this.map, i, j));
                    // Remove enemies
                    if (currActor.hasCapability(Status.REMOVE)) {
                        this.map.removeActor(currActor);
                    }
                }
                Location currGround = this.map.at(i, j);
                // Removes all coins 
                for (Item item : currGround.getItems()) {
                    if (item.hasCapability(Status.REMOVE)) {
                        currGround.removeItem(item);
                        currGround.setGround(new Dirt());
                        break;
                    }

                }

                // Removes trees with 50% chance
                if (currGround.getGround().hasCapability(Status.IS_RESETTABLE)) {
                    if (new Random().nextInt(100) < 50) {
                        currGround.setGround(new Dirt());
                    }
                }
            }
        }
        return null;

    }

    /**
     * The menu description
     *
     * @param actor The actor performing the action.
     * @return The string for menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset game";
    }
}
