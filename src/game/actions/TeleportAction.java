package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class TeleportAction extends MoveActorAction {

    public TeleportAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.hasCapability(Status.TELEPORT_TO_LAVAZONE)){
//            Teleport to lavazone
            actor.removeCapability(Status.TELEPORT_TO_LAVAZONE);
            actor.addCapability(Status.TELEPORT_TO_MAINMAP);
            map.moveActor(actor, moveToLocation);
        }else if(actor.hasCapability(Status.TELEPORT_TO_MAINMAP)){
//            Teleport to main map
            actor.removeCapability(Status.TELEPORT_TO_MAINMAP);
            actor.addCapability(Status.TELEPORT_TO_LAVAZONE);
            map.moveActor(actor, moveToLocation);
        }else{
//            Raise map not found
        }
        return menuDescription(actor);
    }
}
