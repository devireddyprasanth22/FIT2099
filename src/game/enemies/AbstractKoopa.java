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
import game.actions.FireAttackAction;
import game.behaviour.Behaviour;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractKoopa extends Enemy{

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public AbstractKoopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * currentLocation is location of object on the map
     */
    public void tick(Location currentLocation) {

        int r1 = (int) (Math.random() * (11 - 1) + 1);
        //Dormant mechanic
        if (this.getHp() <= 0) {
            this.setDisplayChar('D');
        }
        //attack mechanic
        else if (isPlayerInAttackRange(currentLocation)) {
            //koopa is in attack range
            if (r1 > 5 && this.getDisplayChar() != 'D') {
                //koopa attacks player
                System.out.println("Koopa attacks player");
                getPlayerObj(currentLocation).hurt(30);

                Player player = (Player) this.getPlayerObj(currentLocation);
                player.hurt(30);
                player.deactivateMagicalItem(Status.SUPER_MUSHROOM, 0);

            } else if (this.getDisplayChar() != 'D') {
                //koopa misses player
                System.out.println("koopa misses player");
            }
        }
    }

    /**
     * otherActor: Actor around the player
     * direction: Direction in which otherActor is relative to Goomba
     * map: current GameMap object
     * <p>
     * returns: An ActionList object of actions that the other player can do to Koopa
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.getDisplayChar() != 'D') {
            if (otherActor.hasCapability(Status.FIRE_ATTACK)) {
                actions.add(new FireAttackAction(this, direction));
            }
            actions.add(new AttackAction(this, direction));
        }

        Player player = (Player) otherActor;
        boolean playerHasWrench = (boolean) player.inventoryContains("Wrench").get(1);

        if (playerHasWrench && this.getDisplayChar() == 'D') {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * actions: ActionList of allowable actions of Koopa
     * lastAction: Previous action of Koopa
     * map: current GameMap
     * display: current Display object
     * <p>
     * returns: current action of Koopa. If none, returns DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location location = map.locationOf(this);
        tick(location);
        for (Behaviour Behaviour : behaviours.values()) {
            if (location.map().locationOf(this) != null || this.getDisplayChar() != 'D') {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }
}
