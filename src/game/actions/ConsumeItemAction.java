package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;
import game.Status;

public class ConsumeItemAction extends Action {

    private final String actionFor;

    /**
     * Constructor for consuming items
     * @param actionFor String value for the item you want to use it for eg: SuperMushroom or PowerStar
     */
    public ConsumeItemAction(String actionFor) {
        this.actionFor = actionFor;
    }

    /**
     * This method is executed when the actor consumes the item
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return null
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (this.actionFor == "Super Mushroom"){
            System.out.println("Super mushroom reached");
            boolean hasSuperMushroom = false;
            for(Item item: actor.getInventory()){
                if (item.getDisplayChar() == '^') {
                    hasSuperMushroom = true;
                    actor.removeItemFromInventory(item);
                    break;
                }
            }
            if(hasSuperMushroom){
                player.addCapability(Status.SUPER_MUSHROOM);
                player.increaseMaxHp(50);
                return this.menuDescription(actor);
            }
        }else if(this.actionFor == "Power Star"){
            boolean hasPowerStar = false;
            for(Item item: actor.getInventory()){
                if (item.getDisplayChar() == '*') {
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
        else if (this.actionFor == "Fire Flower") {
            boolean hasFireFlower = false;
            for (Item item : actor.getInventory()) {
                if (item.getDisplayChar() == 'f') {
                    hasFireFlower = true;
                    actor.removeItemFromInventory(item);
                    break;
                }
            }
            if (hasFireFlower) {
                actor.addCapability(Status.FIRE_ATTACK);
                // add functionality to use fire attack on enemies
                return this.menuDescription(actor);
            }
        }
        return null;
    }

    /**
     * Menu description you want to show up
     * @param actor The actor performing the action.
     * @return String value of the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + this.actionFor;
    }
}