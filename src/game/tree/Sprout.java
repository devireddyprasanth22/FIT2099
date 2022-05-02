package game.tree;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Goomba;

public class Sprout extends Tree {
    /**
     * Constructor for Sprout
     */
    public Sprout() {
        super('+');
    }

    /**
     * Method called tick that helps keep track of turns and gets location
     * Adds goomba at a 10% chance and becomes a sapling if turn is 10
     * @param location
     */
    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(10) && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
        if (this.getTurn() == 10) {
            location.setGround(new Sapling());
        }
    }

}