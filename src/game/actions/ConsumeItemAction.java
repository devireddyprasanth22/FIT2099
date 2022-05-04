package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class ConsumeItemAction extends Action {

    private final String actionFor;

    public ConsumeItemAction(String actionFor) {
        this.actionFor = actionFor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        boolean hasSuperMushroom = false;
        for(Item item: actor.getInventory()){
            if (item.getDisplayChar() == '^') {
                hasSuperMushroom = true;
                actor.removeItemFromInventory(item);
                break;
            }
        }
        if(hasSuperMushroom){
            actor.addCapability(Status.SUPER_MUSHROOM);
            return this.menuDescription(actor);
        }

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + this.actionFor;
    }
}
