package game.magicalItems;

import edu.monash.fit2099.engine.actions.Action;
import game.Status;
import game.actions.ConsumeItemAction;

import java.util.ArrayList;
import java.util.List;

public class SuperMushroom extends MagicalItems{
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addCapability(Status.SUPER_MUSHROOM);
    }

}
