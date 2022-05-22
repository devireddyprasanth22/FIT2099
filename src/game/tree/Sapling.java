package game.tree;


import edu.monash.fit2099.engine.positions.Location;
import game.Coin;
import game.Status;
import game.magicalItems.FireFlower;


public class Sapling extends Tree {
    /**
     * Constructor for Sapling
     */
    public Sapling() {
        super('t');
        this.addCapability(Status.SAPLING);
        this.addCapability(Status.IS_JUMPABLE);
    }
    /**
     * Method called tick that helps keep track of turns and gets location
     * Adds a new coin at a 10% chance and becomes a Mature tree if turn is 10
     * @param location
     */
    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(10)) {
            location.addItem(new Coin());
        }
        if (this.getTurn() == 10) {
            location.setGround(new Mature());
            if (chance(50) && !location.containsAnActor()) {
                location.addItem(new FireFlower());
            }
        }
    }

}