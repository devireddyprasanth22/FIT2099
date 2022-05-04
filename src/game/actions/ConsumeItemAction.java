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
        if (this.actionFor == "SuperMushroom"){
            System.out.println("Super muchsroom reached");
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
                actor.increaseMaxHp(50);
                return this.menuDescription(actor);
            }
        }else if(this.actionFor == "PowerStar"){
            boolean hasPowerStar = false;
            for(Item item: actor.getInventory()){
                if (item.getDisplayChar() == '^') {
                    hasPowerStar = true;
                    actor.removeItemFromInventory(item);
                    break;
                }
            }
            if(hasPowerStar){
                actor.addCapability(Status.POWER_STAR);
                actor.heal(200);
                return this.menuDescription(actor);
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + this.actionFor;
    }
}
