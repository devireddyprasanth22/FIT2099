package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Status;
import game.actions.AttackAction;
import game.behaviour.Behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PiranhaPlant extends Enemy{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * Constructor.
     */
    public PiranhaPlant() {

        super("Piranha Plant", 'Y', 150);
        this.addCapability(Status.REMOVE);
        this.addCapability(Status.ENEMY);
    }
    @Override
    public void tick(Location currentLocation) {
        
    if(isPlayerInAttackRange(currentLocation) && chance(50)){
        System.out.println("Piranha Plant attacks player");
        getPlayerObj(currentLocation).hurt(90);
        Player player = (Player) getPlayerObj(currentLocation);
        player.hurt(90);
    }
    else {
        //Piranha Plant misses player
        System.out.println("Piranha Plant misses player");
    }

    }

    @Override
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
                        if (newLocation.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
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

    @Override
    public Actor getPlayerObj(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int k : xArr) {
            for (int i : yArr) {
                try {
                    Location newLocation = currentLocation.map().at(x + k, y + i);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
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

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location location = map.locationOf(this);
        tick(location);
        for (game.behaviour.Behaviour Behaviour : behaviours.values()) {
            if (location.map().locationOf(this) != null || this.getDisplayChar() != 'D') {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * a method that returns a boolean value if chancePercent is within the acceptable range
     *
     * @param chancePercent
     * @return
     */
    public boolean chance(int chancePercent) {
        Random r = new Random();
        return r.nextInt(100) < (chancePercent);
    }








}