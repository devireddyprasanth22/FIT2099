package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeItemAction extends Action {

    private final String actionFor;
    protected String hotKey;

    public ConsumeItemAction(String actionFor, String hotKey) {
        this.actionFor = actionFor;
        this.hotKey = hotKey;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println("called");
        boolean hasSuperMushroom = false;
        for(Item item: actor.getInventory()){
            if (item.getDisplayChar() == '^') {
                hasSuperMushroom = true;
                break;
            }
        }
        if(hasSuperMushroom){
            return this.menuDescription(actor);
        }

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "consume" + this.actionFor;
    }
}
