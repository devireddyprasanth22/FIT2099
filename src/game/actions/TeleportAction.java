//package game.actions;
//
//import edu.monash.fit2099.engine.actions.MoveActorAction;
//import edu.monash.fit2099.engine.actors.Actor;
//import edu.monash.fit2099.engine.positions.GameMap;
//import edu.monash.fit2099.engine.positions.Location;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TeleportAction extends MoveActorAction {
//
//    private final Location actorLocation;
//    private final List<GameMap> maps = new ArrayList<>();
//
//    public TeleportAction(GameMap... mapParams) {
//        maps.addAll(List.of(mapParams));
//        if()
//        super(moveToLocation, direction);
//        this.actorLocation = actorLocation;
//    }
//    // getter
//    public Location getActorLocation() {
//        return actorLocation;
//    }
//
//    public void preExecute(Actor actor, ) {
//
//        this.execute(actor, maps.get(0));
//    }
//
//    @Override
//    public String execute(Actor actor, GameMap map) {
//        map.moveActor(actor, moveToLocation);
//        return menuDescription(actor);
//    }
//
//
//}
