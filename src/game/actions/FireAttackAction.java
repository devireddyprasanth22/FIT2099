package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

import java.util.Random;

public class FireAttackAction extends AttackAction {
    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (actor.hasCapability(Status.FIRE_ATTACK)) {
            int damage = 20; // implement to hurt 20 every turn for three turn
            //map.locationOf(target).addExit(); // trying to add 'v' at target location for 3 turns
            result += System.lineSeparator() + actor + " " + "uses fire attack on" + " " + target + " for " + damage + " damage.";
            target.hurt(damage);
            if (!target.isConscious())
            {
                // remove actor
                map.removeActor(target);
                result += System.lineSeparator() + target + " is killed.";
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }
}