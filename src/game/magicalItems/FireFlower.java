package game.magicalItems;

import game.Status;

public class FireFlower extends MagicalItems{
    /**
     * Constructor for SuperMushroom
     */
    public FireFlower() {
        super("Fire Flower", 'f', true);
        this.addCapability(Status.FIRE_ATTACK);
    }
}
