package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Coin;
import game.groundItems.Dirt;
import game.Status;

import java.util.Random;

public class JumpActorAction extends Action {
    /**
     * Target location
     */
    protected Location moveToLocation;
    /**
     * One of the 8-d navigation
     */
    protected String direction;
    /**
     * Or the command key
     */
    protected String hotKey;

    /**
     * Constructor for JumpActorAction
     * @param moveToLocation
     * @param direction
     * @param hotKey
     */

    public JumpActorAction(Location moveToLocation, String direction, String hotKey) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.hotKey = hotKey;
    }

    /**
     * Method called normalJump that validates what its jumping over and the success and fail rate
     * @param actor
     * @param map
     * @param actorLocation
     */

    public void normalJump(Actor actor, GameMap map, Location actorLocation) {
        if (this.moveToLocation.getDisplayChar() == "#".charAt(0)) {
            this.jumpValidation(map, actor, 80, 20, "Wall", actorLocation);
        }
        if (this.moveToLocation.getDisplayChar() == "+".charAt(0)) {
            this.jumpValidation(map, actor, 90, 10, "Sprout", actorLocation);
        }
        if (this.moveToLocation.getDisplayChar() == "t".charAt(0)) {
            this.jumpValidation(map, actor, 80, 20, "Sapling", actorLocation);
        }
        if (this.moveToLocation.getDisplayChar() == "T".charAt(0)) {
            this.jumpValidation(map, actor, 70, 30, "Mature", actorLocation);
        }
    }

    /**
     *a method called superMushroomJump which basically allows the player to jump freely when they consume the super mushroom
     * @param actor
     * @param map
     * @param actorLocation
     */
    public void superMushroomJump(Actor actor, GameMap map, Location actorLocation) {
        if (this.moveToLocation.getDisplayChar() == "#".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Wall", actorLocation);
        }
        if (this.moveToLocation.getDisplayChar() == "+".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Sprout", actorLocation);
        }
        if (this.moveToLocation.getDisplayChar() == "t".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Sapling", actorLocation);
        }
        if (this.moveToLocation.getDisplayChar() == "T".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Mature", actorLocation);
        }
    }

    /**
     * powerStarJump is a method that is implemented for when the player consumes the powerStar
     * @param actor
     * @param map
     * @param actorLocation
     */
    public void powerStarJump(Actor actor, GameMap map, Location actorLocation) {
        if (this.moveToLocation.getDisplayChar() == "#".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Wall", actorLocation);
            this.moveToLocation.setGround(new Dirt());
            this.moveToLocation.addItem(new Coin());
       }
        if (this.moveToLocation.getDisplayChar() == "+".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Sprout", actorLocation);
            this.moveToLocation.setGround(new Dirt());
            this.moveToLocation.addItem(new Coin());
        }
        if (this.moveToLocation.getDisplayChar() == "t".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Sapling", actorLocation);
            this.moveToLocation.setGround(new Dirt());
            this.moveToLocation.addItem(new Coin());
        }
        if (this.moveToLocation.getDisplayChar() == "T".charAt(0)) {
            this.jumpValidation(map, actor, 100, 0, "Mature", actorLocation);
            this.moveToLocation.setGround(new Dirt());
            this.moveToLocation.addItem(new Coin());
        }
    }


    @Override
    /**
     * Method called execute that checks the capability and perform the appropriate jump action
     */
    public String execute(Actor actor, GameMap map) {
        // The actor has jumped
        Location actorLocation = map.locationOf(actor);
        if (actor.hasCapability(Status.SUPER_MUSHROOM)) {
            this.superMushroomJump(actor, map, actorLocation);
        } else if (actor.hasCapability(Status.POWER_STAR)) {
            this.powerStarJump(actor, map, actorLocation);
        } else {
            this.normalJump(actor, map, actorLocation);
        }

        return this.menuDescription(actor);
    }

    @Override
    /**
     * menuDescription returns a string that is displayed to the user
     */
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction;
    }

    /**
     * successChance returns a boolean value depending on wether or not the chance percent is met
     * @param chance
     * @return
     */
    private boolean successChance(int chance) {
        return new Random().nextInt(100) < chance;
    }

    /**
     * jumpValidation does a validation if the jump has been successful and displays where the actor has jumped to
     * @param map
     * @param actor
     * @param chance
     * @param damage
     * @param toJumpOn
     * @param actorLocation
     */
    private void jumpValidation(GameMap map, Actor actor, int chance, int damage, String toJumpOn, Location actorLocation) {
        if (successChance(chance)) {
            map.moveActor(actor, this.moveToLocation);
            System.out.println(toJumpOn + " (x,y) -> " + "(" + actorLocation.x() + "," + actorLocation.y() + ")");
        } else {
            System.out.println("The actor was hurt");
            actor.hurt(damage);
        }
    }
}
