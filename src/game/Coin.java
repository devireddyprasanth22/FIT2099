package game;

import edu.monash.fit2099.engine.items.Item;

public class Coin extends Item {
    /***
     * Constructor.
     */
    public Coin() {
        super("Coin", '$', true);
        this.addCapability(Status.IS_RESETTABLE);
    }

}
