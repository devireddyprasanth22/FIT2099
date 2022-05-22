package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Speak;
import game.Status;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviour.Behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PiranhaPlant extends Enemy implements Speak {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    private int turn = 0;

    private final String[] monologueLines = new String[]
            {
                    "Slsstssthshs~! (Never gonna say goodbye~)",

                    "Ohmnom nom nom nom.",
            };

    /**
     * Constructor.
     */
    public PiranhaPlant() {

        super("Piranha Plant", 'Y', 150);
        this.addCapability(Status.REMOVE);
        this.addCapability(Status.ENEMY);
        this.addCapability(Status.PIRANHA_PLANT);
        System.out.println(this.getHp());

    }

    @Override
    public void tick(Location currentLocation) {
        System.out.println(this.getHp());
        if (isPlayerInAttackRange(currentLocation)) {
            if (chance(50)) {
                System.out.println("Piranha Plant attacks player");
                getPlayerObj(currentLocation).hurt(90);
            } else {
                System.out.println("Piranha Plant misses player");
            }
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
    public void speak(){
        int max = monologueLines.length-1;
        int min = 0;
        if(turn % 2 == 0){
            Random random = new Random();
            int randomNum = random.nextInt(max + 1 - min) + min;
            System.out.println(monologueLines[randomNum]);
        }
    }

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

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        turn++;
        speak();
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
