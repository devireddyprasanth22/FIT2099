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

    abstract public boolean isPlayerInAttackRange(Location currentLocation);

    abstract public void tick(Location currentLocation);
}
