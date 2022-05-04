package game;

import edu.monash.fit2099.engine.items.Item;

public class Coin extends Item {
    /***
     * Constructor for creating a new coin
     */
    public Coin() {
        super("Coin", '$', true);
        this.addCapability(Status.REMOVE);
    }

}
