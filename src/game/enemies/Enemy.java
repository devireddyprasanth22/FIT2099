package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.behaviour.Behaviour;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor {
    private int hp;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.REMOVE);
        this.addCapability(Status.ENEMY);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    @Override
    abstract public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

    @Override
    abstract public ActionList allowableActions(Actor otherActor, String direction, GameMap map);

    abstract  public Actor getPlayerObj(Location currentLocation);

    abstract public void tick(Location currentLocation);

    /**
     * currentLocation: location of Koopa on the map
     * returns: true or false if Mario is in attack range or not
     */
    public boolean isPlayerInAttackRange(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int k : xArr) {
            for (int i : yArr) {
                try {
                    Location newLocation = currentLocation.map().at(x + k, y + i);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().getDisplayChar() == 'm' || newLocation.getActor().getDisplayChar() == 'M') {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    //doing nothing
                }

            }
        }
        return false;
    }
}
