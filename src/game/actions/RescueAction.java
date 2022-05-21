package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;

public class RescueAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if((boolean) player.inventoryContains("Key").get(1)){
            System.out.println("princess is rescued");
            map.removeActor(actor);
        }
        return this.menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Rescue princess";
    }
}
