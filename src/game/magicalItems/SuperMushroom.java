package game.magicalItems;

import edu.monash.fit2099.engine.actions.Action;
import game.Status;

import java.util.ArrayList;
import java.util.List;

public class SuperMushroom extends MagicalItems{
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addCapability(Status.INCREASE_MAX_HP);
        this.addCapability(Status.EVOLVE_CHAR);
        this.addCapability(Status.FREE_JUMP);
        this.addCapability(Status.SUPER_MUSHROOM);
    }

}
