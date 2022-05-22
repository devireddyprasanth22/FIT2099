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
import game.actions.FireAttackAction;
import game.behaviour.Behaviour;
import game.behaviour.WanderBehaviour;
import game.magicalItems.SuperMushroom;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    private int turn = 0;

    private final String[] monologueLines = new String[]
            {
                    "Mugga mugga!",

                    "Ugha ugha... (Never gonna run around and desert you...)",

                    "Ooga-Chaka Ooga-Ooga!",
            };

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
                Player player = (Player) this.getPlayerObj(currentLocation);
                player.hurt(10);
                player.deactivateMagicalItem(Status.SUPER_MUSHROOM, 0);
            } else {
                //goomba misses player
                System.out.println("Goomba misses player");
            }
        }
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
            if (otherActor.hasCapability(Status.FIRE_ATTACK))
            {
                actions.add(new FireAttackAction(this,direction));
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    public void speak(){
        int max = monologueLines.length-1;
        int min = 0;
        if(turn % 2 == 0){
            Random random = new Random();
            int randomNum = random.nextInt(max + 1 - min) + min;
            System.out.println(monologueLines[randomNum]);
        }
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

        turn++;
        speak();

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
