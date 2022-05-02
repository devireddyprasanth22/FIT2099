package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Status;

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
        System.out.println("Construccted");
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println(actor.hasCapability(Status.CAN_JUMP));
        if(actor.hasCapability(Status.CAN_JUMP)){
            char charAtLocation = map.at(moveToLocation.x(), moveToLocation.y()).getDisplayChar();
            if (charAtLocation == "+".charAt(0)){
                actor.hurt(10);
            }else if(charAtLocation == "t".charAt(0)){
                actor.hurt(20);
            }else if(charAtLocation == "T".charAt(0)){
                actor.hurt(30);
            }else if(charAtLocation == "#".charAt(0)){
                actor.hurt(20);
            }else {
                System.out.println("VALUE ERROR");
            }
        }else{
            map.moveActor(actor, moveToLocation);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction;
    }
}
