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
import game.behaviour.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

public class Bowser extends Enemy{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private final int hitRate;
    private final int punchDamage;

    /**
     * Constructor.
     *
     */
    public Bowser() {
        super("bowser", 'B', 500);
        this.behaviours.put(10, new WanderBehaviour());
        this.addCapability(Status.ENEMY);
        this.hitRate = 50;
        this.punchDamage = 80;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location myLocation = map.locationOf(this);
        boolean hitChance = this.hitRate < (Math.random() * 100);
        if (this.isPlayerInAttackRange(myLocation) && hitChance){
            Player player = (Player) this.getPlayerObj(myLocation);
            player.hurt(this.punchDamage);
        }
        return new DoNothingAction();
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
    public void tick(Location currentLocation) {

    }
}
