package game.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.actions.AttackAction;
import game.behaviour.Behaviour;
import game.behaviour.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * A little fungus guy.
 */
public class Goomba extends Actor {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    private int hp;


    /**
     * Constructor.
     */
    public Goomba() {
        super("Goomba", 'g', 20);
        this.behaviours.put(10, new WanderBehaviour());
        this.addCapability(Status.REMOVE);
        this.addCapability(Status.ENEMY);

    }

    /**
     * currentLocation is location of object on the map
     */
    public void tick(Location currentLocation) {
        int r1 = (int) (Math.random() * (11 - 1) + 1);
        System.out.println(r1);
        //suicide mechanic
        if (r1 == 10 || this.getHp() < 0) {
            currentLocation.map().removeActor(this);
        }
        //attack mechanic
        else if (isPlayerInAttackRange(currentLocation)) {
            //goomba is in attack range
            if (r1 > 5) {
                //Goomba attacks player
                System.out.println("Goomba attacks player");
                getPlayerObj(currentLocation).hurt(10);
                Player player = (Player) getPlayerObj(currentLocation);
                player.hurt(10);
                player.deactivateSuperMushroom();
            } else {
                //goomba misses player
                System.out.println("Goomba misses player");
            }
        }
    }

    /**
     * returns hp of Goomba
     */
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * currentLocation: location of Goomba on the map
     * returns: true or false if Mario is in attack range or not
     */
    public boolean isPlayerInAttackRange(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int i = 0; i < xArr.length; i++) {
            for (int j = 0; j < yArr.length; j++) {
                try {
                    Location newLocation = currentLocation.map().at(x + xArr[i], y + yArr[j]);
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

    /**
     * currentLocation: current location of Goomba object
     * returns: Actor object in range of player. If not in range, return null
     */
    public Actor getPlayerObj(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int i = 0; i < xArr.length; i++) {
            for (int j = 0; j < yArr.length; j++) {
                try {
                    Location newLocation = currentLocation.map().at(x + xArr[i], y + yArr[j]);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().getDisplayChar() == 'm' || newLocation.getActor().getDisplayChar() == 'M') {
                            return newLocation.getActor();
                        }
                    }
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
        return null;
    }


    /**
     * otherActor: Actor around the player
     * direction: Direction in which otherActor is relative to Goomba
     * map: current GameMap object
     * <p>
     * returns: An ActionList object of actions that the other player can do to Goomba
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * actions: ActionList of allowable actions of Goomba
     * lastAction: Previous action of Goomba
     * map: current GameMap
     * display: current Display object
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     * returns: current action of Goomba. If none, returns DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Location location = map.locationOf(this);
        tick(location);
        for (Behaviour Behaviour : behaviours.values()) {
            if (location.map().locationOf(this) != null) {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

}
