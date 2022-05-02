package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

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

    public JumpActorAction(Location moveToLocation, String direction, String hotKey) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.hotKey = hotKey;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // System.out.println("Jumped");
        // The actor has jumped
        Location actorLocation = map.locationOf(actor);
        if(this.moveToLocation.getDisplayChar() == "#".charAt(0)){
            this.jumpValidation(map, actor, 80, 20, "Wall", actorLocation);

        }
        if (this.moveToLocation.getDisplayChar() == "+".charAt(0)){
            this.jumpValidation(map, actor, 90, 10, "Sprout", actorLocation);

        }
        if(this.moveToLocation.getDisplayChar() == "t".charAt(0)){
            this.jumpValidation(map, actor, 80, 20, "Sapling", actorLocation);

        }
        if(this.moveToLocation.getDisplayChar() == "T".charAt(0)){
            this.jumpValidation(map, actor, 70, 30, "Mature", actorLocation);
        }

        return this.menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction;
    }

    private boolean successChance(int chance){
        return new Random().nextInt(100) < chance;
    }

    private void jumpValidation(GameMap map, Actor actor, int chance, int damage,String toJumpOn, Location actorLocation){
        if(successChance(chance)){
            map.moveActor(actor, this.moveToLocation);
            System.out.println(toJumpOn + " (x,y) -> " + "(" + actorLocation.x() + "," + actorLocation.y() + ")");
        }else{
            System.out.println("The actor was hurt");
            actor.hurt(damage);
        }
    }
}
